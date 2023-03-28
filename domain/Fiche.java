package metier;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.sql.Date;
import java.time.LocalDate;

@Entity
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="Inheritance")
@XmlRootElement(name = "fiche")
public  abstract class  Fiche {

    private Long id;
    private String title;
    private String description;
    private Date dateEmission;
    private Date datePriseenCharge;
    private Date dateCloture;
    private SupportMember support;
    private User user;
    private String tag ;


    public Fiche() {
        this.tag = Tags.NEW.toString();
        this.dateEmission = Date.valueOf(LocalDate.now());
    }

    public Fiche(String name, User user) {
        this.title = name;
        this.user = user;
        this.tag = Tags.NEW.toString();
        this.dateEmission = Date.valueOf(LocalDate.now());
    }

    public Fiche(String name) {
        this.title = name;
        this.tag = Tags.NEW.toString();
        this.dateEmission = Date.valueOf(LocalDate.now());
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @XmlElement(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    @XmlElement(name = "titre")
    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
    }

    @ManyToOne
    @XmlElement(name = "user")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Ticket [id=" + id + ", name=" + title + ", user="
                + user.getName() + "]";
    }
    //@OneToMany(mappedBy = "tag", cascade = CascadeType.PERSIST)
    @XmlElement(name = "tag")
    public String getTags() {
        return tag;
    }

    public void setTags(String tag) {this.tag = tag;}
    @ManyToOne
    @XmlElement(name = "support")
    public SupportMember getSupport() {
        return support;
    }

    public void setSupport(SupportMember support) {
        this.support = support;
    }
    @XmlElement(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    @XmlElement(name = "emission")
    public Date getDateEmission() {
        return dateEmission;
    }

    public void setDateEmission(Date dateEmission) {
        this.dateEmission = dateEmission;
    }
    @XmlElement(name = "encharge")
    public Date getDatePriseenCharge() {
        return datePriseenCharge;
    }

    public void setDatePriseenCharge(Date datePriseenCharge) {
        this.datePriseenCharge = datePriseenCharge;
    }
    @XmlElement(name = "cloture")
    public Date getDateCloture() {
        return dateCloture;
    }

    public void setDateCloture(Date dateCloture) {
        this.dateCloture = dateCloture;
    }

}

