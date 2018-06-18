package bridge;

import jade.core.Agent;
import jade.core.behaviours.*;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

import utils.YellowPages;

public class BridgeAgent extends Agent {

    public static final String TYPE = "Bridge";

    protected void setup() {

        YellowPages.df_register(this, getAID(), getLocalName(), TYPE);

        addBehaviour(new PassBridgeRequestsServer());
    }

    private class PassBridgeRequestsServer extends CyclicBehaviour {

        public void action() {
            // System.out.println("Pass Bridge");
            MessageTemplate message_template = MessageTemplate.MatchPerformative(ACLMessage.CFP);
            ACLMessage message = myAgent.receive(message_template);

            if (message != null) {
              ACLMessage reply = message.createReply();
              reply.setPerformative(ACLMessage.PROPOSE);
              System.out.println(message + " sold to agent " + message.getSender().getName());
              reply.setContent("Hi, how are you?");
              myAgent.send(reply);
           } else {
              // System.out.println("Block");
              // block();
           }
        }
    }
}
