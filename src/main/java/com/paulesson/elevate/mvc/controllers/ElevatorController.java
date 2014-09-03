package com.paulesson.elevate.mvc.controllers;

import com.paulesson.elevator.ElevatorCommandRouter;
import com.paulesson.elevator.RESTModel.Elevator;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Paul Esson
 */



@Controller
@RequestMapping("/elevator")
public class ElevatorController {
    
    @Autowired
    private ElevatorCommandRouter ecr;
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView showElevators(){
        ArrayList<Elevator> elevators = new ArrayList<Elevator>();
        for(com.paulesson.elevator.Elevator el : ecr.getElevators()){
            elevators.add(new Elevator(el));
        }
        ModelAndView model = new ModelAndView("index");
        model.addObject("elevators", elevators);
        return model;
    }
}
