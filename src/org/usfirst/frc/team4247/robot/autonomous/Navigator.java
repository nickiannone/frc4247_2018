package org.usfirst.frc.team4247.robot.autonomous;

import org.usfirst.frc.team4247.robot.Robot;
import org.usfirst.frc.team4247.robot.autonomous.FieldMap.Position;
import org.usfirst.frc.team4247.robot.autonomous.FieldMap.Region;
import org.usfirst.frc.team4247.robot.autonomous.Task.TaskType;

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
	
	private Robot robot;

	public Navigator(Robot robot) {
		// TODO Auto-generated constructor stub
		this.robot = robot;
	}

	public Task[] generateInitialTasks(State state) {
		Position pos = robot.fieldMap.getCurrentPosition();
		switch (state) {
		case START:
			// No tasks
			return new Task[] {};
		case BASE_LINE:
			Region targetRegion;
			switch (robot.fieldMap.getStartPosition()) {
			case LEFT:
				// TODO Fill these in based on bitmap!
				targetRegion = new Region();
				break;
			case CENTER:
				targetRegion = new Region();
				break;
			case RIGHT:
				targetRegion = new Region();
				break;
			}
			
			return this.generatePathBetween(robot.fieldMap.getStartPosition().region.getCentroid(), targetRegion);
			break;
		case SCORE_CUBE:
			// TODO Score a cube
			break;
		case FIND_CUBE:
			// TODO Find a cube
			
			
			break;
		case IDLE:
		default:
			// Find a random point on the map
			double x, y;
			
			// Make sure it doesn't collide with anything
			do {
				x = Math.random() * (double)(FieldMap.MAX_X - 1);
				y = Math.random() * (double)(FieldMap.MAX_Y - 1);
			} while (this.checkCollision(x, y));
			
			// Plot a path to that region
			Region r = new Region((int)x, (int)y, ((int)x)+1, ((int)y)+1);
			
			return this.generatePathBetween(robot.fieldMap.getCurrentPosition(), r);
		}
	}

	public void updateFieldMap(FieldMap fieldMap, AutoState as) {
		// TODO Auto-generated method stub
		// Apply things like robot position,
		// any temporary obstacles,
		// missing cubes,
		// etc.
	}

	public Task[] reevaluateTasks(State state, AutoState as) {
		// TODO Recalculate based on current state.
		return null;
	}
	
	private Task[] generatePathBetween(Position p, Region r) {
		// TODO Implement pathfinding algorithm
	}
	
	private boolean checkCollision(double x, double y) {
		// TODO A simple collision check against all Obstacles and TemporaryObstacles.
	}

}
