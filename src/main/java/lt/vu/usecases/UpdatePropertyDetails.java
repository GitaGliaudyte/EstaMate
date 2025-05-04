package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Property;
import lt.vu.interceptors.LoggedInvocation;
import lt.vu.persistence.PropertiesDAO;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Map;

@ViewScoped
@Named
@Getter @Setter
public class UpdatePropertyDetails implements Serializable {
    private Property property;

    @Inject
    private PropertiesDAO propertiesDAO;

    @PostConstruct
    private void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer propertyId =  Integer.parseInt(requestParameters.get("propertyId"));
        this.property = propertiesDAO.findById(propertyId);
    }

    @Transactional
    @LoggedInvocation
    public String updateProperty() throws InterruptedException {
        try {
            propertiesDAO.update(this.property);
        } catch (OptimisticLockException e) {
            return "/propertyDetails.xhtml?faces-redirect=true&propertyId=" + this.property.getId() + "&error=optimistic-lock-exception";
        }
        return "/propertyDetails.xhtml?faces-redirect=true&propertyId=" + this.property.getId();
    }

}
