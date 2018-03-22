package org.usfirst.frc.team4247.robot.autonomous;

public class LiftReleaseSolver implements ILiftSolver {

	private static final double IDLE = 100.0;
	
	private static final double LIFT_UP_SPEED = 0.8;
	private static final double LIFT_DOWN_SPEED = -0.6;
	private static final double LIFT_HOLD_SPEED = 0.05;
	
	private static final double TIME_TO_EXTEND = 2.0;
	private static final double TIME_TO_GRAB = 0.3;
	private static final double TIME_TO_RETRACT = 2.0;
	
	private double target = 0.0;
	
	private double timeLifted = 0.0;
	private double timeExtending = 0.0;
	private double timeExtended = 0.0;
	private double timeReleasing = 0.0;
	private double timeReleased = 0.0;
	private double timeRetracting = 0.0;
	private double timeRetracted = 0.0;
	private double timeDropped = 0.0;

	@Override
	public void initLiftSolver(double target) {
		this.target = target;
	}

	@Override
	public Outputs updateLiftSolver(double dTime, double encoderPos) {
		Outputs o = new Outputs();
		
		if (timeLifted == 0.0) {
			// We need to bring it on back up
			if (encoderPos > target + 20.0) {
				o.liftMotorOutput = LIFT_DOWN_SPEED;
			} else if (encoderPos < target - 20.0) {
				o.liftMotorOutput = LIFT_UP_SPEED;
			} else {
				o.liftMotorOutput = LIFT_HOLD_SPEED;
				timeLifted = dTime;
			}
			
		} else if (timeExtended == 0.0) {
			// Stick it out
			o.liftMotorOutput = LIFT_HOLD_SPEED;
			if (timeExtending == 0.0) {
				o.extendGrabber = true;
				timeExtending = dTime;
			} else if (dTime < timeExtending + TIME_TO_EXTEND) {
				o.extendGrabber = true;
			} else {
				timeExtended = dTime;
			}
			
		} else if (timeReleased == 0.0) {
			// Drop the cube
			o.openClaw = true;
			o.liftMotorOutput = LIFT_HOLD_SPEED;
			if (timeReleasing == 0.0) {
				// Let the claw sit open
				timeReleasing = dTime;
			} else if (dTime < timeReleasing + TIME_TO_GRAB) {
				// Let the claw sit open
			} else {
				timeReleased = dTime;
			}
			
		} else if (timeRetracted == 0.0) {
			// Retract
			o.liftMotorOutput = LIFT_HOLD_SPEED;
			if (timeRetracting == 0.0) {
				o.retractGrabber = true;
				timeRetracting = dTime;
			} else if (dTime < timeRetracting + TIME_TO_RETRACT) {
				o.retractGrabber = true;
			} else {
				timeRetracted = dTime;
			}
			
		} else if (timeDropped == 0.0) {
			// Return the lift to the idle position
			if (encoderPos > IDLE + 20.0) {
				o.liftMotorOutput = LIFT_DOWN_SPEED;
			} else if (encoderPos < IDLE - 20.0) {
				o.liftMotorOutput = LIFT_UP_SPEED;
			} else {
				o.liftMotorOutput = LIFT_HOLD_SPEED;
				timeDropped = dTime;
			}
			
		} else {
			o.liftMotorOutput = LIFT_HOLD_SPEED;
			o.done = true;
		}
		
		return o;
	}

}
