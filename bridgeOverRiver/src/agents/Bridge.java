package agents;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class Bridge extends Agent {
	private MessageTemplate template = MessageTemplate.and(
	        MessageTemplate.MatchPerformative(ACLMessage.QUERY_IF),
	        MessageTemplate.MatchOntology("presence") );

	protected void setup() {
		addBehaviour(new CyclicBehaviour(this) {
            public void action() {
                ACLMessage msg = blockingReceive(template);
                try {
                	System.out.println("Conteudo da mensage: " + msg.getContent());
				} catch (NullPointerException e) {
					System.out.println("Mensagem sem conteudo");
				}
                if (msg != null) {
                    System.out.println("Received QUERY_IF message from agent "+msg.getSender().getName());
                    ACLMessage reply = msg.createReply();
                    if ("alive".equals(msg.getContent())) {
                        reply.setPerformative(ACLMessage.INFORM);
                        reply.setContent("1");
                    }
                    else {
                        reply.setPerformative(ACLMessage.INFORM);
                        reply.setContent("0");
                    }
                    myAgent.send(reply);
                }
                else {
                }
            }
        } );
	}
}
