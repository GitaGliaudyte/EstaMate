package io.github.gitagliaudyte.estamate.persistence;

import io.github.gitagliaudyte.estamate.entities.Property;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class PropertiesDAO {
    @Inject
    private EntityManager entityManager;

    public void setEntityManager(EntityManager entityManager) { this.entityManager = entityManager; }
    public List<Property> loadAll() { return entityManager.createNamedQuery("Property.findAll", Property.class).getResultList(); }
    public void persist(Property property) { this.entityManager.persist(property); }
    public Property findById(Integer id) { return entityManager.find(Property.class, id); }
}
