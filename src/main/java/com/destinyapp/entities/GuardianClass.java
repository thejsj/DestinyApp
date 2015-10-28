package com.destinyapp.entities;

import javax.persistence.*;
import javax.persistence.NamedQueries;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
/**
 * Created by franzsilv1 on 10/19/2015.
 */

@Entity
@Table(name="Class")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Class.findAll", query = "SELECT c FROM GuardianClass c"),
        @NamedQuery(name = "Class.findById", query = "SELECT c FROM GuardianClass c WHERE c.classId = :ClassId"),
        @NamedQuery(name = "Class.findByName", query = "SELECT c FROM GuardianClass c WHERE c.className = :ClassName")
})
public class GuardianClass implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CLASSID")
    private Integer classId;

    @Basic(optional = false)
    @NotNull
    @Column(name = "CLASSNAME")
    private String className;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "IMAGELINK")
    private String imageLink;

    public GuardianClass() {}

    public GuardianClass(Integer classId, String className) {
        this.setClassId(classId);
        this.setClassName(className);
    }


    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }
}
