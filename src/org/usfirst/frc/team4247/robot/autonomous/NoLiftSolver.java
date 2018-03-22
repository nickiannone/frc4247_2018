package org.usfirst.frc.team4247.robot.autonomous;

public class NoLiftSolver implements ILiftSolver {

	@Override
	public void initLiftSolver(double target) {	}

	@Override
	public Outputs updateLiftSolver(double dTime, double encoderPos) {
		return new Outputs();
	}

}
