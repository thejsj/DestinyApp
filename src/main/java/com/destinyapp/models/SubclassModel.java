package com.destinyapp.models;

import com.destinyapp.entities.Subclass;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by franzsilv1 on 10/20/2015.
 */
@Component
@Service("subclassService")
public class SubclassModel {
    @PersistenceContext(unitName = "DestinyAppPU")
    private EntityManager em;

    @Transactional
    public void create (Subclass subclass) {
        em.persist(subclass);
    }

    public List<Subclass> findAllSubclasses() {
        List<Subclass> subclassList = new ArrayList<Subclass>();

        try {
            Query q = em.createNamedQuery("Subclass.findAll");
            subclassList = q.getResultList();
        } catch (Exception ex) {
            System.out.println("ERROR:: " + ex);
        }
        return subclassList;
    }

    public Subclass findSubclassById(Integer subclassId) {
        List<Subclass> subclassList = new ArrayList<Subclass>();

        try {
            Query q = em.createNamedQuery("Subclass.findById");
            q.setParameter("SubclassId", subclassId);
            subclassList = q.getResultList();
        } catch (Exception ex) {
            System.out.println("ERROR:: " + ex);
        }
        return subclassList.get(0);
    }

    public Subclass findSubclassByName(String subclassName) {
        List<Subclass> subclassList = new ArrayList<Subclass>();

        try {
            Query q = em.createNamedQuery("Subclass.findByName");
            q.setParameter("SubclassName", subclassName);
            subclassList = q.getResultList();
        } catch (Exception ex) {
            System.out.println("ERROR:: " + ex);
        }
        return subclassList.get(0);
    }
}
