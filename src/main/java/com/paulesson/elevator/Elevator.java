package com.paulesson.elevator;

/**
 * This class represents an elevator
 * @author Paul Esson
 */

public class Elevator {

    short currentFloor;
    byte capacity;
    byte load;
    byte allocatedLoad;
    Direction direction;
    
    public Elevator(){}

    public Elevator(Elevator org){
        this.currentFloor = org.currentFloor;
        this.capacity = org.capacity;
        this.load = org.load;
        this.allocatedLoad = org.allocatedLoad;
        this.direction = org.direction;
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
    
}
