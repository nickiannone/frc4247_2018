package org.usfirst.frc.team4247.robot.parts;

public interface IEncoder {

	int get();
	boolean getDirection();
	void reset();
	double getRate();
	double getDistance();
	void setReverseDirection(boolean reverseDirection);
	boolean getReverseDirection();
	void setDistancePerPulse(double distance);
	double getDistancePerPulse();
}
