package com.destinyapp.controllers;

import com.destinyapp.entities.Burn;
import com.destinyapp.entities.GuardianClass;
import com.destinyapp.entities.Subclass;
import com.destinyapp.models.BurnModel;
import com.destinyapp.models.ClassModel;
import com.destinyapp.models.SubclassModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by franzsilv1 on 10/19/2015.
 */
@Controller
public class MainController {

    @Resource(name = "burnService")
    private BurnModel burnModel;

    @Resource(name = "classService")
    private ClassModel classModel;

    @Resource(name = "subclassService")
    private SubclassModel subclassModel;

    private List<Burn> burnList = null;
    private List<GuardianClass> classList = null;
    private List<Subclass> subclassList = null;

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
    public String getSubclassPage(ModelMap model) {

        return "Subclass";
    }

}
