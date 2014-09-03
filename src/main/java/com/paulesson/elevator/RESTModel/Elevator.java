/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.paulesson.elevator.RESTModel;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Paul Esson
 */
@XmlRootElement(name = "Elevator")
public class Elevator implements Serializable{
    

    
    public Elevator(com.paulesson.elevator.Elevator from){
        this.direction = Direction.fromControllerDirection(from.getDirection());
        this.currentFloor = from.getCurrentFloor();
        this.numPeople = from.getLoad();
        this.name = from.getName();
    }
    
    public Elevator(){}
    
    private Direction direction;
    private int currentFloor;
    private int numPeople;
    private String name;

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
    
    public int getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

    public int getNumPeople() {
        return numPeople;
    }

    public void setNumPeople(int numPeople) {
        this.numPeople = numPeople;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}

