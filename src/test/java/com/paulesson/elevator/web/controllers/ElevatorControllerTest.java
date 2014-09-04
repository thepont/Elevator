/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.paulesson.elevator.web.controllers;

import com.paulesson.elevator.elevatorcontrol.Elevator;
import com.paulesson.elevator.elevatorcontrol.ElevatorCommandRouter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import static org.junit.Assert.*;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author pont
 */
public class ElevatorControllerTest {
    
    ElevatorCommandRouter ecr;
    List<Elevator> elevatorList;
    
    public ElevatorControllerTest() {
        elevatorList =getNewMockListElevator();
        ecr = new ElevatorCommandRouter(getNewMockListElevator());
    }

    protected List<Elevator> getNewMockListElevator(){
        List<Elevator> elevatorList = new ArrayList<Elevator>();
        
        elevatorList.add(new Elevator("A"));
        elevatorList.add(new Elevator("B"));
        elevatorList.add(new Elevator("C"));
        elevatorList.add(new Elevator("D"));
        
        return elevatorList;
    }
   
    protected List<com.paulesson.elevator.web.model.Elevator> getModelElevatorList(){
        ArrayList<com.paulesson.elevator.web.model.Elevator> elevatorListModel = new ArrayList<com.paulesson.elevator.web.model.Elevator>();
        for(Elevator e: elevatorList){
            elevatorListModel.add(new com.paulesson.elevator.web.model.Elevator(e));
        }
        return elevatorListModel;
    }
    
  
    
}
