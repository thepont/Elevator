package com.paulesson.elevator.elevatorcontrol;

import com.paulesson.elevator.elevatorcontrol.model.RequestCommand;
import com.paulesson.elevator.elevatorcontrol.model.Direction;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Semaphore;
import org.springframework.scheduling.annotation.Async;

/**
 *
 * @author Paul Esson
 */

public class ElevatorCommandRouter {
    final static int START_QUEUE_SIZE = 0;
    
    private final List<Elevator> elevators;
    private final Semaphore elevatorAvailible;
    private final Semaphore queueEmpty = new Semaphore(START_QUEUE_SIZE);
    private final ConcurrentLinkedQueue<RequestCommand> commandQueue = new ConcurrentLinkedQueue<RequestCommand>();
    
    public ElevatorCommandRouter(List<Elevator> elevators)
    {
        this.elevators = elevators;
        elevatorAvailible = new Semaphore(elevators.size());
        queueEmpty.drainPermits();
    }
     
    
    /**
     * Queues a command and signals any threads waiting on queue.
     * @param cmd command to queue 
     */
    
    public void queueCommand(RequestCommand cmd)
    {
        if(cmd != null)
        {
            commandQueue.add(cmd);
            queueEmpty.release();
        }
    }
    
    /**
     * Waits on Queue requesting next command 
     * @return RequestCommand at the front of the queue
     * @throws InterruptedException thread was interrupted while waiting.
     */
    
    public RequestCommand waitDequeueCommand() throws InterruptedException {
        try
        {
            queueEmpty.acquire();
            RequestCommand cmd = commandQueue.poll();
            assert cmd!=null;
            return cmd;
        } catch(InterruptedException e) {
            queueEmpty.release();
            throw e;
        }
    }
    
    /**
     * Thread waits until an elevator becomes available.
     * @throws InterruptedException 
     */
    public void waitForElevatorAvailable() throws InterruptedException {
        try
        {
            elevatorAvailible.acquire();
        }
        catch(InterruptedException e) {
            elevatorAvailible.release();
            throw e;
        }
    }
    
    /**
     * Mark an elevator as available and ready for use 
     */
    protected void markElevatorAsAvailable(){
        elevatorAvailible.release();
    }
   
    /**
     * Returns the amount of permits available in the semaphore queue, this is equal
     * to the amount of items in the queue.
     * @return 
     */
    protected int getQueueSemephoreSize(){
        return queueEmpty.availablePermits();
    }
    @Async
    public void execute(RequestCommand cmd, Elevator e){
        short from = cmd.getLevelFrom();
        short to = cmd.getLevelTo();
        byte amtPeople = cmd.getPeople();

        e.moveTo(from);
        e.pickUp(amtPeople);
        e.moveTo(to);
        e.dropOff(amtPeople);
        e.setDirection(Direction.STOPPED);
        markElevatorAsAvailable();
    } 
    
    /*
    * Return a copy of the current Elevators.
    **/
    
    public List<Elevator> getElevators()
    {
       List<Elevator> copy = new ArrayList<Elevator>();
       for(Elevator ele : elevators){
           copy.add(new Elevator(ele));
       } 
       return copy;
    }
    
    /**
     * requests an elevator
     * 
     * this method is synchronized so multiple 'executes' do not get the same elevator. 
     * 
     * @param cmd command to execute
     * @return the most ideal free elevator to execute the command.
     */
    
    synchronized protected Elevator requestElevator(RequestCommand cmd) {
        Elevator best = null;
        int distance, mindistance = Integer.MAX_VALUE;
        for (Elevator ele : elevators) {
            if (ele.getDirection() == Direction.STOPPED) {
                distance = Math.abs(ele.getCurrentFloor() - cmd.getLevelFrom());
                if (distance < mindistance) {
                    mindistance = distance;
                    best = ele;
                }
            }
        }
        if (best != null)
        {
            best.setDirection(Direction.WAITING);
        }
        return best;
    }
}
