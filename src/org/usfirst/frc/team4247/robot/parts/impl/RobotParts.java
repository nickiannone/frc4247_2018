package org.usfirst.frc.team4247.robot.parts.impl;

import org.usfirst.frc.team4247.robot.parts.IAccelerometer;
import org.usfirst.frc.team4247.robot.parts.ICamera;
import org.usfirst.frc.team4247.robot.parts.IDrive;
import org.usfirst.frc.team4247.robot.parts.IDriverStation;
import org.usfirst.frc.team4247.robot.parts.IGyro;
import org.usfirst.frc.team4247.robot.parts.IJoystick;
import org.usfirst.frc.team4247.robot.parts.IMotor;
import org.usfirst.frc.team4247.robot.parts.IPairedSolenoid;
import org.usfirst.frc.team4247.robot.parts.IPneumatics;
import org.usfirst.frc.team4247.robot.parts.IRobotParts;
import org.usfirst.frc.team4247.robot.parts.ISmartDashboard;
import org.usfirst.frc.team4247.robot.parts.ITimer;

// TODO Move these out into implementation classes!
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.drive.MecanumDrive;

public class RobotParts implements IRobotParts {
	
	private IDriverStation driverStation;
	private ISmartDashboard smartDashboard;
	private IJoystick joystick;
	private ITimer timer;
	
	private IMotor liftMotor;
	private IMotor climbMotor;
	private IDrive mecanumDrive;
	private IPneumatics pneumatics;
	private ICamera camera;
	private IAccelerometer accelerometer;
	private IGyro gyro;
	private IPairedSolenoid claw;
	private IPairedSolenoid grabber;
	
	// Initialize the core robot parts (called in robotInit())
	public RobotParts() {
		
		// Driver Station
		this.driverStation = new DriverStation();
		
		// Dashboard (for auto stuff, etc.)
		this.smartDashboard = new SmartDashboard();
		
		// Joystick
		this.joystick = new Joystick(0);
		
		// Timer
		this.timer = new Timer();
		
		// Lift motor
		WPI_TalonSRX lift = new WPI_TalonSRX(5);
		// TODO Invert if needed!
		this.liftMotor = new Motor(lift);
		
		// Climb motor - Do we need another Talon for this?
		WPI_TalonSRX climb = new WPI_TalonSRX(6);
		// TODO Invert if needed!
		this.climbMotor = new Motor(climb);
		
		// Drive system
		WPI_TalonSRX frontLeft = new WPI_TalonSRX(1);
		WPI_TalonSRX frontRight = new WPI_TalonSRX(2);
		WPI_TalonSRX backLeft = new WPI_TalonSRX(3);
		WPI_TalonSRX backRight = new WPI_TalonSRX(4);
		
		backLeft.setInverted(true);
		frontRight.setInverted(true);
		
		MyMecanumDrive drive = new MyMecanumDrive(frontLeft, backLeft, frontRight, backRight);
		drive.setMaxOutput(0.75);
		this.mecanumDrive = new Drive(drive);
		
		// Pneumatics
		// TODO Figure out ports!
		this.pneumatics = new Pneumatics(10, 1);
		this.grabber = new PairedSolenoid(3, 4);
		this.claw = new PairedSolenoid(1, 2);
		
		// Accelerometer and Gyro
		this.accelerometer = new Accelerometer();
		this.gyro = new Gyro(0);
		
		// Camera
		this.camera = new Camera();
	}
	
	@Override
	public IDriverStation getDriverStation() {
		return driverStation;
	}

	@Override
	public IDrive getMecanumDrive() {
		return mecanumDrive;
	}

	@Override
	public IMotor getLiftMotor() {
		return liftMotor;
	}
	
	@Override
	public IMotor getClimbMotor() {
		return climbMotor;
	}

	@Override
	public IPneumatics getPneumatics() {
		return pneumatics;
	}

	@Override
	public IJoystick getJoystick() {
		return joystick;
	}
	
	@Override
	public ICamera getCamera() {
		return camera;
	}

	@Override
	public ITimer getTimer() {
		return timer;
	}

	@Override
	public IAccelerometer getAccelerometer() {
		return accelerometer;
	}

	@Override
	public IGyro getGyro() {
		return gyro;
	}

	@Override
	public ISmartDashboard getSmartDashboard() {
		return smartDashboard;
	}

	@Override
	public IPairedSolenoid getGrabber() {
		return grabber;
	}

	@Override
	public IPairedSolenoid getClaw() {
		return claw;
	}
}
