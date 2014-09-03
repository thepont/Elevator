package com.paulesson.elevator;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.context.annotation.Scope;

/**
 * This class represents a mock elevator
 * @author Paul Esson
 */
@Scope("prototype")
public class Elevator {
    
    // Time taken to move a floor 
    private static final long LIFT_DELAY = 500L;
    private static final Direction INITAL_DIRECTION = Direction.STOPPED;
    private static final int INIT_LOAD = 0;
    private static final short INIT_FLOOR = 0;
    private static final byte DEFAULT_CAPACITY = 20;
    
    
    private final String name;

    private final AtomicInteger currentFloor;
    private final AtomicInteger allocatedLoad;
    private final AtomicInteger load;
    
    //private short currentFloor;
    private final byte capacity;

    
    private AtomicReference<Direction> direction;
    
    public Elevator(String name)
    {
        this.name = name;
        
        this.allocatedLoad = new AtomicInteger(INIT_LOAD);
        this.currentFloor = new AtomicInteger(INIT_FLOOR);
        this.load = new AtomicInteger(INIT_LOAD);
        this.direction = new AtomicReference<Direction>();
        this.direction.set(INITAL_DIRECTION);
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
    
    public int getCurrentFloor() {
        return currentFloor.get();
    }

    public void setCurrentFloor(short currentFloor) {
        this.currentFloor.set(currentFloor);
    }

    public byte getCapacity() {
        return capacity;
    }

    public int getLoad() {
        return load.get();
    }

    public void setLoad(int load) {
        this.load.set(load);
    }
    
    public int getAllocatedLoad() {
        return allocatedLoad.get();
    }

    public void setAllocatedLoad(int allocatedLoad) {
        this.allocatedLoad.set(allocatedLoad);
    }

    public Direction getDirection() {
        return direction.get();
    }
    
    public void setDirection(Direction direction) {
        this.direction.set(direction);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + (this.name != null ? this.name.hashCode() : 0 );
        hash = 37 * hash + this.currentFloor.hashCode();
        hash = 37 * hash + this.capacity;
        hash = 37 * hash + this.load.hashCode();
        hash = 37 * hash + this.allocatedLoad.hashCode();
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
        return currentFloor.incrementAndGet();
    }
    
    public int moveDownFloor(){
        delayLift();
        return currentFloor.decrementAndGet();
    }
    
    public int pickUp(int amt){
        delayLift();
        return load.getAndAdd(amt);
    } 
    
    public int dropOff(int amt){
        delayLift();
        return load.getAndAdd(-amt);
    }
    
    public void moveTo(int floor){
        while(currentFloor.get() != floor)
        {
            if(currentFloor.get() < floor)
            {
                direction.lazySet(Direction.UP);
                moveUpFloor();
            }
            if(currentFloor.get() > floor)
            {
                direction.lazySet(Direction.DOWN);
                moveDownFloor();
            }   
        }
    }
    
 
}
