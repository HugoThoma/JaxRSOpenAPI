package fr.istic.taa.jaxrs.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

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
