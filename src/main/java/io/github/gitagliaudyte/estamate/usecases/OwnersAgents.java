package io.github.gitagliaudyte.estamate.usecases;

import io.github.gitagliaudyte.estamate.entities.Agent;
import io.github.gitagliaudyte.estamate.entities.Owner;
import io.github.gitagliaudyte.estamate.entities.Property;
import io.github.gitagliaudyte.estamate.persistence.AgentsDAO;
import io.github.gitagliaudyte.estamate.persistence.PropertiesDAO;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
@Named
public class OwnersAgents implements Serializable {

    @Getter @Setter
    private List<Agent> agents;

    @PostConstruct
    public void init() {
        agents = new ArrayList<>();

    }

    public void loadAgentsForOwner(Owner owner) {
        List<Property> properties = owner.getProperties();

        agents.clear();
        for (Property property : properties) {
            agents.addAll(property.getAgents());
        }
    }
}
