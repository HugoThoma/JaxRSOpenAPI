package fr.istic.taa.jaxrs.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@DiscriminatorValue("Bug")
@XmlRootElement(name = "bug")
public class BugFiche extends Fiche {
    public BugFiche() {
    }

    public BugFiche(String name, User user) {
        super(name,user);
    }

    public BugFiche(String name) {
        super(name);
    }


}
