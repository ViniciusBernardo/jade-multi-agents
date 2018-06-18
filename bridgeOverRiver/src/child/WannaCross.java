package child;

import child.NamePrinter;

public class WannaCross extends NamePrinter {

    public void action() {
        System.out.println("Executing behaviour " + getBehaviourName());
    }
}
