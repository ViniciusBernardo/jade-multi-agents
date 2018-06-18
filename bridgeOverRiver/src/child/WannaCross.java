package child;

import jade.core.AID;
import jade.lang.acl.ACLMessage;

import child.NamePrinter;
import child.ChildAgent;
import utils.YellowPages;

public class WannaCross extends NamePrinter {

    public WannaCross(ChildAgent child) {
        super(child);
    }

    public void action() {

         AID search_result[] = YellowPages.df_search(myAgent, "Bridge");
         AID bridge;

        if(search_result.length > 0) {
            bridge = search_result[0];
            YellowPages.send_message(myAgent, bridge, ACLMessage.CFP, "Can I pass the bridge?");
        }
    }
}
