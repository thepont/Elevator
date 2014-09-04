/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.paulesson.elevator.web.controllers;

import com.paulesson.elevator.web.model.Elevator;
import com.paulesson.elevator.web.model.Command;
import com.paulesson.elevator.elevatorcontrol.ElevatorCommandRouter;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Paul Esson
 */
@Controller
@RequestMapping("/api/elevators/")
public class ElevatorRESTController extends ElevatorController{
    
    @Autowired
    public void setElevatorCommandRouter(ElevatorCommandRouter ecr) {
        this.ecr = ecr;
    }
    
    /**
     * Get the list of elevators and their current locations.
     * @return list of current elevator status.
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public @ResponseBody List<Elevator> getElevators()
    {
       return getRESTElevators();
    }
    
    /**
     * Request an elevator to come to a specific floor for a group of people
     * @param cmd command to request
     * @return true on success
     */
    @RequestMapping(value = "/command", method = RequestMethod.POST)
    public @ResponseBody Boolean sendCommand(@RequestBody final Command cmd)
    {
        ecr.queueCommand(cmd.toRequestCommand());
        return true;
    }
    
}
