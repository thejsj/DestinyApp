package com.destinyapp.entities;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * Created by franzsilv1 on 10/19/2015.
 */

@Entity
@Table(name="Burn")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Burn.findAll", query = "SELECT b from Burn b"),
        @NamedQuery(name = "Burn.findById", query = "SELECT b FROM Burn b WHERE b.burnId = :BurnId"),
        @NamedQuery(name = "Burn.findByName", query = "SELECT b FROM Burn b WHERE b.burnName = :BurnName")
})

public class Burn implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "BURNID")
    private Integer burnId;

    @Basic(optional = false)
    @NotNull
    @Column(name = "BURNNAME")
    private String burnName;

    @Column(name = "IMAGELINK")
    private String imageLink;

    public Burn() {}

    public Burn(Integer burnId, String burnName) {
        this.setBurnId(burnId);
        this.setBurnName(burnName);
    }


    public Integer getBurnId() {
        return burnId;
    }

    public void setBurnId(Integer burnId) {
        this.burnId = burnId;
    }

    public String getBurnName() {
        return burnName;
    }

    public void setBurnName(String burnName) {
        this.burnName = burnName;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }
}
