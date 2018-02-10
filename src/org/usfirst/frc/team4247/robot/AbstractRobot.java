package org.usfirst.frc.team4247.robot;

import org.usfirst.frc.team4247.robot.parts.IRobotParts;

public abstract class AbstractRobot implements IRobot {
	protected IRobotLogic robotLogic;
	protected IRobotParts robotParts;
	
	@Override
	public IRobotParts getRobotParts() {
		return robotParts;
	}
	
	public 
}
