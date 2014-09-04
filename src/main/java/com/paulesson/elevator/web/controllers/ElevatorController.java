package com.paulesson.elevator.web.controllers;

import com.paulesson.elevator.elevatorcontrol.ElevatorCommandRouter;
import com.paulesson.elevator.web.model.Elevator;
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
@RequestMapping("/")
public class ElevatorController {
    protected static final String SHOW_ELEVATORS_PAGE = "index";
    protected static final String MODEL_ELEVATORS_LIST_NAME = "elevators";
    
    private ElevatorCommandRouter ecr;
    
   @Autowired
    void setElevatorCommandRouter(ElevatorCommandRouter ecr){
        this.ecr = ecr;
    }
    
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView showElevators(){
        ArrayList<Elevator> elevators = new ArrayList<Elevator>();
        for(com.paulesson.elevator.elevatorcontrol.Elevator el : ecr.getElevators()){
            elevators.add(new Elevator(el));
        }
        ModelAndView model = new ModelAndView(SHOW_ELEVATORS_PAGE);
        model.addObject(MODEL_ELEVATORS_LIST_NAME, elevators);
        return model;
    }
}
