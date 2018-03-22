package org.usfirst.frc.team4247.robot.autonomous;

public class LiftGrabSolver implements ILiftSolver {
	
	private static final double IDLE = 100.0;
	
	private static final double LIFT_UP_SPEED = 0.8;
	private static final double LIFT_DOWN_SPEED = -0.6;
	private static final double LIFT_HOLD_SPEED = 0.05;
	
	private static final double TIME_TO_EXTEND = 2.0;
	private static final double TIME_TO_GRAB = 0.3;
	private static final double TIME_TO_RETRACT = 2.0;
	
	private double target = 0.0;
	
	private double timeAtTarget = 0.0;
	private double timeExtending = 0.0;
	private double timeExtended = 0.0;
	private double timeGrabbing = 0.0;
	private double timeGrabbed = 0.0;
	private double timeLifted = 0.0;
	private double timeRetracting = 0.0;
	private double timeRetracted = 0.0;
	
	@Override
	public void initLiftSolver(double target) {
		this.target = target;
	}

	@Override
	public Outputs updateLiftSolver(double dTime, double encoderPos) {
		Outputs o = new Outputs();
		
		if (timeAtTarget == 0.0) {
			// We need to drop it down low
			if (encoderPos > target + 20.0) {
				o.liftMotorOutput = LIFT_DOWN_SPEED;
			} else if (encoderPos < target - 20.0) {
				o.liftMotorOutput = LIFT_UP_SPEED;
			} else {
				o.liftMotorOutput = LIFT_HOLD_SPEED;
				timeAtTarget = dTime;
			}
			
		} else if (timeExtended == 0.0) {
			// Open up and stick it out
			o.openClaw = true;
			if (timeExtending == 0.0) {
				o.extendGrabber = true;
				timeExtending = dTime;
			} else if (dTime < timeExtending + TIME_TO_EXTEND) {
				o.extendGrabber = true;
			} else {
				timeExtended = dTime;
			}
			
		} else if (timeGrabbed == 0.0) {
			// Grab the cube
			if (timeGrabbing == 0.0) {
				// Let the claw snap shut
				timeGrabbing = dTime;
			} else if (dTime < timeGrabbing + TIME_TO_GRAB) {
				// Let the claw snap shut
			} else {
				timeGrabbed = dTime;
			}
			
		} else if (timeLifted == 0.0) {
			// Lift the cube to the idle position
			if (encoderPos > IDLE + 20.0) {
				o.liftMotorOutput = LIFT_DOWN_SPEED;
			} else if (encoderPos < IDLE - 20.0) {
				o.liftMotorOutput = LIFT_UP_SPEED;
			} else {
				o.liftMotorOutput = LIFT_HOLD_SPEED;
				timeLifted = dTime;
			}
			
		} else if (timeRetracted == 0.0) {
			// Hold and retract
			o.liftMotorOutput = LIFT_HOLD_SPEED;
			if (timeRetracting == 0.0) {
				o.retractGrabber = true;
				timeRetracting = dTime;
			} else if (dTime < timeRetracting + TIME_TO_RETRACT) {
				o.retractGrabber = true;
			} else {
				timeRetracted = dTime;
			}
			
		} else {
			o.liftMotorOutput = LIFT_HOLD_SPEED;
			o.done = true;
		}
		
		return o;
	}

}
