package com.paulesson.elevator.elevatorcontrol;

import com.paulesson.elevator.elevatorcontrol.model.Status;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.context.annotation.Scope;

/**
 * This class represents a mock elevator that can move up and down floors.
 *
 * @author Paul Esson
 */
public class Elevator {
    private static final long MOVEMENT_DELAY     = 500L;
    private static final Status INITAL_DIRECTION = Status.STOPPED;
    private static final int INIT_LOAD = 0;
    private static final short INIT_FLOOR = 1;
    
    private static final int TOP_FLOOR = 10;
    private static final int BOTTOM_FLOOR = 1;

    public static final byte CAPACITY = 20;
    
    private int dbId;
    private final String name;
    
    private final AtomicInteger currentFloor;
    private final AtomicInteger allocatedLoad;
    private final AtomicInteger load;
    
    private final AtomicReference<Status> direction;

    public Elevator(String name, int dbId) {
        this.name = name;
        this.dbId = dbId;
        this.allocatedLoad = new AtomicInteger(INIT_LOAD);
        this.currentFloor = new AtomicInteger(INIT_FLOOR);
        this.load = new AtomicInteger(INIT_LOAD);
        this.direction = new AtomicReference<Status>();
        this.direction.set(INITAL_DIRECTION);
    }

    public Elevator(com.paulesson.elevator.db.entities.Elevator e)
    {
        this.dbId = e.getId();
        this.name = e.getName();
        this.allocatedLoad = new AtomicInteger(INIT_LOAD);
        this.currentFloor = new AtomicInteger(e.getCurrentFloor());
        this.load = new AtomicInteger(e.getLoad());
        this.direction = new AtomicReference<Status>();
        if ( e.getStatus() != null )
        {
            this.direction.set(e.getStatus());   
        } else{
            this.direction.set(INITAL_DIRECTION);
        }
    }
    /**
     * Convert Threadsafe Elevator Object into Database Entity.
     * @return 
     */
    public com.paulesson.elevator.db.entities.Elevator toDBEntity(){
        com.paulesson.elevator.db.entities.Elevator entity = new com.paulesson.elevator.db.entities.Elevator();
        entity.setId(dbId);
        entity.setName(name);
        entity.setLoad(load.get());
        entity.setStatus(direction.get());
        entity.setCurrentFloor(currentFloor.get());
        return entity;
    }
    
    /**
     * Elevator copy constructor
     * @param elevator original Elevator to copy
     */
    public Elevator(Elevator elevator) {
        this.name = elevator.name;
        this.currentFloor = elevator.currentFloor;
        this.load = elevator.load;
        this.allocatedLoad = elevator.allocatedLoad;
        this.direction = elevator.direction;
    }

    public String getName() {
        return name;
    }

    public int getCurrentFloor() {
        return currentFloor.get();
    }

    protected void setCurrentFloor(int currentFloor) {
        this.currentFloor.set(currentFloor);
    }

    public int getLoad() {
        return load.get();
    }

    protected void setLoad(int load) {
        this.load.set(load);
    }

    public int getAllocatedLoad() {
        return allocatedLoad.get();
    }

    protected void setAllocatedLoad(int allocatedLoad) {
        this.allocatedLoad.set(allocatedLoad);
    }

    public Status getDirection() {
        return direction.get();
    }

    protected void setDirection(Status direction) {
        this.direction.set(direction);
    }

    public int getDbId() {
        return dbId;
    }

    public void setDbId(int dbId) {
        this.dbId = dbId;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + (this.name != null ? this.name.hashCode() : 0);
        hash = 37 * hash + (this.name != null ? this.currentFloor.hashCode() : 0);
        hash = 37 * hash + (this.name != null ? this.load.hashCode() : 0);
        hash = 37 * hash + (this.name != null ? this.allocatedLoad.hashCode() : 0);
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

        if (!this.name.equals(other.name)) {
            return false;
        }

        if (this.currentFloor != other.currentFloor) {
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

    /**
     * Delays the elevator for MOVEMENT_DELAY, this used to simulate real life elevator conditions.
    */
    private void delayElevator() {
        try {
            Thread.sleep(MOVEMENT_DELAY);
        } catch (InterruptedException ex) {
            Logger.getLogger(Elevator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Moves elevator up a floor
     * @return the updated floor
     */
    protected int moveUpFloor() {
        delayElevator();
        return currentFloor.incrementAndGet();
    }

    /**
     * Moves elevator down a floor
     * @return the updated floor;
     */
    protected int moveDownFloor() {
        delayElevator();
        return currentFloor.decrementAndGet();
    }

    /**
     * Picks people up in the elevator
     * @param amt amount of people to pick up
     * @return amount of people in the lift
     */
    protected int pickUp(int amt) {
        delayElevator();
        return load.addAndGet(amt);
    }

    /**
     * Drops people off the elevator
     * @param amt amount of people to drop off
     * @return amount of people in the lift
     */
    protected int dropOff(int amt) {
        delayElevator();
        return load.addAndGet(-amt);
    }

    /**
     * Moves the elevator to another floor
     * @param floor floor to move elevator to
     */
    public void moveTo(int floor) {
        if ( floor > TOP_FLOOR || floor < BOTTOM_FLOOR)
            return;
        while (currentFloor.get() != floor) {
            if (currentFloor.get() < floor) {
                direction.lazySet(Status.UP);
                moveUpFloor();
            }
            if (currentFloor.get() > floor) {
                direction.lazySet(Status.DOWN);
                moveDownFloor();
            }
        }
    }
}
