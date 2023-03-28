package metier;

import javax.persistence.*;

@Entity
@DiscriminatorValue("FeratureRequest")
public class FeatureRequestFiche extends Fiche{
    public FeatureRequestFiche() {
    }

    public FeatureRequestFiche(String name, User user) {
        super(name,user);
    }

    public FeatureRequestFiche(String name) {
        super(name);
    }

}
