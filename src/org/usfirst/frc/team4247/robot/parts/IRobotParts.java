package org.usfirst.frc.team4247.robot.parts;

import org.usfirst.frc.team4247.robot.parts.ISmartDashboard;

public interface IRobotParts {
	// Get base components of the robot
	IDrive getMecanumDrive();
	IMotor getLiftMotor(); // Controls the lift
	IMotor getClimbMotor(); // Extends and retracts the climber
	IAccelerometer getAccelerometer();
	IGyro getGyro();
	IPneumatics getPneumatics();
	IJoystick getJoystick();
	ICamera getCamera();
	ITimer getTimer();
	IDriverStation getDriverStation();
	ISmartDashboard getSmartDashboard();
	
	IPairedSolenoid getGrabber();
	IPairedSolenoid getClaw();
}
