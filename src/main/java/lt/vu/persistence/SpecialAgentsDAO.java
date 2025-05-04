package lt.vu.persistence;

import lt.vu.entities.Agent;
import lt.vu.interceptors.LoggedInvocation;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Specializes;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@ApplicationScoped
@LoggedInvocation
@Specializes
public class SpecialAgentsDAO extends AgentsDAO {
    @Inject
    private EntityManager em;

    @Override
    public void persist (Agent agent) {
        agent.setName(agent.getName()+ " - specialist");
        System.out.println("specializes");
        this.em.persist(agent);
    }
}
