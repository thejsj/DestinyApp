package com.destinyapp.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@Entity
@Table(name = "Subclass")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Subclass.findAll", query = "SELECT sc FROM Subclass sc"),
        @NamedQuery(name = "Subclass.findById", query = "SELECT sc FROM Subclass sc WHERE sc.subclassId = :SubclassId"),
        @NamedQuery(name = "Subclass.findByName", query = "SELECT sc FROM Subclass sc WHERE sc.subclassName = :SubclassName")
})
public class Subclass implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "SUBCLASSID")
    private Integer subclassId;

    @Basic(optional = false)
    @NotNull
    @Column(name = "SUBCLASSNAME")
    private String subclassName;

    @Column(name = "DESCRIPTION")
    private String description;

    @JoinColumn(name = "CLASSID", referencedColumnName = "CLASSID")
    @ManyToOne
    private GuardianClass guardianClass;

    @JoinColumn(name = "BURNID", referencedColumnName = "BURNID")
    @ManyToOne
    private Burn burn;

    @Column(name = "IMAGELINK")
    private String imageLink;

    public Subclass () {}

    public Subclass (Integer subclassId, String subclassName, String description, GuardianClass guardianClass, Burn burn) {
        this.setSubclassId(subclassId);
        this.setSubclassName(subclassName);
        this.setDescription(description);
        this.setGuardianClass(guardianClass);
        this.setBurn(burn);
    }


    public Integer getSubclassId() {
        return subclassId;
    }

    public void setSubclassId(Integer subclassId) {
        this.subclassId = subclassId;
    }

    public String getSubclassName() {
        return subclassName;
    }

    public void setSubclassName(String subclassName) {
        this.subclassName = subclassName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public GuardianClass getGuardianClass() {
        return guardianClass;
    }

    public void setGuardianClass(GuardianClass guardianClass) {
        this.guardianClass = guardianClass;
    }

    public Burn getBurn() {
        return burn;
    }

    public void setBurn(Burn burn) {
        this.burn = burn;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }
}
