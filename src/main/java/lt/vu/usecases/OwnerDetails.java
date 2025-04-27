package lt.vu.usecases;

import lt.vu.entities.Owner;
import lt.vu.persistence.OwnersDAO;
import lt.vu.persistence.PropertiesDAO;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.Map;

@Named
@RequestScoped
public class OwnerDetails {

    @Getter
    @Inject
    private OwnersAgents ownersAgents;

    @Inject
    private OwnersDAO ownersDAO;

    @Inject
    private PropertiesDAO propertiesDAO;

    @Getter @Setter
    private Owner owner;

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer ownerId = Integer.parseInt(requestParameters.get("ownerId"));

        owner = ownersDAO.findById(ownerId);
        if (!owner.getProperties().isEmpty()) {
            owner.setProperties(propertiesDAO.findByOwnerId(ownerId));
        } else {
            owner.setProperties(new ArrayList<>());
        }
    }


    public String ownerInformation() {
        return owner.getName() + "'s information";
    }
}
