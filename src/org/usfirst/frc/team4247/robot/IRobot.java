package org.usfirst.frc.team4247.robot;

import org.usfirst.frc.team4247.robot.parts.IRobotParts;

public interface IRobot {

	// Methods to enforce via IterativeRobot
	void robotInit();
	void robotPeriodic();
	void disabledInit();
	void disabledPeriodic();
	void autonomousInit();
	void autonomousPeriodic();
	void teleopInit();
	void teleopPeriodic();
	
	IRobotParts getRobotParts();
}