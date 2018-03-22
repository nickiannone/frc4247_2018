package org.usfirst.frc.team4247.robot.autonomous;

import org.usfirst.frc.team4247.robot.RobotUtils;
import org.usfirst.frc.team4247.robot.parts.ISmartDashboard;

public class RampingDriveSolver implements IDriveSolver {

	// TODO Figure out a reasonable distance metric for this!
	private static final double CLOSE_ENOUGH = 1.0;
	
	// TODO Figure out a reasonable rotation speed metric for this!
	private static final double FIX_ANGLE = 0.2;
	
	// TODO Scale output to convert (and invert) delta distance to motor output.
	private static final double X_SCALING_FACTOR = -0.2;
	private static final double Y_SCALING_FACTOR = -0.2;
	
	private double goalDistance = 0.0;
	private DriveDirection direction;
	
	private double velXLocal, velYLocal;
	private double refAngle;
	private double posX, posY;
	
	private double targetX = 0.0;
	private double targetY = 0.0;
	
	@Override
	public void initDrive(double goal, DriveDirection direction) {
		this.goalDistance = goal;
		this.direction = direction;
		
		velXLocal = 0.0;
		velYLocal = 0.0;
		refAngle = 0.0;
		posX = 0.0;
		posY = 0.0;
		
		if (direction == DriveDirection.DRIVE) {
			targetY = goal;
			targetX = 0.0;
		} else if (direction == DriveDirection.STRAFE) {
			targetY = 0.0;
			targetX = goal;
		}
	}

	@Override
	public Outputs updateDrive(double dTime, double accelX, double accelY, double gyroAngle, ISmartDashboard dashboard) {
		Outputs o = new Outputs();
		if (refAngle == 0.0) {
			// Get the initial angle
			refAngle = gyroAngle;
		}
		
		// Add the local accelerations
		velXLocal += accelX;
		velYLocal += accelY;
		
		// Translate into ref coords by the current angle
		double angle = RobotUtils.deltaAngle(refAngle, gyroAngle);
		
		// TODO Verify my trig here?
		posX += (velXLocal * RobotUtils.cosDegs(angle)) + (velYLocal * RobotUtils.sinDegs(angle));
		posY += (velXLocal * RobotUtils.sinDegs(angle)) + (velYLocal * RobotUtils.cosDegs(angle));
		
		// Try and correct angle first
		if (angle > refAngle + 1.0) {
			o.zRot = -FIX_ANGLE;
		} else if (angle < refAngle - 1.0) {
			o.zRot = FIX_ANGLE;
		}
		
		// Afterwards, try and correct position
		double deltaX = targetX - posX;
		double deltaY = targetY - posY;
		
		// Calculate the distances.
		double maxDistance = Math.sqrt((targetX * targetX) + (targetY * targetY));
		double currentDistance = Math.sqrt((deltaX * deltaX) + (deltaY * deltaY));
		double percentage = currentDistance / maxDistance;
		
		// Update the dashboard.
		dashboard.setNumber("driveSolverPosX", posX);
		dashboard.setNumber("driveSolverPosY", posY);
		dashboard.setNumber("driveSolverDeltaX", deltaX);
		dashboard.setNumber("driveSolverDeltaY", deltaY);
		
		// TODO Ramp based on percentage!
		
		// Correct outputs based on a proportion of delta.
		o.x = RobotUtils.clamp(deltaX * X_SCALING_FACTOR, -1.0, 1.0);
		o.y = RobotUtils.clamp(deltaY * Y_SCALING_FACTOR, -1.0, 1.0);
		
		// If we're close enough, end.
		if (Math.abs(deltaX) < CLOSE_ENOUGH && Math.abs(deltaY) < CLOSE_ENOUGH) {
			o.done = true;
		}
		
		return o;
	}

}
