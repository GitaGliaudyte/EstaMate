package io.github.gitagliaudyte.estamate.persistence;

import io.github.gitagliaudyte.estamate.entities.Owner;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

public class OwnersDAO {
    @Inject
    private EntityManager entityManager;

    public void setEntityManager(EntityManager entityManager) { this.entityManager = entityManager; }
    public List<Owner> loadAll() { return entityManager.createNamedQuery("Owner.findAll", Owner.class).getResultList(); }
    public void persist(Owner owner) { this.entityManager.persist(owner); }
    public Owner findById(Integer id) { return entityManager.find(Owner.class, id); }
}
