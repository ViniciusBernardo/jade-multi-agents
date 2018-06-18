package bridge;

import jade.core.Agent;
import jade.core.behaviours.*;
// import jade.lang.acl.ACLMessage;
// import jade.lang.acl.MessageTemplate;

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
