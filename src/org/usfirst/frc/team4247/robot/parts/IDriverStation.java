package org.usfirst.frc.team4247.robot.parts;

public interface IDriverStation {
	
	public enum Alliance {
		RED,
		BLUE,
		INVALID
	}
	
	public String getGameSpecificMessage();
	public Alliance getAlliance();
	public int getLocation();
}
