package org.usfirst.frc.team4247.robot.autonomous;

import org.usfirst.frc.team4247.robot.RobotUtils;
import org.usfirst.frc.team4247.robot.parts.IGyro;
import org.usfirst.frc.team4247.robot.parts.ISmartDashboard;

/**
 * A rotation-only version of the Drive solver that uses the gyro for rotation.
 */
public class RampingGyroDriveSolver implements IDriveSolver {
	
	// Full rotation speed (degrees per unit joystick input)
	// TODO Measure this from robot!
	private static final double ROTATE_FULL_SPEED = 10.0;

	private IGyro gyro;
	private double startingAngle = 0.0;
	private double goalAngle = 0.0;
	private boolean clockwise;

	public RampingGyroDriveSolver(IGyro gyro) {
		this.gyro = gyro;
	}
	
	@Override
	public void initDrive(double goal, DriveDirection direction) {
		startingAngle = gyro.getAngle();
		goalAngle = goal;
		
		// Figure out whether we turn clockwise or counterclockwise
		double shortestOffset = RobotUtils.deltaAngle(startingAngle, goal);
		clockwise = (shortestOffset > 0.0);
	}

	@Override
	public Outputs updateDrive(double dTime, double accelX, double accelY, double gyroAngle, ISmartDashboard dashboard) {
		Outputs o = new Outputs();
		
		double currentAngle = gyroAngle;
		
		// Figure out how much we can turn in this frame.
		double remainingAngle = RobotUtils.deltaAngle(currentAngle, goalAngle);
		clockwise = (remainingAngle > 0.0);
		double desiredAbsDeltaAngle = Math.min(Math.abs(remainingAngle), ROTATE_FULL_SPEED);
		double desiredDeltaAngle = (clockwise) ? desiredAbsDeltaAngle : -desiredAbsDeltaAngle;
		
		// Convert back from degrees per second to joystick input
		// TODO Apply PID ramping before this conversion!
		double rotationSpeed = desiredDeltaAngle / ROTATE_FULL_SPEED;
		
		o.zRot = rotationSpeed;
		
		if (Math.abs(remainingAngle) < 1.0) {
			o.done = true;
		}
		
		return o;
	}

}
