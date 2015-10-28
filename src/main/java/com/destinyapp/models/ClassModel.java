package com.destinyapp.models;

import com.destinyapp.entities.GuardianClass;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by franzsilv1 on 10/19/2015.
 */
@Component
@Service("classService")
public class ClassModel {

    @PersistenceContext(unitName = "DestinyAppPU")
    private EntityManager em;

    @Transactional
    public void create (GuardianClass gclass) {
        em.persist(gclass);
    }

    public List<GuardianClass> findAllClasses() {
        List<GuardianClass> classList = new ArrayList<GuardianClass>();

        try {
            Query q = em.createNamedQuery("Class.findAll");
            classList = q.getResultList();
        } catch (Exception ex) {
            System.out.println("ERROR: " + ex);
        }
        return classList;
    }

    public GuardianClass findClassById(int classId) {
        List<GuardianClass> classList = new ArrayList<GuardianClass>();

        try {
            Query q = em.createNamedQuery("Class.findById");
            q.setParameter("ClassId", classId);

            classList = q.getResultList();
        } catch (Exception e) {
            System.out.println("ERROR::: " + e);
        }

        return classList.get(0);
    }

    public GuardianClass findClassByName(String className) {
        List<GuardianClass> classList = new ArrayList<GuardianClass>();

        try {
            Query q = em.createNamedQuery("Class.findByName");
            q.setParameter("ClassName", className);

            classList = q.getResultList();
        } catch (Exception ex) {
            System.out.println("ERROR:: " + ex);
        }
        return classList.get(0);
    }
}
