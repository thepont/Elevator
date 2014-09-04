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

    public static Direction fromControllerDirection(com.paulesson.elevator.elevatorcontrol.model.Direction from){
        Direction ret = Direction.valueOf(from.name());
        return ret;
    }
}
