package lt.vu.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery(name = "Property.findAll", query = "select p from Property as p"),
        @NamedQuery(name = "Property.findByOwnerId", query = "SELECT p FROM Property p WHERE p.owner.id = :ownerId"),
        @NamedQuery(name = "Property.findByAgentId", query = "SELECT p FROM Property p JOIN p.agents a WHERE a.id = :agentId")
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

    @Column(name = "TAXES_ID_NO")
    private Integer taxesIdNo;

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
