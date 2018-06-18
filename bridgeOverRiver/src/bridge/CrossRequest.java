package bridge;

import jade.core.behaviours.*;
import jade.lang.acl.ACLMessage;
import utils.YellowPages;

public class CrossRequest extends CyclicBehaviour {

    private BridgeAgent bridge;

    public CrossRequest(BridgeAgent agent) {
        this.bridge = agent;
    }

    public void action() {
        // System.out.println("Request");
        ACLMessage envelope = YellowPages.receive_message(myAgent, ACLMessage.CFP);
        if(envelope != null) {

            if(envelope.getContent().contains("Can I pass the bridge?")) {
                if(bridge.busy) {
                    YellowPages.reply_message(myAgent, ACLMessage.INFORM, envelope, "No");
                } else {
                    bridge.busy = true;
                    YellowPages.reply_message(myAgent, ACLMessage.INFORM, envelope, "Yes");
                }
            } else {
            System.out.println("PERFORM_REQUEST receiver: " + envelope.getContent());
                YellowPages.reply_message(myAgent, ACLMessage.INFORM, envelope, "I don't understand!");
            }
        }
    }
}
