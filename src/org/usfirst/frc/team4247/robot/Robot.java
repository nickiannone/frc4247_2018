package org.usfirst.frc.team4247.robot;

import java.util.List;

import org.usfirst.frc.team4247.robot.autonomous.AutoState;
import org.usfirst.frc.team4247.robot.autonomous.Driver;
import org.usfirst.frc.team4247.robot.autonomous.FieldMap;
import org.usfirst.frc.team4247.robot.autonomous.Navigator;
import org.usfirst.frc.team4247.robot.autonomous.State;
import org.usfirst.frc.team4247.robot.autonomous.Task;
import org.usfirst.frc.team4247.robot.autonomous.FieldMap.StartPosition;
import org.usfirst.frc.team4247.robot.parts.IDrive;
import org.usfirst.frc.team4247.robot.parts.IJoystick;
import org.usfirst.frc.team4247.robot.parts.IRobotParts;
import org.usfirst.frc.team4247.robot.parts.impl.RobotParts;
import org.usfirst.frc.team4247.robot.vision.VisionProcessor;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.drive.MecanumDrive;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class Robot extends IterativeRobot implements IRobot {
	
	private IRobotParts robotParts;
	
	// TODO Wrap these in IRobotParts!
	// Robot controls - keep public!
	public Timer timer = new Timer();
	//...
	
	public MecanumDrive drive;
	public WPI_TalonSRX liftMotor;
	public Joystick joystick = new Joystick(0);
	
	
	
	// Vision values
	public VisionProcessor vision;
	
	// Autonomous values
	private State state = State.START;
	private boolean enteringState = true;
	private int frameCounter;
	
	public Navigator navigator;
	public Driver driver;
	public FieldMap fieldMap;
	
	/* (non-Javadoc)
	 * @see org.usfirst.frc.team4247.robot.IRobot#robotInit()
	 */
	@Override
	public void robotInit() {
		// Set up the controls
		robotParts = new RobotParts();
		
		// Drive system
		WPI_TalonSRX frontLeft = new WPI_TalonSRX(1);
		WPI_TalonSRX frontRight = new WPI_TalonSRX(2);
		WPI_TalonSRX backLeft = new WPI_TalonSRX(3);
		WPI_TalonSRX backRight = new WPI_TalonSRX(4);
		
		backLeft.setInverted(true);
		frontRight.setInverted(true);
		// TODO Invert any of the Talons here!
		this.drive = new MecanumDrive(frontLeft, backLeft, frontRight, backRight);
		
		// Lift system
		this.liftMotor = new WPI_TalonSRX(5);
		// TODO Invert if needed!
		
		// Pneumatics
		
		// Sensors
		
		// Camera
		
		// Set up vision processing
		this.vision = new VisionProcessor(/* camera */);
	}
	
	/* (non-Javadoc)
	 * @see org.usfirst.frc.team4247.robot.IRobot#robotPeriodic()
	 */
	@Override
	public void robotPeriodic() {
		// Compressor?
	}
	
	/* (non-Javadoc)
	 * @see org.usfirst.frc.team4247.robot.IRobot#disabledInit()
	 */
	@Override
	public void disabledInit() {
		// Disable the bot
	}
	
	/* (non-Javadoc)
	 * @see org.usfirst.frc.team4247.robot.IRobot#disabledPeriodic()
	 */
	@Override
	public void disabledPeriodic() {
		// Do something cool with lights and stuff, I dunno
	}
	
	/* (non-Javadoc)
	 * @see org.usfirst.frc.team4247.robot.IRobot#autonomousInit()
	 */
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
		
		// TODO Use a switch, analog input, or NetworkTables or something to figure out what side we're on!
		StartPosition position = StartPosition.LEFT;
		
		this.fieldMap = new FieldMap(gsc, position);
		
		// Set up the Navigator and the Driver
		this.navigator = new Navigator(this);
		this.driver = new Driver(this);
	}
	
	/* (non-Javadoc)
	 * @see org.usfirst.frc.team4247.robot.IRobot#autonomousPeriodic()
	 */
	@Override
	public void autonomousPeriodic() {
		this.frameCounter++;
		if (this.enteringState) {
			List<Task> tasks = this.navigator.generateInitialTasks(this.state);
			this.driver.setTasks(tasks);
		} else {
			// Get the position updates from the driver
			AutoState as = this.driver.getAutoState(this.frameCounter);
			
			// Let the navigator see the state, and 
			this.navigator.updateFieldMap(this.fieldMap, as);
			
			// If we need to find a new plan
			if (as.needsReevaluation) {
				List<Task> tasks = this.navigator.reevaluateTasks(this.state, as);
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
	
	/* (non-Javadoc)
	 * @see org.usfirst.frc.team4247.robot.IRobot#teleopInit()
	 */
	@Override
	public void teleopInit() {
		// Let the user take control; reset stuff operating during Auto to sane values.
		this.drive.stopMotor();
	}
	
	/* (non-Javadoc)
	 * @see org.usfirst.frc.team4247.robot.IRobot#teleopPeriodic()
	 */
	@Override
	public void teleopPeriodic() {
		IJoystick joystick = this.robotParts.getJoystick();
		IDrive drive = this.robotParts.getMecanumDrive();
		
		// Get joystick input
		double x = joystick.getRawAxis(IJoystick.Axis.LEFT_X);
		double y = joystick.getRawAxis(IJoystick.Axis.LEFT_Y);
		double z = joystick.getRawAxis(IJoystick.Axis.RIGHT_X);
		
		// Drive
		drive.driveCartesian(y, x, z);
		
		// Operate lift
		
		// Operate pneumatic claws
		
		// 
		
	}

	@Override
	public IRobotParts getRobotParts() {
		return robotParts;
	}
}