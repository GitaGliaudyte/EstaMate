package io.github.gitagliaudyte.estamate.usecases;

import io.github.gitagliaudyte.estamate.entities.Owner;
import io.github.gitagliaudyte.estamate.persistence.OwnersDAO;
import io.github.gitagliaudyte.estamate.persistence.PropertiesDAO;
import lombok.Getter;
import lombok.Setter;

import javax.faces.context.FacesContext;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.annotation.PostConstruct;
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
        ownersAgents.loadAgentsForOwner(owner);
    }

}
