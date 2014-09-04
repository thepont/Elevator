package com.paulesson.elevator.web.controllers;

import com.paulesson.elevator.elevatorcontrol.ElevatorCommandRouter;
import com.paulesson.elevator.web.model.Elevator;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Paul Esson
 */

public class ElevatorController {

    
   protected ElevatorCommandRouter ecr;
    

    /**
     * Gets elevators from ElevatorCommandRouter and returns rest model of the elevators.
     * @return list of elevators.
     */
    protected List<Elevator> getRESTElevators(){
        ArrayList<Elevator> elevators = new ArrayList<Elevator>();
        for(com.paulesson.elevator.elevatorcontrol.Elevator el : ecr.getElevators()){
            elevators.add(new Elevator(el));
        }
        return elevators;
    }
    
    

}
