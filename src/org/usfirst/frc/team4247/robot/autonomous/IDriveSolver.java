package org.usfirst.frc.team4247.robot.autonomous;

import org.usfirst.frc.team4247.robot.parts.ISmartDashboard;

public interface IDriveSolver {
	
	public class Outputs {
		public double x;
		public double y;
		public double zRot;
		public boolean done;
	}
	
	public enum DriveDirection {
		DRIVE,
		STRAFE,
		ROTATE
	}

	/**
	 * Initializes the drive solver.
	 * @param goal The goal (relative distance in units or absolute angle in degrees) to aim for.
	 * @param direction The intended direction to travel.
	 */
	void initDrive(double goal, DriveDirection direction);
	
	/**
	 * Solves for the outputs to a drive based on a preconfigured target and the current time from start.
	 * @param dTime The time that has elapsed since the start of this drive movement.
	 * @return The drive outputs.
	 */
	Outputs updateDrive(double dTime, double accelX, double accelY, double gyroAngle, ISmartDashboard dashboard);
}
