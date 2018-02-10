package org.usfirst.frc.team4247.robot.parts.impl;

import org.usfirst.frc.team4247.robot.parts.IMotor;

import edu.wpi.first.wpilibj.SpeedController;

public class Motor implements IMotor {
	
	private SpeedController speedController;

	public Motor(SpeedController controller) {
		this.speedController = controller;
	}

	@Override
	public void set(double speed) {
		speedController.set(speed);
	}

	@Override
	public double get() {
		return speedController.get();
	}

	@Override
	public void setInverted(boolean isInverted) {
		speedController.setInverted(isInverted);
	}

	@Override
	public boolean getInverted() {
		return speedController.getInverted();
	}

	@Override
	public void disable() {
		speedController.disable();
	}

	@Override
	public void stopMotor() {
		speedController.stopMotor();
	}
}
