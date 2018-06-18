package bridge;

import jade.core.Agent;
import jade.core.behaviours.*;

import utils.YellowPages;
import bridge.CrossRequest;

public class BridgeAgent extends Agent {

    public static final String TYPE = "Bridge";

    public boolean busy = false;

    protected void setup() {

        YellowPages.df_register(this, getAID(), getLocalName(), TYPE);

        addBehaviour(new CrossRequest(this));
        addBehaviour(new FinishRequest(this));
    }
}
