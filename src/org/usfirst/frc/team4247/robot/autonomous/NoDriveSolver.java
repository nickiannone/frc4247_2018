package org.usfirst.frc.team4247.robot.autonomous;

import org.usfirst.frc.team4247.robot.parts.ISmartDashboard;

public class NoDriveSolver implements IDriveSolver {

	@Override
	public void initDrive(double goal, DriveDirection direction) {}

	@Override
	public Outputs updateDrive(double dTime, double accelX, double accelY, double gyroAngle, ISmartDashboard dashboard) {
		return new Outputs();
	}

}
