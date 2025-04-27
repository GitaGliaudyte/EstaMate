package lt.vu.usecases;

import lt.vu.entities.Owner;
import lt.vu.interceptors.LoggedInvocation;
import lt.vu.persistence.OwnersDAO;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class Owners {
    @Inject
    private OwnersDAO ownersDAO;

    @Getter @Setter
    private Owner ownerToCreate = new Owner();

    @Getter
    private List<Owner> allOwners;

    @PostConstruct
    public void init() {
        loadAllOwners();
    }

    @Transactional
    @LoggedInvocation
    public String createOwner() {
        this.ownersDAO.persist(ownerToCreate);
        return "index?faces-redirect=true";
    }

    private void loadAllOwners() {
        this.allOwners = ownersDAO.loadAll();
    }

}
