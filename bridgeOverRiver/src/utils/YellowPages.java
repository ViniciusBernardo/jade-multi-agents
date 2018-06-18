package utils;

import jade.core.Agent;
import jade.core.AID;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;

public class YellowPages {

    public static void df_register(Agent agent, AID agent_aid, String name, String type) {

        DFAgentDescription df_description = newDFAgent(type, agent_aid, name);

        try {
            DFService.register(agent, df_description);
            System.out.println( type.toUpperCase() + "[" + name + "]: has been registred.");
        } catch (FIPAException exception) {
            exception.printStackTrace();
        }
    }

    public static void df_search(Agent agent, String type) {

        DFAgentDescription df_description = newDFAgent(type);

        try {
          DFAgentDescription[] result = DFService.search(agent, df_description);
          System.out.println("    Found the following " + type  + " agents (" + result.length  + "):");
          for (int i = 0; i < result.length; ++i) {
            System.out.println("        " + result[i].getName().getName());
          }
        } catch (FIPAException exception) {
          exception.printStackTrace();
        }
    }

    private static DFAgentDescription newDFAgent(String type, AID agent_aid, String name) {

        DFAgentDescription df_description = new DFAgentDescription();
        df_description.setName(agent_aid);

        ServiceDescription service_description = new ServiceDescription();
        service_description.setName(type);
        service_description.setType(name);

        df_description.addServices(service_description);

        return df_description;
    }

    private static DFAgentDescription newDFAgent(String type) {

        DFAgentDescription df_description = new DFAgentDescription();

        ServiceDescription service_description = new ServiceDescription();
        service_description.setName(type);

        df_description.addServices(service_description);

        return df_description;
    }
}
