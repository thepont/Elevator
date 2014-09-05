Elevator
========
Spring/Hibernate based sample application that controls a mock elevator moving up and down a shaft.

Hibernate is currently only used for storing previous command objects, and the state of the elevator.

The elevator is predominantly controlled by the multi threaded code in the com.paulesson.elevator.elevatorcontrol package. Commands are added to a queue that is being waited on by a thread for a command to enter and an elevator to be free, once those conditions are met the ElevatorCommandRouter attempts to finds the most ideal elevator to service 
the request.

