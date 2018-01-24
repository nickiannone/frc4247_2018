package org.usfirst.frc.team4247.robot.autonomous;

/**
 * The Task class represents a single operation to be performed
 * in real-time by the Driver class. These are broken down into
 * two categories:
 * 
 * <ul>
 * 	<li>Drive Commands (Drive, Strafe, Rotate)</li>
 *  <li>Manipulator Commands (Grab, Release)</li>
 * </ul>
 * 
 * The value parameter of the Task indicates distance, angle, or
 * height values to be used for task execution.
 * 
 * @author NickIannone
 */
public class Task {

	public Task(TaskType rotate, double value) {
		// TODO Auto-generated constructor stub
	}

	public enum TaskType {
		DRIVE,
		STRAFE,
		ROTATE,
		GRAB,
		RELEASE
	}
}
