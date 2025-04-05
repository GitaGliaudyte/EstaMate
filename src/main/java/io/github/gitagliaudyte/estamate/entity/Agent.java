package io.github.gitagliaudyte.estamate.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "agents")
public class Agent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String phoneNumber;

    @ManyToMany(mappedBy = "agents")
    private Set<Property> properties = new HashSet<>();

    // Getters and Setters
}

