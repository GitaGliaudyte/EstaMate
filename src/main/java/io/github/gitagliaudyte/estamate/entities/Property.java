package io.github.gitagliaudyte.estamate.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery(name = "Property.findAll", query = "select p from Property as p")
})
@Table(name = "PROPERTY")
@Getter @Setter
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "PRICE")
    private Double price;

    @ManyToOne
    private Owner owner;

    @ManyToMany
    @JoinTable(
            name = "AGENT_PROPERTY",
            joinColumns = @JoinColumn(name = "PROPERTY_ID"),
            inverseJoinColumns = @JoinColumn(name = "AGENT_ID")
    )
    private List<Agent> agents = new ArrayList<>();

    public Property() {}

    @Override
    public int hashCode() {
        return Objects.hash(address);
    }
}
