package com.paulesson.elevator.web.model;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Paul Esson
 */
@XmlRootElement(name = "Direction")
public enum Direction  implements Serializable{
    STOPPED,UP,DOWN;

    public static Direction fromControllerDirection(com.paulesson.elevator.elevatorcontrol.model.Status from){
        Direction ret;
        switch (from){
            case STOPPED:
                ret = Direction.STOPPED;
                break;
            case UP:
                ret = Direction.UP;
                break;
            case DOWN:
                ret = Direction.DOWN;
                break;
            case WAITING:
                ret = Direction.STOPPED;
                break;
            default:
                ret = Direction.STOPPED;
                break;
        }
        return ret;
    }
}
