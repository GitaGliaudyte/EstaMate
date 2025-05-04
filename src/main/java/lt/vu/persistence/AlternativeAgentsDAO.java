package lt.vu.persistence;

import lt.vu.entities.Agent;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
@Alternative
public class AlternativeAgentsDAO extends AgentsDAO {
    @Inject
    EntityManager em;

    @Override
    public List<Agent> loadAll() {
        System.out.println("Alternative Method");
        return em.createQuery("select a from Agent a where a.version <> 0", Agent.class).getResultList();
    }
}
