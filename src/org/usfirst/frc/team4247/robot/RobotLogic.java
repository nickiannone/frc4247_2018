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

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.drive.MecanumDrive;

public class RobotLogic implements IRobotLogic {
	
	private IRobotParts parts;
	
	// Autonomous values
	private State state = State.START;
	private boolean enteringState = true;
	private int frameCounter;
	
	public RobotLogic(IRobotParts parts) {
		this.parts = parts;
	}
	
	/* (non-Javadoc)
	 * @see org.usfirst.frc.team4247.robot.IRobot#robotInit()
	 */
	@Override
	public void robotInit() {
		// Set up the controls
		robotParts = new RobotParts();
		

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
		this.parts.getTimer().start();
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
			
			// Let the navigator see the state, and update the field map accordingly
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
				this.driver.setVisionResults(this.vision.processVision(this.robotParts.getCamera()));
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

}
