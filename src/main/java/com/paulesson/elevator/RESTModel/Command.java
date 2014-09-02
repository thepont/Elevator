package com.paulesson.elevator.RESTModel;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Command, sent when elevator is called
 *
 * @author Paul Esson
 */

@XmlRootElement(name = "Command")
public class Command {

    private byte people;
    private short levelFrom;
    private short levelTo;

    Command(byte people, short levelFrom, short levelTo) {
        this.people = people;
        this.levelFrom = levelFrom;
        this.levelTo = levelTo;
    }

    public byte getPeople() {
        return people;
    }

    public void setPeople(byte people) {
        this.people = people;
    }

    public short getLevelFrom() {
        return levelFrom;
    }

    public void setLevelFrom(short levelFrom) {
        this.levelFrom = levelFrom;
    }

    public short getLevelTo() {
        return levelTo;
    }

    public void setLevelTo(short levelTo) {
        this.levelTo = levelTo;
    }

}
