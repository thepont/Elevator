package com.paulesson.elevator.db.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *Table for auditing commands sent to elevator.
 * @author Paul Esson
 */

@Entity
@Table(name = "COMMAND")
public class Command {
    private int id;
    private int floorFrom;
    private int floorTo;
    private int people;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", unique = true, nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "FLOOR_FROM", nullable = false)
    public int getFloorFrom() {
        return floorFrom;
    }

    public void setFloorFrom(int floorFrom) {
        this.floorFrom = floorFrom;
    }

    @Column(name = "FLOOR_TO", nullable = false)
    public int getFloorTo() {
        return floorTo;
    }

    public void setFloorTo(int floorTo) {
        this.floorTo = floorTo;
    }

    @Column(name = "NUM_PEOPLE", nullable = false)
    public int getPeople() {
        return people;
    }

    public void setPeople(int people) {
        this.people = people;
    }
}
