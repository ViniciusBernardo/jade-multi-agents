package child;

import jade.core.behaviours.OneShotBehaviour;

import child.ChildAgent;

public class NamePrinter extends OneShotBehaviour {

    protected ChildAgent child;

    public NamePrinter(ChildAgent child) {
        this.child = child;
    }

    public void action() {
        System.out.println("Executing behaviour " + getBehaviourName());
    }
}

