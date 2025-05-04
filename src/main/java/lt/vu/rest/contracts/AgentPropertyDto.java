package lt.vu.rest.contracts;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Agent;
import lt.vu.entities.Property;

import java.util.List;

@Getter @Setter
public class AgentPropertyDto {
    private String name;
    private String phoneNumber;
    private Integer version;
    private List<Property> properties;

    public static AgentPropertyDto convertToAgentPropertyDto(Agent agent) {
        if(agent != null) {
            AgentPropertyDto agentPropertyDto = new AgentPropertyDto();
            agentPropertyDto.setName(agent.getName());
            agentPropertyDto.setPhoneNumber(agent.getPhoneNumber());
            agentPropertyDto.setProperties(agent.getProperties());
            return agentPropertyDto;
        }
        return null;
    }
}
