package agents;
import jade.core.Agent;
import jade.core.AID;
import jade.lang.acl.ACLMessage;
import jade.core.behaviours.FSMBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.MessageTemplate;

public class Child extends Agent {
    private static final String BEGIN = "Child iniciating";
    private static final String PLAYING = "Playing";
    private static final String WANNA_CROSS = "I wanna cross the bridge";
    private static final String CROSSING = "I'm crossing the bridge";
    private String SIDE = "A";

    protected void setup() {

        FSMBehaviour fsm = new FSMBehaviour(this) {
            public int onEnd() {
                System.out.println("Child behaviour completed.");
                myAgent.doDelete();
                return super.onEnd();
            }
        };
     
        // Register state PLAYING (first state)
        fsm.registerFirstState(new RandomGenerator(2), PLAYING);
     
        // Register state WANNA_CROSS
        fsm.registerState(new SendMessage(), WANNA_CROSS);
     
        // Register state CROSSING
        fsm.registerState(new CrossBridge(), CROSSING);

        fsm.registerDefaultTransition(BEGIN, PLAYING);
        fsm.registerTransition(PLAYING, PLAYING, 0);
        fsm.registerTransition(PLAYING, WANNA_CROSS, 1);
        fsm.registerTransition(WANNA_CROSS, CROSSING, 2);
        fsm.registerTransition(WANNA_CROSS, WANNA_CROSS, 3);
        //fsm.registerDefaultTransition(CROSSING, PLAYING);

        addBehaviour(fsm);
    }

    private class CrossBridge extends OneShotBehaviour {
        public void action() {
            System.out.println(getBehaviourName());
            SIDE = "B";
        }
    }
    
    private class SendMessage extends OneShotBehaviour {
    	private int canAcross;
    	
        public void action() {
            System.out.println("Executanto "+getBehaviourName());
            ACLMessage msg = new ACLMessage(ACLMessage.QUERY_IF);
            msg.setOntology("presence");
            msg.setContent("alive");
            msg.addReceiver(new AID("Bridge", AID.ISLOCALNAME));
            myAgent.send(msg);
            ACLMessage replyMsg = blockingReceive(MessageTemplate.MatchPerformative(ACLMessage.INFORM));
            if("1".equals(replyMsg.getContent())){
            	this.canAcross = 2;
            }else if("0".equals(replyMsg.getContent())) {
            	this.canAcross = 3;
            }
            System.out.println("Ola mundo: " + replyMsg.getContent());
        }
        
        public int onEnd() {
            return this.canAcross;
        }
    }

    private class RandomGenerator extends CrossBridge {
        private int maxExitValue;
        private int exitValue;
        
        private RandomGenerator(int max) {
            super();
            maxExitValue = max;
        }
        
        public void action() {
            System.out.println("Executing behaviour "+getBehaviourName());
            exitValue = (int) (Math.random() * maxExitValue);
            System.out.println("Exit value is "+exitValue);
        }
        
        public int onEnd() {
            return exitValue;
        }
    }
}
