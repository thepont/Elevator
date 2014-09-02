/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.paulesson.elevate.mvc.controllers;

import com.paulesson.elevator.ElevatorCommandRouter;
import com.paulesson.elevator.RESTModel.*;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Paul Esson
 */
@Controller
@RequestMapping("/api/elevators/")
public class ElevatorRESTController {
    
    @Autowired
    private ElevatorCommandRouter ecr;
    /**
     * Get the list of elevators and their current locations.
     * @return list of current elevator status.
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public @ResponseBody List<Elevator> getElevators()
    {
        List<Elevator> elevators = new ArrayList<Elevator>();
        elevators.add(new Elevator());
        return elevators;
    }
    
    /**
     * Request an elevator to come to a specific floor for a group of people
     * @param cmd command to request
     * @return true on success
     */
    @RequestMapping(value = "/command", method = RequestMethod.POST)
    public @ResponseBody Boolean getElevators(Command cmd)
    {
        List<Elevator> elevators = new ArrayList<Elevator>();
        elevators.add(new Elevator());
        return true;
    }
    
}
