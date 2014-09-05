package com.paulesson.elevator.db.dao;

import com.paulesson.elevator.db.entities.Elevator;

/**
 * Database access object for elevators.
 * @author Paul Esson
 */
public interface ElevatorDao {
    public void saveElevator(Elevator elevator);
    public Elevator getElevator(int id);
    public Elevator getOrCreate(int id, String name);
}
