package org.usfirst.frc.team4247.robot;

public interface IRobotLogic {
	void robotInit();
	void robotPeriodic();
	void disabledInit();
	void disabledPeriodic();
	void autonomousInit();
	void autonomousPeriodic();
	void teleopInit();
	void teleopPeriodic();
}
