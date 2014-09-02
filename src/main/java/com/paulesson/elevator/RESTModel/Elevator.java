/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.paulesson.elevator.RESTModel;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Paul Esson
 */
@XmlRootElement(name = "Elevator")
public class Elevator {
    private Direction direction;
    private short currentFloor;
    private byte numPeople;

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
    
    public short getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(short currentFloor) {
        this.currentFloor = currentFloor;
    }

    public byte getNumPeople() {
        return numPeople;
    }

    public void setNumPeople(byte numPeople) {
        this.numPeople = numPeople;
    }
}
