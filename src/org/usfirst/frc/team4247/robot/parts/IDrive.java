package org.usfirst.frc.team4247.robot.parts;

public interface IDrive {
	public void stopDrive();
	public void feedWatchdog();
	public void driveCartesian(double y, double x, double zRot);
	
	public double getXSpeed();
	public double getYSpeed();
	public double getZRotationSpeed();
}
