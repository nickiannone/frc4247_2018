package org.usfirst.frc.team4247.robot.autonomous;

import org.usfirst.frc.team4247.robot.Robot;

/**
 * The Navigator class is responsible for taking what is
 * known about the current state of the field and the robot's
 * operating state, and translating those into a series of
 * Tasks which can be performed by the Driver during
 * Autonomous mode.
 * 
 * @author NickIannone
 */
public class Navigator {

	public Navigator(Robot robot) {
		// TODO Auto-generated constructor stub
	}

	public Task[] generateInitialTasks(State state) {
		// TODO Auto-generated method stub
		return null;
	}

	public void updateFieldMap(FieldMap fieldMap, AutoState as) {
		// TODO Auto-generated method stub
		// Apply things like robot position,
		// any temporary obstacles,
		// missing cubes,
		// etc.
	}

	public Task[] reevaluateTasks(State state, AutoState as) {
		// TODO Auto-generated method stub
		return null;
	}

}
