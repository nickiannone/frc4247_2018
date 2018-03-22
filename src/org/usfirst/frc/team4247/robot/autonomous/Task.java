package org.usfirst.frc.team4247.robot.autonomous;

import org.usfirst.frc.team4247.robot.parts.IAccelerometer;
import org.usfirst.frc.team4247.robot.parts.IDrive;
import org.usfirst.frc.team4247.robot.parts.IRobotParts;
import org.usfirst.frc.team4247.robot.parts.ITimer;
import org.usfirst.frc.team4247.robot.parts.IEncoder;
import org.usfirst.frc.team4247.robot.parts.IGyro;
import org.usfirst.frc.team4247.robot.parts.IMotor;
import org.usfirst.frc.team4247.robot.parts.IPairedSolenoid;
import org.usfirst.frc.team4247.robot.parts.IPairedSolenoid.Position;

/**
 * The Task class represents a single operation to be performed
 * in real-time by the Driver class. These are broken down into
 * two categories:
 * 
 * <ul>
 * 	<li>Drive Commands (Drive, Strafe, Rotate)</li>
 *  <li>Manipulator Commands (Grab, Release)</li>
 * </ul>
 * 
 * The value parameter of the Task indicates distance, angle, or
 * height values to be used for task execution.
 * 
 * @author NickIannone
 */
public class Task {
	
	public TaskType type;
	public double value;
	
	// The time at which this task was entered.
	private double startTime;
	
	// The target time of completion.
	private double targetTime;
	
	// Drive solver
	private IDriveSolver driveSolver;
	private ILiftSolver liftSolver;

	public Task(TaskType type, double value) {
		this.type = type;
		this.value = value;
	}

	public enum TaskType {
		DRIVE,
		STRAFE,
		ROTATE,
		GRAB,
		RELEASE
	}
	
	public void enter(IRobotParts robotParts) {
		IDrive drive = robotParts.getMecanumDrive();
		ITimer timer = robotParts.getTimer();
		this.startTime = timer.get();
		
		// Stop the drive so we can reconfigure for the next go-round
		drive.stopDrive();
		
		switch (type) {
		case DRIVE:
			driveSolver = new RampingDriveSolver();
			liftSolver = new NoLiftSolver();
			break;
		case STRAFE:
			driveSolver = new RampingDriveSolver();
			liftSolver = new NoLiftSolver();
			break;
		case ROTATE:
			driveSolver = new RampingGyroDriveSolver(robotParts.getGyro());
			liftSolver = new NoLiftSolver();
			break;
		case GRAB:
			driveSolver = new NoDriveSolver();
			liftSolver = new LiftGrabSolver();
			break;
		case RELEASE:
			driveSolver = new NoDriveSolver();
			liftSolver = new LiftReleaseSolver();
			break;
		}
	}
	
	public boolean process(IRobotParts robotParts) {
		ITimer timer = robotParts.getTimer();
		double timeSinceStart = timer.get() - this.startTime;
		
		IDrive drive = robotParts.getMecanumDrive();
		IMotor liftMotor = robotParts.getLiftMotor();
		IPairedSolenoid claw = robotParts.getClaw();
		IPairedSolenoid grabber = robotParts.getGrabber();
		IEncoder liftEncoder = robotParts.getLiftEncoder();
		IGyro gyro = robotParts.getGyro();
		IAccelerometer accel = robotParts.getAccelerometer();

		IDriveSolver.Outputs driveOutputs = driveSolver.updateDrive(timeSinceStart, accel.getX(), accel.getY(), gyro.getAngle(), robotParts.getSmartDashboard());
		ILiftSolver.Outputs outputs = liftSolver.updateLiftSolver(timeSinceStart, liftEncoder.getDistance());
		
		switch (type) {
		case DRIVE:
		case STRAFE:
		case ROTATE:
			drive.driveCartesian(driveOutputs.y, driveOutputs.x, driveOutputs.zRot);
			return driveOutputs.done;
		case GRAB:
		case RELEASE:
			drive.driveCartesian(outputs.y, outputs.x, outputs.zRot);
			
			liftMotor.set(outputs.liftMotorOutput);
			
			if (outputs.openClaw) {
				claw.setPosition(Position.ENGAGED_1);
			} else {
				claw.setPosition(Position.ENGAGED_2);
			}
			
			if (outputs.extendGrabber) {
				grabber.setPosition(Position.ENGAGED_1);
			} else if (outputs.retractGrabber) {
				grabber.setPosition(Position.ENGAGED_2);
			} else {
				grabber.setPosition(Position.IDLE);
			}

			return outputs.done;
		default:
			return true;
		}
	}
}
