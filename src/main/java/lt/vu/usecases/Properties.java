package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Owner;
import lt.vu.entities.Property;
import lt.vu.interceptors.LoggedInvocation;
import lt.vu.persistence.OwnersDAO;
import lt.vu.persistence.PropertiesDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
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
