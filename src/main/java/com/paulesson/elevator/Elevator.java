package com.paulesson.elevator;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Async;

/**
 * This class represents a mock elevator
 * @author Paul Esson
 */
@Scope("prototype")
public class Elevator {

    @Autowired
    ElevatorCommandRouter ecr;
    
    // Time taken to move a floor 
    private static final long LIFT_DELAY = 500L;
    private static final Direction INITAL_DIRECTION = Direction.STOPPED;
    private static final byte INIT_LOAD = 0;
    private static final short INIT_FLOOR = 0;
    private static final byte DEFAULT_CAPACITY = 20;
    
    
    private String name;
    private short currentFloor;
    private byte capacity;
    private byte load;
    private byte allocatedLoad;
    private Direction direction;
    
    public Elevator(String name)
    {
        this.name = name;
        this.direction = INITAL_DIRECTION;
        this.allocatedLoad = INIT_LOAD;
        this.currentFloor = INIT_FLOOR;
        this.capacity = DEFAULT_CAPACITY;
    }

    public Elevator(Elevator org){
        this.name = org.name;
        this.currentFloor = org.currentFloor;
        this.capacity = org.capacity;
        this.load = org.load;
        this.allocatedLoad = org.allocatedLoad;
        this.direction = org.direction;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public short getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(short currentFloor) {
        this.currentFloor = currentFloor;
    }

    public byte getCapacity() {
        return capacity;
    }

    public void setCapacity(byte capacity) {
        this.capacity = capacity;
    }

    public byte getLoad() {
        return load;
    }

    public void setLoad(byte load) {
        this.load = load;
    }
    
    public byte getAllocatedLoad() {
        return allocatedLoad;
    }

    public void setAllocatedLoad(byte allocatedLoad) {
        this.allocatedLoad = allocatedLoad;
    }

    public Direction getDirection() {
        return direction;
    }
    
    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + (this.name != null ? this.name.hashCode() : 0 );
        hash = 37 * hash + this.currentFloor;
        hash = 37 * hash + this.capacity;
        hash = 37 * hash + this.load;
        hash = 37 * hash + this.allocatedLoad;
        hash = 37 * hash + (this.direction != null ? this.direction.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Elevator other = (Elevator) obj;
        
        if(!this.name.equals(other.name)){
            return false;
        }
        
        if (this.currentFloor != other.currentFloor) {
            return false;
        }
        if (this.capacity != other.capacity) {
            return false;
        }
        if (this.load != other.load) {
            return false;
        }
        if (this.allocatedLoad != other.allocatedLoad) {
            return false;
        }
        if (this.direction != other.direction) {
            return false;
        }
        return true;
    }
    
    
    
    private void delayLift(){
        try {
            Thread.sleep(LIFT_DELAY);
        } catch (InterruptedException ex) {
            Logger.getLogger(Elevator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int moveUpFloor(){
        delayLift();
        //direction = Direction.UP;
        return ++currentFloor;
    }
    
    public int moveDownFloor(){
        delayLift();
        return --currentFloor;
    }
    
    public int pickUp(int amt){
        delayLift();
        return load+=amt;
    } 
    
    public int dropOff(int amt){
        delayLift();
        return load-=amt;
    }
    
    public void moveTo(int floor){
        if(currentFloor < floor)
        {
            direction = Direction.UP;
        }
        if(currentFloor > floor)
        {
            direction = Direction.DOWN;
        }
        while(currentFloor != floor)
        {
            if(direction == Direction.UP)
            {
                moveUpFloor();
            } 
            else
            {
                moveDownFloor();
            }
        }
    }
    
    @Async
    public void execute(RequestCommand cmd){
        short from = cmd.getLevelFrom();
        short to = cmd.getLevelTo();
        byte amtPeople = cmd.getPeople();

        moveTo(from);
        pickUp(amtPeople);
        moveTo(to);
        dropOff(amtPeople);
        direction = Direction.STOPPED;
        ecr.markElevatorAsAvailable();
    } 
}
