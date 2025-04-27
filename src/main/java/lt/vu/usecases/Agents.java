package lt.vu.usecases;

import lt.vu.entities.Agent;
import lt.vu.interceptors.LoggedInvocation;
import lt.vu.persistence.AgentsDAO;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

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
