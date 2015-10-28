package com.destinyapp.models;

import com.destinyapp.entities.Burn;
import org.springframework.stereotype.Service;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by franzsilv1 on 10/19/2015.
 */
@Component
@Service("burnService")
public class BurnModel {

    @PersistenceContext(unitName = "DestinyAppPU")
    private EntityManager em;

    @Transactional
    public void create (Burn burn) {
        em.persist(burn);
    }

    public List<Burn> findAllBurns() {
        List<Burn> burnList = new ArrayList<Burn>();

        try {
            Query q = em.createNamedQuery("Burn.findAll");
            burnList = q.getResultList();
        } catch (Exception ex) {
            System.out.println("ERROR: " + ex);
        }
        return burnList;
    }

    public Burn findBurnById(int burnId) {
        List<Burn> burnList = new ArrayList<Burn>();

        try {
            Query q = em.createNamedQuery("Burn.findById");
            q.setParameter("BurnId", burnId);

            burnList = q.getResultList();
        } catch (Exception e) {
            System.out.println("ERROR::: " + e);
        }

        return burnList.get(0);
    }

    public Burn findBurnByName(String burnName) {
        List<Burn> burnList = new ArrayList<Burn>();

        try {
            Query q = em.createNamedQuery("Burn.findByName");
            q.setParameter("BurnName", burnName);

            burnList = q.getResultList();
        } catch (Exception e) {
            System.out.println("ERROR::: " + e);
        }

        return burnList.get(0);
    }
}
