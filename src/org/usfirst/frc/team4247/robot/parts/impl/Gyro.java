package org.usfirst.frc.team4247.robot.parts.impl;

import org.usfirst.frc.team4247.robot.parts.IGyro;

import edu.wpi.first.wpilibj.AnalogGyro;

public class Gyro implements IGyro {

	private AnalogGyro gyro;
	
	public Gyro(int rateAnalogPort) {
		this.gyro = new AnalogGyro(rateAnalogPort);
		
		// TODO If we need to change the sensitivity, do so here!
		// this.gyro.setSensitivity(voltsPerDegreePerSecond);
	}
	
	@Override
	public void calibrate() {
		this.gyro.calibrate();
	}

	@Override
	public void reset() {
		this.gyro.reset();
	}

	@Override
	public double getAngle() {
		return this.gyro.getAngle();
	}

	@Override
	public double getRate() {
		return this.gyro.getRate();
	}
}
