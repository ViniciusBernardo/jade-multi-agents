package child;

import jade.core.behaviours.OneShotBehaviour;

public class NamePrinter extends OneShotBehaviour {

    public void action() {
        System.out.println("Executing behaviour " + getBehaviourName());
    }
}

