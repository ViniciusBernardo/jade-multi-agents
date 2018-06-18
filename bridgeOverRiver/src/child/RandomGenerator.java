package child;

import child.ChildAgent;
import child.NamePrinter;

public class RandomGenerator extends NamePrinter {

    private int maxExitValue;
    private int exitValue;

    public RandomGenerator(ChildAgent child, int max) {
        super(child);
        maxExitValue = max;
    }

    public void action() {
        // System.out.println("Executing behaviour " + getBehaviourName());
        exitValue = (int) (Math.random() * maxExitValue);
        // System.out.println("Exit value is " + exitValue);
    }

    public int onEnd() {
        return exitValue;
    }
}
