package agents;
import jade.core.Agent;
import jade.core.behaviours.FSMBehaviour;
import jade.core.behaviours.OneShotBehaviour;

public class Child extends Agent {
	private static final String BEGIN = "Child iniciating";
	private static final String PLAYING = "Playing";
	private static final String WANNA_CROSS = "I wanna cross the bridge";
	private static final String CROSSING = "I'm crossing the bridge";

	protected void setup() {

		FSMBehaviour fsm = new FSMBehaviour(this) {
            public int onEnd() {
                System.out.println("Child behaviour completed.");
                myAgent.doDelete();
                return super.onEnd();
            }
        };
     
        // Register state PLAYING (first state)
        fsm.registerFirstState(new RandomGenerator(2), PLAYING);
     
        // Register state WANNA_CROSS
        fsm.registerState(new NamePrinter(), WANNA_CROSS);
     
        // Register state CROSSING
        fsm.registerState(new NamePrinter(), CROSSING);

		fsm.registerDefaultTransition(BEGIN, PLAYING);
		fsm.registerTransition(PLAYING, PLAYING, 0);
		fsm.registerTransition(PLAYING, WANNA_CROSS, 1);
		fsm.registerDefaultTransition(WANNA_CROSS, CROSSING);
		fsm.registerDefaultTransition(CROSSING, PLAYING);

        addBehaviour(fsm);
	}

    private class NamePrinter extends OneShotBehaviour {
        public void action() {
            System.out.println("Executing behaviour "+getBehaviourName());
        }
    }

	private class RandomGenerator extends NamePrinter {
		private int maxExitValue;
		private int exitValue;
		
		private RandomGenerator(int max) {
			super();
			maxExitValue = max;
		}
		
		public void action() {
			System.out.println("Executing behaviour "+getBehaviourName());
			exitValue = (int) (Math.random() * maxExitValue);
			System.out.println("Exit value is "+exitValue);
		}
		
		public int onEnd() {
			return exitValue;
		}
	}
}
