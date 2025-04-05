package io.github.gitagliaudyte.estamate.usecases;

import io.github.gitagliaudyte.estamate.entities.Property;
import io.github.gitagliaudyte.estamate.entities.Owner;
import io.github.gitagliaudyte.estamate.interceptors.LoggedInvocation;
import io.github.gitagliaudyte.estamate.persistence.PropertiesDAO;
import io.github.gitagliaudyte.estamate.persistence.OwnersDAO;
import lombok.Getter;
import lombok.Setter;

import javax.faces.context.FacesContext;
import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Model
public class Properties {
    @Inject
    private PropertiesDAO propertiesDAO;

    @Inject
    private OwnersDAO ownersDAO;

    @Getter @Setter
    private Property propertyToCreate = new Property();

    @Getter
    private List<Property> allProperties;

    @Getter @Setter
    private Owner owner;

    @PostConstruct
    public void init(){
        loadAllProperties();

        Map<String, String> params = FacesContext.getCurrentInstance()
                .getExternalContext()
                .getRequestParameterMap();
        String ownerIdParam = params.get("ownerId");
        if (ownerIdParam != null) {
            try {
                Integer ownerId = Integer.valueOf(ownerIdParam);
                this.owner = ownersDAO.findById(ownerId);
            } catch (NumberFormatException e) {}
        }
    }

    @Transactional
    @LoggedInvocation
    public String createProperty(){
        propertyToCreate.setOwner(owner);
        this.propertiesDAO.persist(propertyToCreate);
        return "ownerDetails?ownerId=" + owner.getId() + "&faces-redirect=true";
    }

    private void loadAllProperties(){
        this.allProperties = propertiesDAO.loadAll();
    }
}
