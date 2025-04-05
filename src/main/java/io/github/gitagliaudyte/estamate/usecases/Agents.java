package io.github.gitagliaudyte.estamate.usecases;

import io.github.gitagliaudyte.estamate.entities.Agent;
import io.github.gitagliaudyte.estamate.entities.Property;
import io.github.gitagliaudyte.estamate.interceptors.LoggedInvocation;
import io.github.gitagliaudyte.estamate.persistence.AgentsDAO;
import io.github.gitagliaudyte.estamate.persistence.PropertiesDAO;
import javax.faces.context.FacesContext;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Model
public class Agents {
    @Inject
    private AgentsDAO agentsDAO;

    @Getter @Setter
    private Agent agentToCreate = new Agent();

    @Getter
    private List<Agent> allAgents;

    @PostConstruct
    public void init(){
        loadAllAgents();
    }

    @Transactional
    @LoggedInvocation
    public String createAgent(){
        this.agentsDAO.persist(agentToCreate);
        return "index?faces-redirect=true";
    }

    private void loadAllAgents(){
        this.allAgents = agentsDAO.loadAll();
    }
}
