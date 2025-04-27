package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Agent;
import lt.vu.persistence.AgentsDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SessionScoped
@Named
public class OwnersAgents implements Serializable {

    @Inject
    private AgentsDAO agentsDAO;
    @Getter @Setter
    private List<Agent> agents;

    @PostConstruct
    public void init() {
        agents = new ArrayList<>();
        loadAgentsForOwner();
    }

    public void loadAgentsForOwner() {
        agents = agentsDAO.loadAll();
    }
}
