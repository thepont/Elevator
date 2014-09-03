package com.paulesson.elevator.RESTModel;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Paul Esson
 */
@XmlRootElement(name = "Direction")
public enum Direction  implements Serializable{
    STOPPED,UP,DOWN;

    public static Direction fromControllerDirection(com.paulesson.elevator.Direction from){
        Direction ret = Direction.valueOf(from.name());
        return ret;
    }
}
