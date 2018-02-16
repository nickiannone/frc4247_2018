package org.usfirst.frc.team4247.robot.autonomous;

import org.usfirst.frc.team4247.robot.parts.IDrive;
import org.usfirst.frc.team4247.robot.parts.IRobotParts;
import org.usfirst.frc.team4247.robot.parts.ITimer;

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
	
	private TaskType type;
	private double value;
	
	// The time at which this task was entered.
	private double startTime;
	
	// The target time of completion.
	private double targetTime;
	
	// Drive solver
	private IDriveSolver driveSolver;

	public Task(TaskType type, double value) {
		this.type = type;
		this.value = value;
	}

	public enum TaskType {
		DRIVE,
		STRAFE,
		ROTATE,
		GRAB,
		RELEASE
	}
	
	public void enter(IRobotParts robotParts) {
		IDrive drive = robotParts.getMecanumDrive();
		ITimer timer = robotParts.getTimer();
		this.startTime = timer.get();
		
		// Stop the drive so we can reconfigure for the next go-round
		drive.stopDrive();
		
		switch (type) {
		case DRIVE:
			driveSolver = new ConstantDriveSolver();
			break;
		case STRAFE:
			driveSolver = new RampingDriveSolver();
			break;
		case ROTATE:
			driveSolver = new RampingGyroDriveSolver(robotParts.getGyro());
			break;
		case GRAB:
			
			break;
		case RELEASE:
			
			break;
		}
	}
	
	public void process(IRobotParts robotParts) {
		ITimer timer = robotParts.getTimer();
		double timeSinceStart = timer.get() - this.startTime;
		
		switch (type) {
		case DRIVE:
			
			break;
		case STRAFE:
			
			break;
		case ROTATE:
			
			break;
		case GRAB:
			
			break;
		case RELEASE:
			
			break;
		}
	}
}
