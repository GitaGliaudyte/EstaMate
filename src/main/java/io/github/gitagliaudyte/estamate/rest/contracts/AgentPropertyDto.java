package io.github.gitagliaudyte.estamate.rest.contracts;

import io.github.gitagliaudyte.estamate.entities.Agent;
import io.github.gitagliaudyte.estamate.entities.Property;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class AgentPropertyDto {
    public String name;
    public List<Property> properties;

    public static AgentPropertyDto convertToAgentPropertyDto(Agent agent) {
        if(agent != null) {
            AgentPropertyDto agentPropertyDto = new AgentPropertyDto();
            agentPropertyDto.setName(agent.getName());
            agentPropertyDto.setProperties(agent.getProperties());
            return agentPropertyDto;
        }
        return null;
    }
}
