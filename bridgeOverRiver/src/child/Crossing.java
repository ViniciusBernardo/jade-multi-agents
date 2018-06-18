package child;

import jade.core.AID;
import jade.lang.acl.ACLMessage;

import child.NamePrinter;
import child.ChildAgent;
import utils.YellowPages;

public class Crossing extends NamePrinter {

    public Crossing(ChildAgent child) {
        super(child);
    }

    public void action() {

        ACLMessage envelope = YellowPages.receive_message(myAgent, ACLMessage.INFORM);

        if(envelope != null) {

            if(envelope.getContent().contains("Yes")) {
                System.out.println("CROSSING received: Yes");
                YellowPages.reply_message(myAgent, ACLMessage.CONFIRM, envelope, "I crossed the bridge");
                child.change_side();
            } else {
                System.out.println("CROSSING received: No");
            }
        }
    }
}
