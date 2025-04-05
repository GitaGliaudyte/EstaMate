package io.github.gitagliaudyte.estamate.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@NamedQueries({
        @NamedQuery(name = "Property.findAll", query = "select p from Property as p")
})
@Table(name = "PROPERTY")
@Getter @Setter
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String address;
    private Double price;

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private Owner owner;

    @ManyToMany
    @JoinTable(
            name = "property_agents",
            joinColumns = @JoinColumn(name = "property_id"),
            inverseJoinColumns = @JoinColumn(name = "agent_id")
    )
    private Set<Agent> agents = new HashSet<>();

    public Property() {}

    @Override
    public int hashCode() {
        return Objects.hash(address);
    }
}
