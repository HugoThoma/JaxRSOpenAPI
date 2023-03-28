package metier;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="Inheritance")
@XmlRootElement(name = "personne")
public abstract class Personne {
    private Long id_member;

    private String name;



    public Personne() {}

    public Personne(String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @XmlElement(name = "id")
    public Long getId_member() {
        return id_member;
    }

    public void setId_member(Long id) {
        this.id_member = id;
    }
    @XmlElement(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return "Employee [id=" + id_member + ", name=" + name+ "]";
    }

}
