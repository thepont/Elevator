package com.paulesson.elevator.web.model;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;
/**
 * Command, sent when elevator is called
 *
 * @author Paul Esson
 */

@XmlRootElement(name = "Command")
public class Command implements Serializable{

    private int id;
    private int people;
    private int levelFrom;
    private int levelTo;

    public Command(){}
    
    /**
     * Create a new command.
     * @param people amount of people who are requesting an elevator.
     * @param levelFrom the level the elevator has to pick people up
     * @param levelTo  the level the elevator has to drop people off
     */
    public Command(int people, int levelFrom, int levelTo) {
        this.people = people;
        this.levelFrom = levelFrom;
        this.levelTo = levelTo;
    }
    /**
     * Create a REST Command model from a DBEntity.
     * @param dbEntity dbEntity to create command from.
     */
    public Command(com.paulesson.elevator.db.entities.Command dbEntity){
        this.id = dbEntity.getId();
        this.people = dbEntity.getPeople();
        this.levelFrom = dbEntity.getFloorFrom();
        this.levelTo = dbEntity.getFloorTo();
    }
    
    /**
     * Create a DB entity from a command.
     * @return DB Enity of the command.
     */
    public com.paulesson.elevator.db.entities.Command toDbEntity(){
        com.paulesson.elevator.db.entities.Command entity = new com.paulesson.elevator.db.entities.Command();
        entity.setId(id);
        entity.setFloorFrom(levelFrom);
        entity.setFloorTo(levelTo);
        entity.setPeople(people);
        return entity;
    }
    
    public com.paulesson.elevator.elevatorcontrol.model.RequestCommand toRequestCommand(){
        com.paulesson.elevator.elevatorcontrol.model.RequestCommand rc = new com.paulesson.elevator.elevatorcontrol.model.RequestCommand((byte)people,(short)levelFrom,(short)levelTo);
        return rc;
    }
    
    public int getId()
    {
        return id;
    }
    
    public void setId(int id){
        this.id = id;
    }

    public int getPeople() {
        return people;
    }

    public void setPeople(int people) {
        this.people = people;
    }

    public int getLevelFrom() {
        return levelFrom;
    }

    public void setLevelFrom(int levelFrom) {
        this.levelFrom = levelFrom;
    }

    public int getLevelTo() {
        return levelTo;
    }

    public void setLevelTo(int levelTo) {
        this.levelTo = levelTo;
    }

}
