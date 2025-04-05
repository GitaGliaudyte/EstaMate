package io.github.gitagliaudyte.estamate.entities;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@NamedQueries({
        @NamedQuery(name = "Agent.findAll", query = "select a from Agent as a")
})
@Table(name = "AGENT")
@Getter @Setter
public class Agent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(max = 50)
    @Column(name = "NAME")
    private String name;

    @Column(name = "PHONE")
    private String phoneNumber;

    @ManyToMany(mappedBy = "agents")
    private Set<Property> properties = new HashSet<>();

    @Version
    @Column(name = "OPT_LOCK_VERSION")
    private Integer version;

    public Agent() {}

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}

