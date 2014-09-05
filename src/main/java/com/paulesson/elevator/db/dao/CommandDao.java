package com.paulesson.elevator.db.dao;

import com.paulesson.elevator.db.entities.Command;
import java.util.List;

/**
 * 
 * @author Paul Esson
 */
public interface CommandDao
{    
    public int saveCommand(Command cmd);
    public List<Command>getCommands();
}