package metier;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.sql.Date;
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
