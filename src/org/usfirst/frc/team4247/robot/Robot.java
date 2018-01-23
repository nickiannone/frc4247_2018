package org.usfirst.frc.team4247.robot;

import org.usfirst.frc.team4247.robot.autonomous.AutoState;
import org.usfirst.frc.team4247.robot.autonomous.Driver;
import org.usfirst.frc.team4247.robot.autonomous.FieldMap;
import org.usfirst.frc.team4247.robot.autonomous.Navigator;
import org.usfirst.frc.team4247.robot.autonomous.State;
import org.usfirst.frc.team4247.robot.autonomous.Task;
import org.usfirst.frc.team4247.robot.vision.VisionProcessor;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.drive.MecanumDrive;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class Robot extends IterativeRobot {
	
	// TODO Robot controls - keep public!
	public Timer timer = new Timer();
	//...
	
	public MecanumDrive drive;
	public Joystick joystick = new Joystick(0);
	
	
	
	// Vision values
	private VisionProcessor vision;
	
	// Autonomous values
	private State state = State.START;
	private boolean enteringState = true;
	private int frameCounter;
	
	public Navigator navigator;
	public Driver driver;
	public FieldMap fieldMap;
	
	@Override
	public void robotInit() {
		// Set up the controls
		
		// Drive system
		WPI_TalonSRX frontLeft = new WPI_TalonSRX(1);
		WPI_TalonSRX frontRight = new WPI_TalonSRX(2);
		WPI_TalonSRX backLeft = new WPI_TalonSRX(3);
		WPI_TalonSRX backRight = new WPI_TalonSRX(4);
		// TODO Invert any of the Talons here!
		this.drive = new MecanumDrive(frontLeft, backLeft, frontRight, backRight);
		
		// Pneumatics
		
		// Sensors
		
		// Camera
		
		// Set up vision processing
		this.vision = new VisionProcessor();
	}
	
	@Override
	public void robotPeriodic() {
		// Compressor?
	}
	
	@Override
	public void disabledInit() {
		// Disable the bot
	}
	
	@Override
	public void disabledPeriodic() {
		// Do something cool with lights and stuff, I dunno
	}
	
	@Override
	public void autonomousInit() {
		// Move into START state
		this.state = State.START;
		this.enteringState = true;
		
		// Start the timer
		this.timer.start();
		this.frameCounter = 0;
		
		// Get the GSC from the field, and reinitialize the FieldMap
		String gsc = DriverStation.getInstance().getGameSpecificMessage();
		this.fieldMap = new FieldMap(gsc);
		
		// Set up the Navigator and the Driver
		this.navigator = new Navigator(this);
		this.driver = new Driver(this);
	}
	
	@Override
	public void autonomousPeriodic() {
		this.frameCounter++;
		if (this.enteringState) {
			Task[] tasks = this.navigator.generateInitialTasks(this.state);
			this.driver.setTasks(tasks);
		} else {
			// Get the position updates from the driver
			AutoState as = this.driver.getAutoState(this.frameCounter);
			
			// Let the navigator see the state, and 
			this.navigator.updateFieldMap(this.fieldMap, as);
			
			// If we need to find a new plan
			if (as.needsReevaluation) {
				Task[] tasks = this.navigator.reevaluateTasks(this.state, as);
				this.driver.setTasks(tasks);
				return;
			}
			
			// If we need to run vision processing
			if (as.needsVisionProcessing) {
				// TODO Do vision processing, see if we have a cube!
				this.driver.setVisionResults(this.vision.processVision());
				return;
			}
			
			// If we're done with this state, move on.
			if (as.stateComplete) {
				this.state = as.nextState;
				this.enteringState = true;
				return;
			}
			
			// Otherwise, just let the driver do what they do.
			this.driver.executeTasks();
		}
	}
	
	@Override
	public void teleopInit() {
		// Let the user take control; reset stuff operating during Auto to sane values.
		this.drive.stopMotor();
	}
	
	@Override
	public void teleopPeriodic() {
		// Get joystick input
		double x = this.joystick.getX(Hand.kLeft);
		double y = this.joystick.getY(Hand.kLeft);
		double z = this.joystick.getX(Hand.kRight);
		
		// Drive
		this.drive.driveCartesian(y, x, z);
		
		// Operate lift
		
		// Operate claws
		
		// 
		
	}
}