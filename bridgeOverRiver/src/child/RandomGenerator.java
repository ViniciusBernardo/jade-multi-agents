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
        exitValue = (int) (Math.random() * maxExitValue);
    }

    public int onEnd() {
        return exitValue;
    }
}
