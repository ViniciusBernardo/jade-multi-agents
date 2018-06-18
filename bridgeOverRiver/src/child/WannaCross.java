package child;

import jade.core.AID;
import jade.lang.acl.ACLMessage;

import child.NamePrinter;
import utils.YellowPages;

public class WannaCross extends NamePrinter {

    public WannaCross() {
        super();
        System.out.println("Executing behaviour " + getBehaviourName());
    }

    public void action() {

        System.out.println("Executing behaviour " + getBehaviourName());
        System.out.println("  Trying pass bridge");

         AID search_result[] = YellowPages.df_search(myAgent, "Bridge");
         AID bridge;

        if(search_result.length > 0) {
            bridge = search_result[0];
            YellowPages.send_message(myAgent, bridge, ACLMessage.CFP, "Can I pass the bridge?");
        }

    }
}
