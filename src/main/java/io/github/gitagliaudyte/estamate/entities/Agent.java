package io.github.gitagliaudyte.estamate.entities;

import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery(name = "Agent.findAll", query = "select a from Agent as a")
})
@Table(name = "AGENT")
@Getter @Setter
public class Agent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Size(max = 50)
    @Column(name = "NAME")
    private String name;

    @Column(name = "PHONE")
    private String phoneNumber;

    @ManyToMany(mappedBy = "agents")
    private List<Property> properties = new ArrayList<>();

    public Agent() {}

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}

