package io.github.gitagliaudyte.estamate.rest.contracts;

import io.github.gitagliaudyte.estamate.entities.Agent;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter @Setter
public class AgentDto {
    private int id;
    private String name;
    private String phoneNumber;
    private List<PropertyDto> properties;

    public static AgentDto convertToAgentDto(Agent agent) {
        if(agent != null) {
            AgentDto agentDto = new AgentDto();
            agentDto.setId(agent.getId());
            agentDto.setName(agent.getName());
            agentDto.setPhoneNumber(agent.getPhoneNumber());
            agentDto.setProperties(agent.getProperties().stream()
                    .map(PropertyDto::convertToPropertyDto)
                    .collect(Collectors.toList()));
            return agentDto;
        }
        return null;
    }
}
