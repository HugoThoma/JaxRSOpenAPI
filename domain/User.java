package metier;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@DiscriminatorValue("User")
@XmlRootElement(name = "user")
public class User extends Personne{

    private List<Fiche> tickets = new ArrayList<Fiche>();
    public User() {
        super();
    }

    public User(String name) {
        super(name);
    }
    /*@OneToMany(targetEntity = Fiche.class, mappedBy = "user")
    public List<Fiche> getTicket() {
        return tickets;
    }

    public void setTicket(List<Fiche> tickets) {
        this.tickets = tickets;
    }*/



}
