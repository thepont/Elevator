/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.paulesson.elevator;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 *
 * @author pont
 */
@Component
public class CommandProcessingThread extends Thread {

    @Autowired
    ElevatorCommandRouter ecr;
    
    boolean running = true;
    
    @PostConstruct
    public void init(){
        this.start();
    }
    
    @PreDestroy
    public void destory(){
        running = false;
        this.interrupt();
    }
    
    @Override
    public void run() {
        RequestCommand currentCommand;
        Elevator currentElevator;
        while(running)
        {
            try {
                currentCommand = null;
                ecr.waitForElevatorAvailable();
                currentCommand = ecr.waitDequeueCommand();
                currentElevator = ecr.requestElevator(currentCommand);
                ecr.execute(currentCommand,currentElevator);
                
            } catch(InterruptedException e) {
                
            }
        }
    }
}
