package org.usfirst.frc.team4247.robot.parts;

public interface IRobotParts {
	// Get base components of the robot
	IDrive getMecanumDrive();
	void setMecanumDrive(IDrive mecanumDrive);
	
	IMotor getLiftMotor();
	void setLiftMotor(IMotor liftMotor);
	
	IPneumatics getPneumatics();
	void setPneumatics(IPneumatics pneumatics);
	
	IJoystick getJoystick();
	void setJoystick(IJoystick joystick);
	
	ICamera getCamera();
	void setCamera(ICamera camera);
	
	ITimer getTimer();
	void setTimer(ITimer timer);
}
