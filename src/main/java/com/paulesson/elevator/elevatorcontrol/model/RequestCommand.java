package com.paulesson.elevator.elevatorcontrol.model;

/**
 * Request command, sent when elevator is called
 *
 * @author Paul Esson
 */
public class RequestCommand {

    private int people;
    private int levelFrom;
    private int levelTo;

    public RequestCommand(int people, int levelFrom, int levelTo) {
        this.people = people;
        this.levelFrom = levelFrom;
        this.levelTo = levelTo;
    }

    /**
     * 
     * @return the amount of people in the request for an elevator.
     */
    
    public int getPeople() {
        return people;
    }

    /**
     * 
     * @param people the amount of people in the request for an elevator.
     */
    public void setPeople(byte people) {
        this.people = people;
    }
    /**
     * @return The level the elevator is requested from
     */
    public int getLevelFrom() {
        return levelFrom;
    }

    /**
     * 
     * @param levelFrom The level the elevator is requested from
     */
    public void setLevelFrom(short levelFrom) {
        this.levelFrom = levelFrom;
    }
    /**
     * 
     * @return The level the elevator is requested to.
     */

    public int getLevelTo() {
        return levelTo;
    }

    /**
     * @param levelTo The level the elevator is requested to.
     */
    
    public void setLevelTo(short levelTo) {
        this.levelTo = levelTo;
    }

}
