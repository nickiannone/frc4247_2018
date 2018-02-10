package org.usfirst.frc.team4247.robot;

import org.usfirst.frc.team4247.robot.parts.IRobotParts;

public interface IRobot {
	public IRobotParts getRobotParts();
	public IRobotLogic getRobotLogic();
}