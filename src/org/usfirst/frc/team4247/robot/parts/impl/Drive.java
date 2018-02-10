package org.usfirst.frc.team4247.robot.parts.impl;

import org.usfirst.frc.team4247.robot.parts.IDrive;

import edu.wpi.first.wpilibj.drive.MecanumDrive;

public class Drive implements IDrive {
	
	private MecanumDrive drive;
	private double lastY;
	private double lastX;
	private double lastZRot;

	public Drive(MecanumDrive drive) {
		this.drive = drive;
	}

	@Override
	public void stopDrive() {
		this.drive.stopMotor();

		// Reset the stored variables so feedWatchdog() doesn't break
		lastY = 0.0;
		lastX = 0.0;
		lastZRot = 0.0;
	}

	@Override
	public void feedWatchdog() {
		// Just feed the last known values in, resetting the watchdog so we don't disable
		this.drive.driveCartesian(lastY, lastX, lastZRot);
	}

	@Override
	public void driveCartesian(double y, double x, double zRot) {
		// Stores the new values, and passes them on to the drive motors
		this.lastY = y;
		this.lastX = x;
		this.lastZRot = zRot;
		this.drive.driveCartesian(y, x, zRot);
	}

	@Override
	public double getXSpeed() {
		return lastX;
	}

	@Override
	public double getYSpeed() {
		return lastY;
	}

	@Override
	public double getZRotationSpeed() {
		return lastZRot;
	}
	
}
