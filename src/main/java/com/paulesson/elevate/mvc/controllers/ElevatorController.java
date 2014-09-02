package com.paulesson.elevate.mvc.controllers;

import com.paulesson.elevator.ElevatorCommandRouter;
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
        //load list of elevators.
        //List<com.paulesson.elevator.Elevator> elevators = new ArrayList<com.paulesson.elevator.Elevator>();
        ModelAndView model = new ModelAndView("index", "elevatorlist", "bla");
        return model;
    }
}
