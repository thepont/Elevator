/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.paulesson.elevator.web.controllers;

import java.util.List;
import java.util.Map;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author pont
 */
public class ElevatorJSPControllerTest extends ElevatorControllerTest {
    
    public ElevatorJSPControllerTest() {
        super();
    }

  /**
     * Test of showElevators method, of class ElevatorController.
     */
    @Test
    public void testShowElevators() {
      List<com.paulesson.elevator.web.model.Elevator> expectedModel = getModelElevatorList();
        List<com.paulesson.elevator.web.model.Elevator> resultModel;
        ElevatorJSPController instance = new ElevatorJSPController();
        instance.setElevatorCommandRouter(ecr);
        ModelAndView expResult = new ModelAndView(ElevatorJSPController.SHOW_ELEVATORS_PAGE);
        expResult.addObject(ElevatorJSPController.MODEL_ELEVATORS_LIST_NAME, expectedModel);
        ModelAndView result = instance.showElevators();
        assertEquals(expResult.getViewName(), result.getViewName());
        Map<String,Object> model = result.getModel();
        resultModel = (List<com.paulesson.elevator.web.model.Elevator>)model.get(ElevatorJSPController.MODEL_ELEVATORS_LIST_NAME);
        
        for( int i =0; i < expectedModel.size(); i ++ ){
            assertEquals(expectedModel.get(i), resultModel.get(i));
        }
    }
    
}
