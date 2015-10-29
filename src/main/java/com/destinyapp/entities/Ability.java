package com.destinyapp.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * Created by franzsilv1 on 10/28/2015.
 */
@Entity
@Table(name = "Ability")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Ability.findAll", query = "SELECT a from Ability a"),
        @NamedQuery(name = "Ability.findById", query = "SELECT a from Ability a WHERE a.abilityId = :AbilityId"),
        @NamedQuery(name = "Ability.findBySubclassId", query = "SELECT a from Ability a WHERE a.subclazz.subclassId =:SubclassId")
})
public class Ability implements Serializable {
    private static final long serivalVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ABILITYID")
    private Integer abilityId;

    @Basic(optional = false)
    @NotNull
    @Column(name = "ABILITYNAME")
    private String abilityName;

    @NotNull
    @JoinColumn(name = "SUBCLASSID", referencedColumnName = "SUBCLASSID")
    @ManyToOne
    private Subclass subclazz;

    @Basic(optional = false)
    @NotNull
    @Column(name = "DESCRIPTION")
    private String description;

    @Basic(optional = false)
    @NotNull
    @Column(name = "COLUMNNUMBER")
    private Integer column;

    @Basic(optional = false)
    @NotNull
    @Column(name = "ROWNUMBER")
    private Integer row;

    public Ability() {}

    public Integer getAbilityId() {
        return abilityId;
    }

    public void setAbilityId(Integer abilityId) {
        this.abilityId = abilityId;
    }

    public String getAbilityName() {
        return abilityName;
    }

    public void setAbilityName(String abilityName) {
        this.abilityName = abilityName;
    }

    public Subclass getSubclazz() {
        return subclazz;
    }

    public void setSubclazz(Subclass subclass) {
        this.subclazz = subclass;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getColumn() {
        return column;
    }

    public void setColumn(Integer column) {
        this.column = column;
    }

    public Integer getRow() {
        return row;
    }

    public void setRow(Integer row) {
        this.row = row;
    }
}
