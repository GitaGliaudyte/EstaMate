package lt.vu.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery(name = "Owner.findAll", query = "select o from Owner as o")
})
@Table(name = "OWNER")
@Getter @Setter
public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Size(max = 50)
    @Column(name = "NAME")
    private String name;

    @OneToMany(mappedBy = "owner")
    private List<Property> properties = new ArrayList<>();

    public Owner() {}

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}

