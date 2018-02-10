package org.usfirst.frc.team4247.robot.parts;

public interface ITimer {
	public void start();
	public void stop();
	public void reset();
	public double getMatchTime();
	public double get();
	
	// TODO Can we accomplish these in mocks?
	public void delay(double seconds);
	public double getFPGATimestamp();
	public boolean hasPeriodPassed(double timestamp);
}
