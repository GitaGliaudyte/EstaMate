package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Agent;
import lt.vu.interceptors.LoggedInvocation;
import lt.vu.persistence.AgentsDAO;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Map;

@LoggedInvocation
@ViewScoped
@Named
@Getter @Setter
public class UpdateAgentDetails implements Serializable {
    private Agent agent;

    @Inject
    private AgentsDAO agentsDAO;

    @PostConstruct
    private void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer agentId =  Integer.parseInt(requestParameters.get("agentId"));
        this.agent = agentsDAO.findById(agentId);
    }

    @Transactional
    @LoggedInvocation
    public String updateAgent() throws InterruptedException {
        try {
            agentsDAO.update(this.agent);
        } catch (OptimisticLockException e) {
            return "/updateAgentDetails.xhtml?faces-redirect=true&agentId=" + this.agent.getId() + "&error=optimistic-lock-exception";
        }
        return "/updateAgentDetails.xhtml?faces-redirect=true&agentId=" + this.agent.getId();
    }
}
