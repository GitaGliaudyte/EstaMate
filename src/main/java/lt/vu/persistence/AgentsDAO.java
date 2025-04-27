package lt.vu.persistence;

import lt.vu.entities.Agent;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class AgentsDAO {

    @Inject
    private EntityManager entityManager;

    public void setEntityManager(EntityManager entityManager) { this.entityManager = entityManager; }
    public List<Agent> loadAll() { return entityManager.createNamedQuery("Agent.findAll", Agent.class).getResultList(); }
    public void persist(Agent agent) { this.entityManager.persist(agent); }
    public Agent findById(Integer id) { return entityManager.find(Agent.class, id); }

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public Agent update(Agent agent) {
        agent = entityManager.merge(agent);
        entityManager.flush();
        return agent;
    }
}
