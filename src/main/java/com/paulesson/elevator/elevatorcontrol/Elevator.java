package com.paulesson.elevator.elevatorcontrol;

import com.paulesson.elevator.elevatorcontrol.model.Direction;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * This class represents a mock elevator
 *
 * @author Paul Esson
 */
public class Elevator {

    // Time taken to move a floor 
    private static final long LIFT_DELAY = 500L;
    private static final Direction INITAL_DIRECTION = Direction.STOPPED;
    private static final int INIT_LOAD = 0;
    private static final short INIT_FLOOR = 1;
    
    private static final int TOP_FLOOR = 10;
    private static final int BOTTOM_FLOOR = 1;

    public static final byte CAPACITY = 20;
    
    private final String name;
    
    private final AtomicInteger currentFloor;
    private final AtomicInteger allocatedLoad;
    private final AtomicInteger load;
    
    private AtomicReference<Direction> direction;

    public Elevator(String name) {
        this.name = name;

        this.allocatedLoad = new AtomicInteger(INIT_LOAD);
        this.currentFloor = new AtomicInteger(INIT_FLOOR);
        this.load = new AtomicInteger(INIT_LOAD);
        this.direction = new AtomicReference<Direction>();
        this.direction.set(INITAL_DIRECTION);
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

    public Direction getDirection() {
        return direction.get();
    }

    protected void setDirection(Direction direction) {
        this.direction.set(direction);
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
     * Delays lift, used to simulate real life elevator conditions.
    */
    private void delayLift() {
        try {
            Thread.sleep(LIFT_DELAY);
        } catch (InterruptedException ex) {
            Logger.getLogger(Elevator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Moves elevator up a floor
     * @return the updated floor
     */
    protected int moveUpFloor() {
        delayLift();
        return currentFloor.incrementAndGet();
    }

    /**
     * Moves elevator down a floor
     * @return the updated floor;
     */
    protected int moveDownFloor() {
        delayLift();
        return currentFloor.decrementAndGet();
    }

    /**
     * Picks people up in the elevator
     * @param amt amount of people to pick up
     * @return amount of people in the lift
     */
    protected int pickUp(int amt) {
        delayLift();
        return load.addAndGet(amt);
    }

    /**
     * Drops people off the elevator
     * @param amt amount of people to drop off
     * @return amount of people in the lift
     */
    protected int dropOff(int amt) {
        delayLift();
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
                direction.lazySet(Direction.UP);
                moveUpFloor();
            }
            if (currentFloor.get() > floor) {
                direction.lazySet(Direction.DOWN);
                moveDownFloor();
            }
        }
    }
}
