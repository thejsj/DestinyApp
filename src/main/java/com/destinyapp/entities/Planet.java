package com.destinyapp.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * Created by franzsilv1 on 10/29/2015.
 */

@Entity
@Table(name = "Planet")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Planet.findAll", query = "SELECT p from Planet p"),
        @NamedQuery(name = "Planet.findById", query = "SELECT p from Planet p WHERE p.planetId = :PlanetId")
})
public class Planet implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PLANETID")
    private Integer planetId;

    @Basic(optional = false)
    @NotNull
    @Column(name = "PLANETNAME")
    private String planetName;

    @Column(name = "IMAGELINK")
    private String imageLink;

    @Column(name = "MAPLINK")
    private String mapLink;

    public Planet() {}


    public Integer getPlanetId() {
        return planetId;
    }

    public void setPlanetId(Integer planetId) {
        this.planetId = planetId;
    }

    public String getPlanetName() {
        return planetName;
    }

    public void setPlanetName(String planetName) {
        this.planetName = planetName;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public String getMapLink() {
        return mapLink;
    }

    public void setMapLink(String mapLink) {
        this.mapLink = mapLink;
    }
}
