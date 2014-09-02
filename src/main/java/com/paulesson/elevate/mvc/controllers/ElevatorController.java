package com.paulesson.elevate.mvc.controllers;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Paul Esson
 */
@Controller
@RequestMapping("/Elevate")
public class ElevatorController {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView showElevators(){
        //load list of elevators.
       // List<com.paulesson.elevator.Elevator> elevators = new ArrayList<com.paulesson.elevator.Elevator>();
        ModelAndView model = new ModelAndView("index", "elevatorlist", "bla");
        return model;
    }
}
