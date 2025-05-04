package lt.vu.rest.contracts;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Agent;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Getter @Setter
public class AgentDto {
    @Inject
    private EntityManager em;

    private String name;
    private String phoneNumber;
    private Integer version;
    private List<PropertyDto> properties;

    public static AgentDto convertToAgentDto(Agent agent) {
        if(agent != null) {
            AgentDto agentDto = new AgentDto();
            agentDto.setName(agent.getName());
            agentDto.setVersion(agent.getVersion());
            agentDto.setPhoneNumber(agent.getPhoneNumber());
            agentDto.setProperties(
                    agent.getProperties().stream()
                            .map(PropertyDto::convertToPropertyDto)
                            .collect(Collectors.toList())
            );
            return agentDto;
        }
        return null;
    }

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public Agent update(Agent agent) {
        agent = em.merge(agent);
        em.flush();
        return agent;
    }
}
