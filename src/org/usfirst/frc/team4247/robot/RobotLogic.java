package org.usfirst.frc.team4247.robot;

import java.util.List;

import org.usfirst.frc.team4247.robot.autonomous.AutoState;
import org.usfirst.frc.team4247.robot.autonomous.Driver;
import org.usfirst.frc.team4247.robot.autonomous.FieldMap;
import org.usfirst.frc.team4247.robot.autonomous.Navigator;
import org.usfirst.frc.team4247.robot.autonomous.State;
import org.usfirst.frc.team4247.robot.autonomous.Task;
import org.usfirst.frc.team4247.robot.autonomous.FieldMap.StartPosition;
import org.usfirst.frc.team4247.robot.parts.IAccelerometer;
import org.usfirst.frc.team4247.robot.parts.ICamera;
import org.usfirst.frc.team4247.robot.parts.IDrive;
import org.usfirst.frc.team4247.robot.parts.IDriverStation;
import org.usfirst.frc.team4247.robot.parts.IEncoder;
import org.usfirst.frc.team4247.robot.parts.IGyro;
import org.usfirst.frc.team4247.robot.parts.IJoystick;
import org.usfirst.frc.team4247.robot.parts.IJoystick.POV;
import org.usfirst.frc.team4247.robot.parts.IPairedSolenoid.Position;
import org.usfirst.frc.team4247.robot.parts.IMotor;
import org.usfirst.frc.team4247.robot.parts.IPairedSolenoid;
import org.usfirst.frc.team4247.robot.parts.IPneumatics;
import org.usfirst.frc.team4247.robot.parts.IRobotParts;
import org.usfirst.frc.team4247.robot.parts.ISmartDashboard;
import org.usfirst.frc.team4247.robot.parts.ITimer;
import org.usfirst.frc.team4247.robot.vision.VisionProcessor;

public class RobotLogic implements IRobotLogic {
	
	private static double CLIMB_SPEED = -1.0;
	private static double LIFT_UP_SPEED = 0.8;
	private static double LIFT_DOWN_SPEED = -0.6;
	
	private static double MAX_LIFT_DISTANCE = 3800.0;
	
	private static boolean AUTO_CLIMB_EXTEND = false;
	
	// TODO Figure out timings for raising the climber!
	private static double NEAR_END_OF_MATCH = 100.0; // seconds since match start
	private static double TIME_TO_RAISE_CLIMBER = 28.0; // seconds to raise climber
	private static double TIME_TO_CLIMB_A_FOOT = 15.0;
	
	private IRobotParts parts;
	
	// Autonomous values
	private State state = State.START;
	private boolean enteringState = true;
	private int frameCounter;

	private FieldMap fieldMap;
	private Navigator navigator;
	private Driver driver;
	private VisionProcessor vision;
	
	public RobotLogic(IRobotParts parts) {
		this.parts = parts;
	}
	
	@Override
	public void robotInit() {
		ISmartDashboard dashboard = this.parts.getSmartDashboard();
		dashboard.setAutoSelectorOptions(new String[] {
			"Left",
			"Center",
			"Right"
		});
		
		ICamera camera = this.parts.getCamera();
		camera.init();
		
		IEncoder liftEncoder = this.parts.getLiftEncoder();
		liftEncoder.reset();
	}
	
	@Override
	public void robotPeriodic() {
		// Report gyro/accel position?
		IGyro gyro = this.parts.getGyro();
		ISmartDashboard dashboard = this.parts.getSmartDashboard();
		dashboard.setNumber("Gyro", gyro.getAngle());
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
		return;/*
		
		// Move into START state
		this.state = State.START;
		this.enteringState = true;
		
		// Start the timer
		this.parts.getTimer().start();
		this.frameCounter = 0;
		
		// Get the GSC from the field, and reinitialize the FieldMap
		String gsc = this.parts.getDriverStation().getGameSpecificMessage();
		
		ISmartDashboard dashboard = this.parts.getSmartDashboard();
		String autoMode = dashboard.getAutoSelectorOption();
		StartPosition position;
		switch (autoMode) {
		case "Left":
			position = StartPosition.LEFT;
			break;
		case "Center":
			position = StartPosition.CENTER;
			break;
		case "Right":
		default:
			position = StartPosition.RIGHT;
			break;
		}
		
		this.fieldMap = new FieldMap(gsc, position);
		
		// Set up the Navigator and the Driver
		this.navigator = new Navigator(this.fieldMap);
		this.driver = new Driver(this.parts, this.fieldMap);
		*/
	}
	
