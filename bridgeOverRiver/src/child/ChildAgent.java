package child;

import jade.core.Agent;
import jade.core.behaviours.FSMBehaviour;

import child.RandomGenerator;
import child.WannaCross;

public class ChildAgent extends Agent {

    public static final String TYPE = "Child";

    private static final String BEGIN = "Child iniciating";
    private static final String PLAYING = "Playing";
    private static final String WANNA_CROSS = "I wanna cross the bridge";
    private static final String CROSSING = "I'm crossing the bridge";

    private String side = "Right";

    protected void setup() {

        FSMBehaviour fsm = new FSMBehaviour(this) {
            public int onEnd() {
                // System.out.println("Child behaviour completed.");
                myAgent.doDelete();
                return super.onEnd();
            }
        };

        // Register state PLAYING (first state)
        fsm.registerFirstState(new RandomGenerator(this, 2), PLAYING);

        // Register state WANNA_CROSS
        fsm.registerState(new WannaCross(this), WANNA_CROSS);

        // Register state CROSSING
        fsm.registerState(new Crossing(this), CROSSING);

        fsm.registerDefaultTransition(BEGIN, PLAYING);
        fsm.registerTransition(PLAYING, PLAYING, 0);
        fsm.registerTransition(PLAYING, WANNA_CROSS, 1);
        fsm.registerDefaultTransition(WANNA_CROSS, CROSSING);
        fsm.registerDefaultTransition(CROSSING, PLAYING);

        addBehaviour(fsm);
    }

    public void change_side() {
        if(this.side == "Right") {
            this.side = "Left";
            System.out.println(getLocalName() + ": Left side");
        } else {
            this.side = "Right";
            System.out.println(getLocalName() + ": Right side");
        }
    }

}
