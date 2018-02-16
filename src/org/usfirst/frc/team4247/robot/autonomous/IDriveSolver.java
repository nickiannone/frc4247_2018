package org.usfirst.frc.team4247.robot.autonomous;

import org.usfirst.frc.team4247.robot.parts.IDrive;

public interface IDriveSolver {
	
	public enum DriveAxis {
		DRIVE,
		STRAFE,
		ROTATE
	}
	
	// TODO Fill these in from testing!
	
	/**
	 * Max. velocity (in units (6.5") / second) forward drive reaches at full speed
	 */
	public static final double DRIVE_FULL_SPEED = 1.0;
	
	/**
	 * Max. velocity (in units (6.5") /second) strafe drive reaches at full speed
	 */
	public static final double STRAFE_FULL_SPEED = 0.85;
	
	/**
	 * Max. velocity (in degrees/second) rotate drive reaches at full speed
	 */
	public static final double ROTATE_FULL_SPEED = 45.0;

	/**
	 * Initializes the drive solver.
	 * @param drive The drive to operate on.
	 * @param goal The goal (relative distance in units or absolute angle in degrees) to aim for.
	 * @param axis The axis to drive along.
	 */
	void initDrive(IDrive drive, double goal, DriveAxis axis);
	
	/**
	 * Solves for the outputs to a drive based on a preconfigured target and the current time from start.
	 * @param drive The drive to output to.
	 * @param dTime The time that has elapsed since the start of this drive movement.
	 * @return How far we are towards that goal (at the current point in time).
	 */
	double updateDrive(IDrive drive, double dTime);
}
