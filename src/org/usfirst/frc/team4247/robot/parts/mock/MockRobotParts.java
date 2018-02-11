package org.usfirst.frc.team4247.robot.parts.mock;

import org.usfirst.frc.team4247.robot.parts.ICamera;
import org.usfirst.frc.team4247.robot.parts.IDrive;
import org.usfirst.frc.team4247.robot.parts.IDriverStation;
import org.usfirst.frc.team4247.robot.parts.IJoystick;
import org.usfirst.frc.team4247.robot.parts.IMotor;
import org.usfirst.frc.team4247.robot.parts.IPneumatics;
import org.usfirst.frc.team4247.robot.parts.IRobotParts;
import org.usfirst.frc.team4247.robot.parts.ITimer;

public class MockRobotParts implements IRobotParts {
	
	private IDriverStation driverStation;
	private IDrive mecanumDrive;
	private IMotor liftMotor;
	private IMotor climbMotor;
	private IPneumatics pneumatics;
	private IJoystick joystick;
	private ICamera camera;
	private ITimer timer;

	public MockRobotParts() {
		// TODO Populate this with mock objects!
	}
	
	@Override
	public IDriverStation getDriverStation() {
		return this.driverStation;
	}
	
	public void setDriverStation(IDriverStation ds) {
		this.driverStation = ds;
	}
	
	@Override
	public IDrive getMecanumDrive() {
		return mecanumDrive;
	}
	
	public void setMecanumDrive(IDrive drive) {
		this.mecanumDrive = drive;
	}

	@Override
	public IMotor getLiftMotor() {
		return liftMotor;
	}
	
	public void setLiftMotor(IMotor motor) {
		this.liftMotor = motor;
	}
	
	@Override
	public IMotor getClimbMotor() {
		return climbMotor;
	}
	
	public void setClimbMotor(IMotor motor) {
		this.climbMotor = motor;
	}

	@Override
	public IPneumatics getPneumatics() {
		return pneumatics;
	}
	
	public void setPneumatics(IPneumatics pneumatics) {
		this.pneumatics = pneumatics;
	}

	@Override
	public IJoystick getJoystick() {
		return joystick;
	}
	
	public void setJoystick(IJoystick joystick) {
		this.joystick = joystick;
	}

	@Override
	public ICamera getCamera() {
		return camera;
	}
	
	public void setCamera(ICamera camera) {
		this.camera = camera;
	}

	@Override
	public ITimer getTimer() {
		return timer;
	}
	
	public void setTimer(ITimer timer) {
		this.timer = timer;
	}
}
