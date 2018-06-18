package utils;

import jade.core.Agent;
import jade.core.AID;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;

public class YellowPages {

    public static void df_register(Agent agent, AID agent_aid, String name, String type) {

        DFAgentDescription df_description = new DFAgentDescription();
        df_description.setName(agent_aid);

        ServiceDescription service_description = new ServiceDescription();
        service_description.setType(name);
        service_description.setName(type);

        df_description.addServices(service_description);

        try {
            DFService.register(agent, df_description);
            System.out.println( type.toUpperCase() + "[" + name + "]: has been registred.");
        } catch (FIPAException exception) {
            exception.printStackTrace();
        }
    }
}
