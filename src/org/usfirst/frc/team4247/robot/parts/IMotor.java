package org.usfirst.frc.team4247.robot.parts;

public interface IMotor {
	public void set(double speed);
	public double get();
	public void setInverted(boolean isInverted);
	public boolean getInverted();
	public void disable();
	public void stopMotor();
}
