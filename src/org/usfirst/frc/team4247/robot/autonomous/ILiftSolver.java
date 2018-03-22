package org.usfirst.frc.team4247.robot.autonomous;

public interface ILiftSolver {
	
	public class Outputs {
		public double x = 0.0;
		public double y = 0.0;
		public double zRot = 0.0;
		public double liftMotorOutput = 0.0;
		public boolean openClaw = false;
		public boolean extendGrabber = false;
		public boolean retractGrabber = false;
		public boolean done = false;
	}

	public void initLiftSolver(double target);
	public Outputs updateLiftSolver(double dTime, double encoderPos);
}
