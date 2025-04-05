package io.github.gitagliaudyte.estamate.usecases;

import io.github.gitagliaudyte.estamate.entities.Agent;
import io.github.gitagliaudyte.estamate.entities.Property;
import io.github.gitagliaudyte.estamate.interceptors.LoggedInvocation;
import io.github.gitagliaudyte.estamate.persistence.AgentsDAO;
import io.github.gitagliaudyte.estamate.persistence.PropertiesDAO;
import lombok.Getter;
import lombok.Setter;

import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Named
@ViewScoped
public class AgentDetails implements Serializable {

    @Inject
    private AgentsDAO agentsDAO;

    @Inject
    private PropertiesDAO propertiesDAO;

    @Getter @Setter
    private Agent agent;

    @Getter
    private List<Property> availableProperties;

    @Getter @Setter
    private Integer selectedPropertyId = 0;

    @PostConstruct
    public void init() {
        if (agent == null) {
            Map<String, String> requestParameters = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
            Integer agentId = Integer.parseInt(requestParameters.get("agentId"));
            this.agent = agentsDAO.findById(agentId);
            this.agent.setProperties(propertiesDAO.findByAgentId(agentId));
            filterAvailableProperties();
        }
    }

    @Transactional
    @LoggedInvocation
    public String assignProperty() {
        if (selectedPropertyId > 0) {
            Property propertyToAssign = propertiesDAO.findById(selectedPropertyId);
            if (!agent.getProperties().contains(propertyToAssign)) {
                agent.getProperties().add(propertyToAssign);

                propertyToAssign.getAgents().add(agent);

                propertiesDAO.update(propertyToAssign);
                agentsDAO.update(agent);

                filterAvailableProperties();
            }
        }
        return "agentDetails?faces-redirect=true&agentId=" + this.agent.getId();
    }

    private void filterAvailableProperties() {
        List<Property> allProperties = propertiesDAO.loadAll();
        List<Property> properties = agent.getProperties();
        availableProperties = allProperties.stream().filter(s -> !properties.contains(s)).collect(Collectors.toList());
    }
}

