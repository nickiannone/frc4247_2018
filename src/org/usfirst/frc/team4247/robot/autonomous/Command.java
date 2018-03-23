package org.usfirst.frc.team4247.robot.autonomous;

public class Command {
	public double duration;
	public double x;
	public double y;
	public double zRot;
	
	public Command(double duration, double x, double y, double zRot) {
		this.duration = duration;
		this.x = x;
		this.y = y;
		this.zRot = zRot;
	}
}
