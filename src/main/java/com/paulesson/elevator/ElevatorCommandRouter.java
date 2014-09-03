package com.paulesson.elevator;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Semaphore;
import javax.annotation.PreDestroy;

/**
 *
 * @author Paul Esson
 */

public class ElevatorCommandRouter {
    final static int START_QUEUE_SIZE = 0;
    
    private final List<Elevator> elevators;
    private final Semaphore elevatorAvailible;
    private final Semaphore queueEmpty = new Semaphore(START_QUEUE_SIZE);
    
    private ConcurrentLinkedQueue<RequestCommand> commandQueue = new ConcurrentLinkedQueue<RequestCommand>();
    private boolean running = true;
    
    public ElevatorCommandRouter(List<Elevator> elevators)
    {
        this.elevators = elevators;
        elevatorAvailible = new Semaphore(elevators.size());
        queueEmpty.drainPermits();
    }
     
    /**
     * Release semaphores and kill thread.
     */
    @PreDestroy
    public void destory(){
        queueEmpty.release();
        running = false;
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
   
    
    protected int getQueueSemephoreSize(){
        return queueEmpty.availablePermits();
    }
    
    /*
    * Return a copy of the current Elevators.
    **/
    List<Elevator> getElevators()
    {
       List<Elevator> copy = new ArrayList<Elevator>();
       for(Elevator ele : elevators){
           copy.add(new Elevator(ele));
       } 
       return copy;
    }
    
    /**
     * requests an elevator
     * @param cmd
     * @return 
     */
    
    Elevator requestElevator(RequestCommand cmd)
    {
        Elevator best = null;
        short distance, mindistance = Short.MAX_VALUE;
        for(Elevator ele: elevators)
        {
            if(ele.getDirection() == Direction.STOPPED)
            {
                if(ele.getAllocatedLoad() + cmd.getPeople() < ele.getCapacity())
                {
                    distance = (short)Math.abs(ele.getCurrentFloor() - cmd.getLevelFrom());  
                    if( distance < mindistance)
                    {
                        mindistance = distance;
                        best = ele;
                    }
                }
            }
        }
        return best;
    }
}
