package utils;

import jade.core.Agent;
import jade.core.AID;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

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

    public static AID[] df_search(Agent agent, String type) {

        DFAgentDescription df_description = newDFAgent(type);

        try {
          DFAgentDescription[] result = DFService.search(agent, df_description);
          // System.out.println("    Found the following " + type  + " agents (" + result.length  + "):");
          AID aids[] = new AID[result.length];
          for (int i = 0; i < result.length; ++i) {
            aids[i] = result[i].getName();
            // System.out.println("        " + aids[i].getName());
          }

          return aids;

        } catch (FIPAException exception) {
          exception.printStackTrace();
        }

        return null;
    }

    public static void send_message(Agent agent, AID destiny, int type, String message) {

        // System.out.println("Send Message");
        if(destiny != null) {
            ACLMessage envelope = new ACLMessage(type);
            envelope.addReceiver(destiny);
            envelope.setContent(message);
            agent.send(envelope);
        }
    }

    public static ACLMessage receive_message(Agent agent, int type) {

        // System.out.println("Receive Message");
        MessageTemplate template = MessageTemplate.MatchPerformative(type);
        ACLMessage envelope = agent.receive(template);

        return envelope;
    }

    public static void reply_message(Agent agent, int type, ACLMessage envelope, String message) {

        // System.out.println("Reply Message");
        if(envelope != null) {
            ACLMessage reply = envelope.createReply();
            reply.setPerformative(type);
            reply.setContent(message);
            agent.send(reply);
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
