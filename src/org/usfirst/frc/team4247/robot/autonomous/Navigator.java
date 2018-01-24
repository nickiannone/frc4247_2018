package org.usfirst.frc.team4247.robot.autonomous;

import java.util.LinkedList;
import java.util.List;

import org.usfirst.frc.team4247.robot.Robot;
import org.usfirst.frc.team4247.robot.autonomous.FieldMap.Approach;
import org.usfirst.frc.team4247.robot.autonomous.FieldMap.Cube;
import org.usfirst.frc.team4247.robot.autonomous.FieldMap.StartPosition;
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

	public List<Task> generateInitialTasks(State state) {
		Position pos = robot.fieldMap.getCurrentPosition();
		switch (state) {
		case START:
			// No tasks
			return new LinkedList<Task>();
		case BASE_LINE:
			Region targetRegion = (robot.fieldMap.getStartPosition() == StartPosition.LEFT)
				? new Region(4, 20, 9, 25) 
				: new Region(41, 20, 46, 25);
			
			return this.generatePathBetween(robot.fieldMap.getStartPosition().region.getCentroid(), targetRegion);
		case SCORE_CUBE:
			// TODO Score a cube
			Target t = robot.fieldMap.findNearestAlliedTarget();
			Region targetRegion = t.findClosestApproachPositionTo(robot.fieldMap.getCurrentPosition());
			double angle = targetRegion.getApproachAngle(t.region);
			List<Task> scoreCubeTasks = this.generatePathBetween(robot.fieldMap.getCurrentPosition(), targetRegion);
			scoreCubeTasks.add(new Task(TaskType.ROTATE, angle));
			scoreCubeTasks.add(new Task(TaskType.RELEASE, t.height));
			return scoreCubeTasks;
		case FIND_CUBE:
			Cube nearestCube = robot.fieldMap.findNearestCube();
			List<Task> findCubeTasks = this.generatePathBetween(robot.fieldMap.getCurrentPosition(), nearestCube.getApproachRegion());
			findCubeTasks.add(new Task(TaskType.ROTATE, nearestCube.approach.angle));
			findCubeTasks.add(new Task(TaskType.GRAB, nearestCube.height));
			return findCubeTasks;
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
			Region randomRegion = new Region((int)x, (int)y, ((int)x)+1, ((int)y)+1);
			
			return this.generatePathBetween(robot.fieldMap.getCurrentPosition(), randomRegion);
		}
	}

	public void updateFieldMap(FieldMap fieldMap, AutoState as) {
		// TODO Auto-generated method stub
		// Apply things like robot position,
		// any temporary obstacles,
		// missing cubes,
		// etc.
	}

	public List<Task> reevaluateTasks(State state, AutoState as) {
		// TODO Make sure we don't need anything more than the initial tasks here!
		return this.generateInitialTasks(state);
	}
	
	private List<Task> generatePathBetween(Position p, Region r) {
		// TODO Implement pathfinding algorithm!!!
		return new LinkedList<Task>();
	}
	
	private boolean checkCollision(double x, double y) {
		// TODO A simple collision check against all Obstacles and TemporaryObstacles.
		return false;
	}

}
