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

    private int people;
    private int levelFrom;
    private int levelTo;

    public Command(){}
    
    public Command(byte people, short levelFrom, short levelTo) {
        this.people = people;
        this.levelFrom = levelFrom;
        this.levelTo = levelTo;
    }
    
    public com.paulesson.elevator.elevatorcontrol.model.RequestCommand toRequestCommand(){
        com.paulesson.elevator.elevatorcontrol.model.RequestCommand rc = new com.paulesson.elevator.elevatorcontrol.model.RequestCommand((byte)people,(short)levelFrom,(short)levelTo);
        return rc;
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

    public void setLevelFrom(short levelFrom) {
        this.levelFrom = levelFrom;
    }

    public int getLevelTo() {
        return levelTo;
    }

    public void setLevelTo(int levelTo) {
        this.levelTo = levelTo;
    }

}
