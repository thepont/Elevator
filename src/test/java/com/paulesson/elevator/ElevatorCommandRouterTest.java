/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.paulesson.elevator;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author pont
 */
public class ElevatorCommandRouterTest {
    
    private static final int AMOUNT_TEST_ELEVATORS = 4;
    
    public ElevatorCommandRouterTest() {
    }

    
    private List<Elevator> getListElevator(){
        List<Elevator> elevatorList = new ArrayList<Elevator>();
        
        elevatorList.add(new Elevator("A"));
        elevatorList.add(new Elevator("B"));
        elevatorList.add(new Elevator("C"));
        elevatorList.add(new Elevator("D"));
        
        return elevatorList;
    }
    
    /**
    * This checks that we are correctly monitoring the queue size with the semaphore.
    */
    @Test 
    public void testQueueSemaphoreSize(){
        RequestCommand cmd = new RequestCommand((byte)10, (short)0, (short)5);
        ElevatorCommandRouter instance = new ElevatorCommandRouter(getListElevator());
        for(int i = 1; i <= 10000; i++ )
        {
            instance.queueCommand(cmd);
            assertEquals(i,instance.getQueueSemephoreSize());
        }
    }

    /**
     * Tests that the deQueue command works correctly.
     * 
     * This test needs to be improved to check that the instance.waitDequeueCommand is not blocking
     * if a bug exists in the semaphore counting code then we will just be stuck with a frozen test.
     */
    @Test
    public void testDeQueueSemaphoreSize() {
        RequestCommand cmd = new RequestCommand((byte) 10, (short) 0, (short) 5);
        RequestCommand returned;
        ElevatorCommandRouter instance = new ElevatorCommandRouter(getListElevator());
        for (int i = 1; i <= 100; i++) {
            instance.queueCommand(cmd);
        }
        try {
            for (int i = 100; i > 0; i--) {
                returned = instance.waitDequeueCommand();
                assertEquals(cmd, returned);
            }
        } catch (InterruptedException ex) {
            fail();
            Logger.getLogger(ElevatorCommandRouterTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void testGetElevatorsEqual(){
        List<Elevator> original = getListElevator();
        ElevatorCommandRouter instance = new ElevatorCommandRouter(original);
        List<Elevator> result = instance.getElevators();
        for(int i = 0; i < AMOUNT_TEST_ELEVATORS; i++){
            assertEquals(original.get(i), result.get(i));
        }
    }
    
    @Test
    public void testGetElevatorsDifferentObjects(){
        List<Elevator> original = getListElevator();
        ElevatorCommandRouter instance = new ElevatorCommandRouter(original);
        List<Elevator> result = instance.getElevators();
        for(int i = 0; i < AMOUNT_TEST_ELEVATORS; i++){
            assertNotSame(original.get(i), result.get(i));
        }
    }
    
    @Test
    public void testRequestElevator(){
        List<Elevator> elevators = getListElevator();
        RequestCommand cmd = new RequestCommand((byte)10,(short)10, (short)0);
        //set elevator closer to destination
        Elevator expected = elevators.get(1);
        expected.setCurrentFloor((short)8);
        ElevatorCommandRouter instance = new ElevatorCommandRouter(elevators);
        Elevator result = instance.requestElevator(cmd);
        assertEquals(expected, result);
    }
}
