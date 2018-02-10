package org.usfirst.frc.team4247.robot.parts.impl;

import org.usfirst.frc.team4247.robot.autonomous.Driver;
import org.usfirst.frc.team4247.robot.autonomous.FieldMap;
import org.usfirst.frc.team4247.robot.autonomous.Navigator;
import org.usfirst.frc.team4247.robot.parts.ICamera;
import org.usfirst.frc.team4247.robot.parts.IDrive;
import org.usfirst.frc.team4247.robot.parts.IJoystick;
import org.usfirst.frc.team4247.robot.parts.IMotor;
import org.usfirst.frc.team4247.robot.parts.IPneumatics;
import org.usfirst.frc.team4247.robot.parts.IRobotParts;
import org.usfirst.frc.team4247.robot.parts.ITimer;
import org.usfirst.frc.team4247.robot.vision.VisionProcessor;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.drive.MecanumDrive;

public class RobotParts implements IRobotParts {
	
	private IJoystick joystick;
	private ITimer timer;
	
	private IMotor liftMotor;
	private IDrive mecanumDrive;
	private IPneumatics pneumatics;
	private ICamera camera;
	
	public RobotParts() {
		// Initialize the core robot parts (called in robotInit())
		
		// Joystick
		this.joystick = new Joystick(0);
		
		this.timer = new Timer();
		
		
		WPI_TalonSRX lift = new WPI_TalonSRX(5);
		// TODO Invert if needed!
		this.liftMotor = new Motor(lift);
		
		// Drive system
		WPI_TalonSRX frontLeft = new WPI_TalonSRX(1);
		WPI_TalonSRX frontRight = new WPI_TalonSRX(2);
		WPI_TalonSRX backLeft = new WPI_TalonSRX(3);
		WPI_TalonSRX backRight = new WPI_TalonSRX(4);
		
		backLeft.setInverted(true);
		frontRight.setInverted(true);
		
		this.mecanumDrive = new Drive(new MecanumDrive(frontLeft, backLeft, frontRight, backRight));
		
		// Pneumatics
		
		
		// Sensors
		
		// Camera
		
	}

	@Override
	public IDrive getMecanumDrive() {
		return mecanumDrive;
	}

	@Override
	public void setMecanumDrive(IDrive mecanumDrive) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public IMotor getLiftMotor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setLiftMotor(IMotor liftMotor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public IPneumatics getPneumatics() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setPneumatics(IPneumatics pneumatics) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public IJoystick getJoystick() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setJoystick(IJoystick joystick) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ICamera getCamera() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setCamera(ICamera camera) {
		this.camera = camera;
	}

	@Override
	public VisionProcessor getVisionProcessor() {
		return this.visionProcessor;
	}

	@Override
	public ITimer getTimer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setTimer(ITimer timer) {
		// TODO Auto-generated method stub
		
	}

}
