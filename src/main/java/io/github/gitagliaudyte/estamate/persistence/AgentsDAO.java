package io.github.gitagliaudyte.estamate.persistence;

import io.github.gitagliaudyte.estamate.entities.Agent;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class AgentsDAO {

    @Inject
    private EntityManager entityManager;

    public void setEntityManager(EntityManager entityManager) { this.entityManager = entityManager; }
    public List<Agent> loadAll() { return entityManager.createNamedQuery("Agent.findAll", Agent.class).getResultList(); }
    public void persist(Agent agent) { this.entityManager.persist(agent); }
    public Agent findById(Integer id) { return entityManager.find(Agent.class, id); }
}
