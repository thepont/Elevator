/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.paulesson.elevator.web.controllers;

import com.paulesson.elevator.web.model.Elevator;
import java.util.ArrayList;
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
public class ElevatorJSPController extends ElevatorController{
    
    protected static final String SHOW_ELEVATORS_PAGE = "index";
    protected static final String MODEL_ELEVATORS_LIST_NAME = "elevators";
    
        
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView showElevators(){
        ModelAndView model = new ModelAndView(SHOW_ELEVATORS_PAGE);
        model.addObject(MODEL_ELEVATORS_LIST_NAME, getRESTElevators());
        return model;
    }
    
}
