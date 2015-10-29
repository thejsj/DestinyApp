package com.destinyapp.models;

import com.destinyapp.entities.Ability;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by franzsilv1 on 10/28/2015.
 */
@Component
@Service("abilityService")
public class AbilityModel {

    @PersistenceContext(unitName = "DestinyAppPU")
    private EntityManager em;

    @Transactional
    public void create (Ability ability) {
        em.persist(ability);
    }

    public List<Ability> findAllAbilities() {
        List<Ability> abilityList = new ArrayList<>();

        try {
            Query q = em.createNamedQuery("Ability.findAll");
            abilityList = q.getResultList();
        } catch (Exception ex) {
            System.out.println("ERROR:: " + ex);
        }
        return abilityList;
    }

    public Ability findAbilityById(Integer abilityId) {

        List<Ability> abilityList = new ArrayList<>();

        try {
            Query q = em.createNamedQuery("Ability.findById");
            q.setParameter("AbilityId", abilityId);
            abilityList = q.getResultList();
        } catch (Exception ex) {
            System.out.println("ERROR:: " + ex);
        }
        return abilityList.get(0);
    }

    public List<Ability> findBySubclassId(Integer subclassId) {
        List<Ability> abilityList = new ArrayList<>();

        try {
            Query q = em.createNamedQuery("Ability.findBySubclassId");
            q.setParameter("SubclassId", subclassId);
            abilityList = q.getResultList();
        } catch (Exception ex) {
            System.out.println("ERROR:: " + ex);
        }
        return abilityList;
    }
}
