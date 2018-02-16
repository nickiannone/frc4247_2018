package org.usfirst.frc.team4247.robot.parts;

public interface IGyro {
	
	
	
	/**
	 * Recalibrates the gyro, and resets the gyro's heading to 0.
	 * Should be called at the start of each match when the robot is aligned and not moving.
	 */
	public void calibrate();
	
	/**
	 * Resets the gyro's heading to 0. Does not alter calibration settings;
	 * simply moves the 0-point of the heading to the current point.
	 */
	public void reset();
	
	/**
	 * Returns the gyro's current heading.
	 * @return The heading, in +/- degrees from the 0-point.
	 */
	public double getAngle();
	
	/**
	 * Returns the gyro's current rate of change.
	 * @return The rate of change, in +/- degrees per second.
	 */
	public double getRate();
}
