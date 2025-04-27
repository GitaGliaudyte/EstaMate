package lt.vu.persistence;

import lt.vu.entities.Property;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class PropertiesDAO {
    @Inject
    private EntityManager entityManager;

    public void setEntityManager(EntityManager entityManager) { this.entityManager = entityManager; }
    public List<Property> loadAll() { return entityManager.createNamedQuery("Property.findAll", Property.class).getResultList(); }
    public void persist(Property property) { this.entityManager.persist(property); }
    public Property findById(Integer id) { return entityManager.find(Property.class, id); }
    public List<Property> findByOwnerId(Integer ownerId) {
        return entityManager.createNamedQuery("Property.findByOwnerId", Property.class)
                .setParameter("ownerId", ownerId)
                .getResultList();
    }

    public List<Property> findByAgentId(Integer agentId) {
        return entityManager.createNamedQuery("Property.findByAgentId", Property.class)
                .setParameter("agentId", agentId)
                .getResultList();
    }

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public Property update(Property property) {
        property = entityManager.merge(property);
        entityManager.flush();
        return property;
    }
}
