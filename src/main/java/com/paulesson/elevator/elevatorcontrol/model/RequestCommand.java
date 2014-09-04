/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.paulesson.elevator.elevatorcontrol.model;

/**
 * Request command, sent when elevator is called
 *
 * @author Paul Esson
 */
public class RequestCommand {

    private byte people;
    private short levelFrom;
    private short levelTo;

    public RequestCommand(byte people, short levelFrom, short levelTo) {
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
