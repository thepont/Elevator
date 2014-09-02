/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.paulesson.elevator;

import java.util.ArrayList;
import java.util.Queue;

/**
 *
 * @author Paul Esson
 */
public class ElevatorController {
    
    ArrayList<Elevator> elevators;
    Queue<RequestCommand> commandQueue;
    
    
    ElevatorController(ArrayList<Elevator> elevators)
    {
        this.elevators = elevators;
    }
    
    void queueCommand(RequestCommand cmd)
    {
        commandQueue.add(cmd);
    }
    
    Elevator requestElevator(RequestCommand cmd)
    {
        Elevator best = null;
        short distance, mindistance = Short.MAX_VALUE;
        for(Elevator el: elevators)
        {
            if(el.getDirection() == Direction.STOPPED)
            {
                if(el.getAllocatedLoad() + cmd.getPeople() < el.getCapacity())
                {
                    distance = (short)Math.abs(el.currentFloor - cmd.getLevelFrom());  
                    if( distance < mindistance)
                    {
                        mindistance = distance;
                        best = el;
                    }
                }
            }
        }
        return best;
    }  
}
