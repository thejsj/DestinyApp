package com.destinyapp.controllers;

import com.destinyapp.entities.*;
import com.destinyapp.models.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by franzsilv1 on 10/19/2015.asdf
 */
@Controller
public class MainController {

    @Resource(name = "burnService")
    private BurnModel burnModel;

    @Resource(name = "classService")
    private ClassModel classModel;

    @Resource(name = "subclassService")
    private SubclassModel subclassModel;

    @Resource(name = "abilityService")
    private AbilityModel abilityModel;

    @Resource(name = "planetService")
    private PlanetModel planetModel;



    private List<Burn> burnList = null;
    private List<GuardianClass> classList = null;
    private List<Subclass> subclassList = null;
    private List<Planet> planetList = null;

    private HashMap<Integer, List<Ability>> abilityMap = new HashMap<>();

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getIndex(ModelMap model) {

        return "index";
    }

    @RequestMapping(value = "/class", method = RequestMethod.GET)
    public String getClassPage(ModelMap model) {

        if (burnList == null) {
            burnList = burnModel.findAllBurns();
        }
        if (classList == null) {
            classList = classModel.findAllClasses();
        }
        if (subclassList == null) {
            subclassList = subclassModel.findAllSubclasses();
        }

        model.put("burnList", burnList);
        model.put("classList", classList);
        model.put("subclassList", subclassList);

        return "Classes";
    }

    @RequestMapping(value = "/subclass", method = RequestMethod.GET)
    public String getSubclassPage(@RequestParam(value = "sc", required = true) Integer subclassId, ModelMap model) {

        List<Ability> abilityList = null;
        if (!abilityMap.containsKey(subclassId)) {
            abilityList = abilityModel.findBySubclassId(subclassId);
            abilityMap.put(subclassId, abilityList);
        } else {
            abilityList = abilityMap.get(subclassId);
        }

        Subclass currSubclass = subclassModel.findSubclassById(subclassId);

        ArrayList<Ability> firstColumn = new ArrayList<>();
        ArrayList<Ability> secondColumn = new ArrayList<>();
        ArrayList<Ability> thirdColumn = new ArrayList<>();
        ArrayList<Ability> fourthColumn = new ArrayList<>();

        for (Ability ab : abilityList) {
            Integer column = ab.getRow();
            if (column == 1) {
                firstColumn.add(ab);
            } else if (column == 2) {
                secondColumn.add(ab);
            } else if (column == 3) {
                thirdColumn.add(ab);
            } else {
                fourthColumn.add(ab);
            }
        }

        model.put("subclassObj", currSubclass);

        model.put("firstCol", firstColumn);
        model.put("secondCol", secondColumn);
        model.put("thirdCol", thirdColumn);
        model.put("fourthCol", fourthColumn);

        model.put("iconList1", setupAbilityIcons(1));
        model.put("iconList2", setupAbilityIcons(4));

        return "Subclass";
    }

    @RequestMapping(value = "/location", method = RequestMethod.GET)
    public String getLocationPage(ModelMap model) {

        if (planetList == null) {
            planetList = planetModel.findAllPlanets();
        }

        model.put("planetList", planetList);

        return "Locations";
    }

    private ArrayList<String> setupAbilityIcons(Integer rowNumber) {
        ArrayList<String> iconList = new ArrayList<>();

        if (rowNumber == 1 || rowNumber == 2 || rowNumber == 3) {
            iconList.add("../../Images/grenade.png");
            iconList.add("../../Images/jump.png");
            iconList.add("../../Images/super.png");
            iconList.add("../../Images/melee.png");
            iconList.add("../../Images/plus.png");
            iconList.add("../../Images/star.png");
            iconList.add("../../Images/plus.png");
            iconList.add("../../Images/star.png");
        } else {
            iconList.add("../../Images/jump.png");
            iconList.add("../../Images/super.png");
            iconList.add("../../Images/melee.png");
        }

        return iconList;
    }
}
