package bridge;

import jade.core.behaviours.*;
import jade.lang.acl.ACLMessage;
import utils.YellowPages;

public class FinishRequest extends CyclicBehaviour {

    private BridgeAgent bridge;

    public FinishRequest(BridgeAgent agent) {
        this.bridge = agent;
    }

    public void action() {

        ACLMessage envelope = YellowPages.receive_message(myAgent, ACLMessage.CONFIRM);

        if(envelope != null) {

            if(envelope.getContent().contains("I crossed the bridge")) {
                System.out.println("FinishRequest: Atravessou");
                bridge.busy = false;
            }

        }
    }
}
