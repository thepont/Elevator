package com.paulesson.elevator.RESTModel;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Paul Esson
 */
@XmlRootElement(name = "Direction")
public enum Direction{
    STOPPED,UP,DOWN
}
