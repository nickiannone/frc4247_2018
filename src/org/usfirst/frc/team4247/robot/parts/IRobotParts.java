package org.usfirst.frc.team4247.robot.parts;

public interface IRobotParts {
	// Get base components of the robot
	IDrive getMecanumDrive();
	IMotor getLiftMotor(); // Controls the lift
	IMotor getClimbMotor(); // Extends and retracts the climber
	IPneumatics getPneumatics();
	IJoystick getJoystick();
	ICamera getCamera();
	ITimer getTimer();
	IDriverStation getDriverStation();
}
