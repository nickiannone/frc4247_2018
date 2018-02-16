package org.usfirst.frc.team4247.robot.autonomous;

import java.util.List;

import org.usfirst.frc.team4247.robot.IRobot;
import org.usfirst.frc.team4247.robot.parts.IRobotParts;
import org.usfirst.frc.team4247.robot.vision.VisionResult;

/**
 * The Driver class takes the place of a traditional driver
 * during Autonomous mode. By being fed a series of Tasks, it
 * will figure out how to translate those in real-time to a
 * set of outputs to the robot itself.
 * 
 * @author NickIannone
 */
public class Driver {
	
	private List<Task> currentTasks;
	private VisionResult visionResult;
	
	private IRobotParts robotParts;
	private FieldMap fieldMap;
	
	private boolean enteringTask = true;

	public Driver(IRobotParts robotParts, FieldMap fieldMap) {
		this.robotParts = robotParts;
		this.fieldMap = fieldMap;
	}

	public void setTasks(List<Task> tasks) {
		this.currentTasks = tasks;
		this.enteringTask = true;
	}

	public AutoState getAutoState(int frameCounter) {
		// Called at the start of a frame of autonomous.
		// Generates a state object encapsulating anything needed for this frame.

		// 
		
		
		// TODO Finish this!
		return null;
	}

	public void setVisionResults(VisionResult visionResult) {
		// Called whenever we get vision results
		this.visionResult = visionResult;
	}

	public void executeTasks() {
		Task currentTask = this.currentTasks.get(0);
		if (enteringTask) {
			currentTask.enter(this.robotParts);
			enteringTask = false;
		}
		currentTask.process(this.robotParts);
	}

}
