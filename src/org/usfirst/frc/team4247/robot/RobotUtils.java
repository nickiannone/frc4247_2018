package org.usfirst.frc.team4247.robot;

public class RobotUtils {

	public static double clampAngle(double a) {
		// TODO Come up with a better way to do this?
		while (a > 180.0) {
			a -= 360.0;
		}
		while (a < -180.0) {
			a += 360.0;
		}
		return a;
	}
	
	/**
	 * Calculates the shortest difference between two angles as a signed angle.
	 * @param a The starting angle (in [-180.0, 180.0])
	 * @param b The target angle (in [-180.0, 180.0])
	 * @return A signed angle (in degrees) that can be added to a to reach b.
	 */
	public static double deltaAngle(double a, double b) {
		// Clamp the angles into [-180.0, 180.0]
		a = clampAngle(a);
		b = clampAngle(b);
		
		// Compute the delta
		double delta = b - a;
		
		// Clamp the delta 
		return clampAngle(delta);
	}
	
	public static double sinDegs(double degs) {
		return Math.sin(degs * Math.PI / 180.0);
	}
	
	public static double cosDegs(double degs) {
		return Math.cos(degs * Math.PI / 180.0);
	}

	public static double clamp(double value, double min, double max) {
		if (value > max) {
			return max;
		} else if (value < min) {
			return min;
		} else {
			return value;
		}
	}
}
