package com.destinyapp.models;

import com.destinyapp.entities.Planet;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by franzsilv1 on 10/29/2015.
 */
@Component
@Service("planetService")
public class PlanetModel {
    @PersistenceContext(unitName = "DestinyAppPU")
    private EntityManager em;

    @Transactional
    public void create (Planet planet) {
        em.persist(planet);
    }

    public List<Planet> findAllPlanets() {
        List<Planet> planetList = new ArrayList<>();

        try {
            Query q = em.createNamedQuery("Planet.findAll");
            planetList = q.getResultList();
        } catch (Exception ex) {
            System.out.println("ERROR:: " + ex);
        }
        return planetList;
    }

    public Planet findPlanetById(Integer planetId) {
        List<Planet> planetList = new ArrayList<>();

        try {
            Query q = em.createNamedQuery("Planet.findById");
            q.setParameter("PlanetId", planetId);
            planetList = q.getResultList();
        } catch (Exception ex) {
            System.out.println("ERROR:: " + ex);
        }
        return planetList.get(0);
    }
}
