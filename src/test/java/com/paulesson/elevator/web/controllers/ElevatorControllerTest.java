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

    private List<Elevator> getNewMockListElevator(){
        List<Elevator> elevatorList = new ArrayList<Elevator>();
        
        elevatorList.add(new Elevator("A"));
        elevatorList.add(new Elevator("B"));
        elevatorList.add(new Elevator("C"));
        elevatorList.add(new Elevator("D"));
        
        return elevatorList;
    }
   
    private List<com.paulesson.elevator.web.model.Elevator> getModelElevatorList(){
        ArrayList<com.paulesson.elevator.web.model.Elevator> elevatorListModel = new ArrayList<com.paulesson.elevator.web.model.Elevator>();
        for(Elevator e: elevatorList){
            elevatorListModel.add(new com.paulesson.elevator.web.model.Elevator(e));
        }
        return elevatorListModel;
    }
    
    /**
     * Test of showElevators method, of class ElevatorController.
     */
    @Test
    public void testShowElevators() {
      List<com.paulesson.elevator.web.model.Elevator> expectedModel = getModelElevatorList();
        List<com.paulesson.elevator.web.model.Elevator> resultModel;
        ElevatorController instance = new ElevatorController();
        instance.setElevatorCommandRouter(ecr);
        ModelAndView expResult = new ModelAndView(ElevatorController.SHOW_ELEVATORS_PAGE);
        expResult.addObject(ElevatorController.MODEL_ELEVATORS_LIST_NAME, expectedModel);
        ModelAndView result = instance.showElevators();
        assertEquals(expResult.getViewName(), result.getViewName());
        Map<String,Object> model = result.getModel();
        resultModel = (List<com.paulesson.elevator.web.model.Elevator>)model.get(ElevatorController.MODEL_ELEVATORS_LIST_NAME);
        
        for( int i =0; i < expectedModel.size(); i ++ ){
            assertEquals(expectedModel.get(i), resultModel.get(i));
        }
    }
    
}
