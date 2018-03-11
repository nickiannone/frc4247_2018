package org.usfirst.frc.team4247.robot.parts;

public interface IPairedSolenoid {
	public enum Position {
		IDLE,
		ENGAGED_1,
		ENGAGED_2
	}
	
	public void setPosition(Position p);
	public Position getPosition();
	public void reset();
}
