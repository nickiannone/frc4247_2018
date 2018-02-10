package org.usfirst.frc.team4247.robot;

import org.usfirst.frc.team4247.robot.parts.IRobotParts;
import org.usfirst.frc.team4247.robot.parts.impl.RobotParts;

import edu.wpi.first.wpilibj.IterativeRobot;

public class Robot extends IterativeRobot implements IRobot {
	
	private IRobotLogic robotLogic;
	private IRobotParts robotParts;
	
	public Robot() {
		// Use the WPILib implementation by default
		robotParts = new RobotParts();
		
		// Use the robot logic we've decided on for competition
		// TODO Make this configurable?
		robotLogic = new RobotLogic(robotParts);
	}
	
	@Override
	public IRobotLogic getRobotLogic() {
		return robotLogic;
	}
	
	@Override
	public IRobotParts getRobotParts() {
		return robotParts;
	}
	
	// Binding logic (since we have to extend IterativeRobot for this to be runnable, 
	// we need this ugly boilerplate)
	@Override
	public void robotInit() {
		robotLogic.robotInit();
	}
	
	@Override
	public void robotPeriodic() {
		robotLogic.robotPeriodic();
	}
	
	@Override
	public void autonomousInit() {
		robotLogic.autonomousInit();
	}
	
	@Override
	public void autonomousPeriodic() {
		robotLogic.autonomousPeriodic();
	}
	
	@Override
	public void teleopInit() {
		robotLogic.teleopInit();
	}
	
	@Override
	public void teleopPeriodic() {
		robotLogic.teleopPeriodic();
	}
}