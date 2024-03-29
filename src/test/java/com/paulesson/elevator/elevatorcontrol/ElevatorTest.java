/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.paulesson.elevator.elevatorcontrol;

import com.paulesson.elevator.elevatorcontrol.model.Status;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * Tests the Elevator class in the elevatorcontrol package
 * 
 * @author Paul Esson
 */
public class ElevatorTest {
    
    public ElevatorTest() {
    }

    /**
     * Test of getName method, of class Elevator.
     */
    @Test
    public void testGetName() {
        Elevator instance = new Elevator("A",0);
        String expResult = "A";
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCurrentFloor method, of class Elevator.
     */
    @Test
    public void testGetCurrentFloor() {
        int floor = 3;
        Elevator instance = new Elevator("A",0);
        instance.setCurrentFloor(floor);
        int result = instance.getCurrentFloor();
        assertEquals(floor, result);
    }

    /**
     * Test of get and setLoad method, of class Elevator.
     */
    @Test
    public void testGetSetLoad() {
        int load = 20;
        Elevator instance = new Elevator("A",0);
        instance.setLoad(load);
        int result = instance.getLoad();
        assertEquals(load, result);
    }

    /**
     * Test of get and setAllocatedLoad method, of class Elevator.
     */
    @Test
    public void testGetSetAllocatedLoad() {
        int allocatedLoad = 20;
        Elevator instance = new Elevator("A",0);
        instance.setAllocatedLoad(allocatedLoad);
        int result = instance.getAllocatedLoad();
        assertEquals(allocatedLoad, result);
    }


    /**
     * Test of get and setDirection method, of class Elevator.
     */
    @Test
    public void testGetSetDirection() {
        Status direction = Status.UP;
        Elevator instance = new Elevator("A",0);
        instance.setDirection(direction);
        Status result = instance.getDirection();
        assertEquals(direction, result);
    }

    /**
     * Test of equals method, of class Elevator.
     */
    @Test
    public void testEqualsSuccess() {
        Object obj = new Elevator("A",0);
        Elevator instance = new Elevator((Elevator)obj);
        boolean expResult = true;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of equals method, of class Elevator.
     */
    @Test
    public void testEqualsFail() {
        Object obj = new Elevator("B",0);
        Elevator instance = new Elevator("A",0);
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of moveUpFloor method, of class Elevator.
     */
    @Test
    public void testMoveUpFloor() {
        int amt = 4;
        Elevator instance = new Elevator("A",0);
        instance.setCurrentFloor(amt);
        int expResult = 5;
        int result = instance.moveUpFloor();
        assertEquals(expResult, result);
    }

    /**
     * Test of moveDownFloor method
     */
    @Test
    public void testMoveDownFloor() {
        int amt = 10;
        Elevator instance = new Elevator("A",0);
        instance.setCurrentFloor(amt);
        int expResult = 9;
        int result = instance.moveDownFloor();
        assertEquals(expResult, result);
    }

    /**
     * Test of pickUp method
     */
    @Test
    public void testPickUp() {
        int amt = 20;
        Elevator instance = new Elevator("A",0);
        int expResult = amt;
        int result = instance.pickUp(amt);
        assertEquals(expResult, result);
    }

    /**
     * Test of dropOff method.
     */
    @Test
    public void testDropOff() {
        int amt = 20;
        Elevator instance = new Elevator("A",0);
        int expResult = 0;
        instance.pickUp(amt);
        int result = instance.dropOff(amt);
        assertEquals(expResult, result);
    }

    /**
     * Test of moveTo method, of class Elevator.
     */
    @Test
    public void testMoveTo() {
        int floor = 10, result;
        Elevator instance = new Elevator("A",0);
        instance.moveTo(floor);
        result = instance.getCurrentFloor();
        assertEquals(floor, result);
    }
    
    /**
     * Test of moveTo method, of class Elevator.
     */
    @Test
    public void testMoveToOnInvalidHighFloor() {
        int floor = 50, result;
        int expectedResult = 1;
        Elevator instance = new Elevator("A",0);
        instance.setCurrentFloor(expectedResult);
        instance.moveTo(floor);
        result = instance.getCurrentFloor();
        assertEquals(expectedResult, result);
    }
    
    /**
     * Test of moveTo method, of class Elevator.
     */
    @Test
    public void testMoveToOnInvalidLowFloor() {
        int floor = 0, result;
        int expectedResult = 1;
        Elevator instance = new Elevator("A",0);
        instance.setCurrentFloor(expectedResult);
        instance.moveTo(floor);
        result = instance.getCurrentFloor();
        assertEquals(expectedResult, result);
    }
}
