
package com.paulesson.elevator.web.model;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Represents an Elevator.
 * @author Paul Esson
 */
@XmlRootElement(name = "Elevator")
public class Elevator implements Serializable{
    

    
    public Elevator(com.paulesson.elevator.elevatorcontrol.Elevator from){
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
    
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + (this.direction != null ? this.direction.hashCode() : 0);
        hash = 97 * hash + this.currentFloor;
        hash = 97 * hash + this.numPeople;
        hash = 97 * hash + (this.name != null ? this.name.hashCode() : 0);
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
        if (this.direction != other.direction) {
            return false;
        }
        if (this.currentFloor != other.currentFloor) {
            return false;
        }
        if (this.numPeople != other.numPeople) {
            return false;
        }
        if ((this.name == null) ? (other.name != null) : !this.name.equals(other.name)) {
            return false;
        }
        return true;
    }
    
}

