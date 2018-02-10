package org.usfirst.frc.team4247.robot.autonomous;

import java.util.List;

import org.usfirst.frc.team4247.robot.IRobot;
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
	
	private IRobot robot;

	public Driver(IRobot robot) {
		// TODO Auto-generated constructor stub
		this.robot = robot;
	}

	public void setTasks(List<Task> tasks) {
		this.currentTasks = tasks;
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
		// TODO Auto-generated method stub
		
	}

}
