package bridge;

import jade.core.Agent;
import jade.core.behaviours.*;
import jade.lang.acl.ACLMessage;
// import jade.lang.acl.MessageTemplate;

import utils.YellowPages;

public class BridgeAgent extends Agent {

    public static final String TYPE = "Bridge";

    protected void setup() {

        YellowPages.df_register(this, getAID(), getLocalName(), TYPE);

        addBehaviour(new PassBridgeRequestsServer());
    }

    private class PassBridgeRequestsServer extends CyclicBehaviour {

        public void action() {
            System.out.println("Request");
            ACLMessage envelope = YellowPages.receive_message(myAgent, ACLMessage.CFP);
            if(envelope != null) {
                System.out.println("------------------------------------------");
                System.out.println(envelope.getContent());
                System.out.println("------------------------------------------");
                YellowPages.reply_message(myAgent, ACLMessage.INFORM, envelope, "Hello");
            }
        }
    }
}
