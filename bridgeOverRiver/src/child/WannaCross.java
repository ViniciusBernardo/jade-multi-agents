package child;

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
        YellowPages.df_search(myAgent, "Bridge");
    }
}