	@Override
	public void autonomousPeriodic() {
		return;
		
/*		
		this.frameCounter++;
		if (this.enteringState) {
			List<Task> tasks = this.navigator.generateInitialTasks(this.state);
			this.driver.setTasks(tasks);
		} else {
			// Get the position updates from the driver
			AutoState as = this.driver.getAutoState(this.frameCounter);
			
			// Let the navigator see the state, and update the field map accordingly
			this.navigator.updateFieldMap(as);
			
			// If we need to find a new plan
			if (as.needsReevaluation) {
				List<Task> tasks = this.navigator.reevaluateTasks(this.state, as);
				this.driver.setTasks(tasks);
				return;
			}
			
			// If we need to run vision processing
			if (as.needsVisionProcessing) {
				// TODO Do vision processing, see if we have a cube!
				this.parts.getMecanumDrive().feedWatchdog();
				this.driver.setVisionResults(this.vision.processVision(this.parts.getCamera()));
				this.parts.getMecanumDrive().feedWatchdog();
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
		*/
	}
	
	@Override
	public void teleopInit() {
		// Let the user take control; reset stuff operating during Auto to sane values.
		
		// Stop the drive
		this.parts.getMecanumDrive().stopDrive();
		
		// Enable pneumatics if not already enabled
		IPneumatics pneumatics = this.parts.getPneumatics();
		if (!pneumatics.isClosedLoopControlEnabled()) {
			pneumatics.setClosedLoopControl(true);
		}
	}
	
	@Override
	public void teleopPeriodic() {
		IJoystick joystick = this.parts.getJoystick();
		IDrive drive = this.parts.getMecanumDrive();
		IMotor liftMotor = this.parts.getLiftMotor();
		IEncoder liftEncoder = this.parts.getLiftEncoder();
		IGyro gyro = this.parts.getGyro();
		IAccelerometer accelerometer = this.parts.getAccelerometer();
		IMotor climbMotor = this.parts.getClimbMotor();
		ITimer timer = this.parts.getTimer();
		IPneumatics pneumatics = this.parts.getPneumatics();
		IPairedSolenoid claw = this.parts.getClaw();
		IPairedSolenoid grabber = this.parts.getGrabber();
		ISmartDashboard dashboard = this.parts.getSmartDashboard();
		
		// Get joystick input
		double x = joystick.getRawAxis(IJoystick.Axis.LEFT_X);
		double y = joystick.getRawAxis(IJoystick.Axis.LEFT_Y);
		double z = joystick.getRawAxis(IJoystick.Axis.RIGHT_X);
		boolean liftUp = joystick.getPOVDirection() == POV.UP;
		boolean liftDown = joystick.getPOVDirection() == POV.DOWN;
		boolean openClaw = joystick.getButton(IJoystick.Button.A);
		boolean extendClimb = joystick.getButton(IJoystick.Button.Y);
		boolean retractClimb = joystick.getButton(IJoystick.Button.B);
		boolean extendGrabber = joystick.getButton(IJoystick.Button.RT);
		boolean retractGrabber = joystick.getButton(IJoystick.Button.LT);
		
		// Feed out different values to driver station
		dashboard.setNumber("liftEncoderDistance", liftEncoder.getDistance());
		dashboard.setNumber("gyroAngle", gyro.getAngle());
		dashboard.setNumber("accelerometerX", accelerometer.getX());
		dashboard.setNumber("accelerometerY", accelerometer.getY());
		dashboard.setNumber("accelerometerZ", accelerometer.getZ());
		dashboard.setNumber("gyroRate", gyro.getRate());
		
		// Drive
		drive.driveCartesian(y, x, z);
		
		// Operate lift TODO - Calculate required speed of lift motor based on position of lift!
		if (liftUp && liftEncoder.getDistance() < MAX_LIFT_DISTANCE) {
			liftMotor.set(LIFT_UP_SPEED);
		} else if (liftDown && liftEncoder.getDistance() > 0.0) {
			liftMotor.set(LIFT_DOWN_SPEED);
		} else {
			liftMotor.set(0.0);
		}
		
		// Operate grabber
		if (extendGrabber) {
			grabber.setPosition(Position.ENGAGED_1);
		} else if (retractGrabber) {
			grabber.setPosition(Position.ENGAGED_2);
		} else {
			grabber.setPosition(Position.IDLE);
		}
		
		// Operate claw
		if (openClaw) {
			claw.setPosition(Position.ENGAGED_1);
		} else {
			claw.setPosition(Position.ENGAGED_2);
		}
		
		// If we're close enough to the end of the match, raise the claw
		if (AUTO_CLIMB_EXTEND) {
			// Automatic extension
			if (timer.hasPeriodPassed(NEAR_END_OF_MATCH)) {
				if (!timer.hasPeriodPassed(NEAR_END_OF_MATCH + TIME_TO_RAISE_CLIMBER)) {
					climbMotor.set(CLIMB_SPEED);
				} else if (extendClimb) {
					climbMotor.set(CLIMB_SPEED);
				} else if (retractClimb) {
					climbMotor.set(-CLIMB_SPEED);
				} else {
					climbMotor.set(0.0);
				}
			}
		} else {
			// Manual control
			if (extendClimb) {
				climbMotor.set(CLIMB_SPEED);
			} else if (retractClimb) {
				climbMotor.set(-CLIMB_SPEED);
			} else {
				climbMotor.set(0.0);
			}
		}
	}

}
