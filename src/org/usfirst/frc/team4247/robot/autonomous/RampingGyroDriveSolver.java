package org.usfirst.frc.team4247.robot.autonomous;

import org.usfirst.frc.team4247.robot.RobotUtils;
import org.usfirst.frc.team4247.robot.parts.IDrive;
import org.usfirst.frc.team4247.robot.parts.IGyro;

/**
 * A rotation-only version of the Drive solver that uses the gyro for rotation.
 */
public class RampingGyroDriveSolver implements IDriveSolver {
	
	private IGyro gyro;
	private double startingAngle = 0.0;
	private double goalAngle = 0.0;
	private boolean clockwise;

	public RampingGyroDriveSolver(IGyro gyro) {
		this.gyro = gyro;
	}
	
	@Override
	public void initDrive(IDrive drive, double distance, DriveAxis axis) {
		startingAngle = gyro.getAngle();
		goalAngle = distance;
		
		// Figure out whether we turn clockwise or counterclockwise
		double shortestOffset = RobotUtils.deltaAngle(startingAngle, distance);
		clockwise = (shortestOffset > 0.0);
	}

	@Override
	public double updateDrive(IDrive drive, double dTime) {
		double currentAngle = gyro.getAngle();
		
		// Figure out how much we can turn in this frame.
		double remainingAngle = RobotUtils.deltaAngle(currentAngle, goalAngle);
		clockwise = (remainingAngle > 0.0);
		double desiredAbsDeltaAngle = Math.min(Math.abs(remainingAngle), IDriveSolver.ROTATE_FULL_SPEED);
		double desiredDeltaAngle = (clockwise) ? desiredAbsDeltaAngle : -desiredAbsDeltaAngle;
		
		// Convert back from degrees per second to joystick input
		// TODO Apply PID ramping before this conversion?
		double rotationSpeed = desiredDeltaAngle / IDriveSolver.ROTATE_FULL_SPEED;
		
		// Drive us in the correct direction.
		drive.driveCartesian(0, 0, rotationSpeed);
		
		// Return the current angle.
		return currentAngle;
	}

}
