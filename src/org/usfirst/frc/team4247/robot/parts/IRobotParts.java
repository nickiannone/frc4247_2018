package org.usfirst.frc.team4247.robot.parts;

import org.usfirst.frc.team4247.robot.autonomous.Driver;
import org.usfirst.frc.team4247.robot.autonomous.FieldMap;
import org.usfirst.frc.team4247.robot.autonomous.Navigator;
import org.usfirst.frc.team4247.robot.vision.VisionProcessor;

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
	
	// Methods to get custom components
	Driver getDriver();
	Navigator getNavigator();
	FieldMap getFieldMap();
	VisionProcessor getVisionProcessor();
}
