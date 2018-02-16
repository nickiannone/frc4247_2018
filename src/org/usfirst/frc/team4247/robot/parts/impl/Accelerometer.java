package org.usfirst.frc.team4247.robot.parts.impl;

import org.usfirst.frc.team4247.robot.parts.IAccelerometer;

import edu.wpi.first.wpilibj.ADXL345_SPI;
import edu.wpi.first.wpilibj.SPI;

public class Accelerometer implements IAccelerometer {
	
	private edu.wpi.first.wpilibj.interfaces.Accelerometer accelerometer;
	
	public Accelerometer() {
		// TODO Use I2C or SPI?
		// TODO Configure range (2G, 4G, 8G, or 16G)?
		this.accelerometer = new ADXL345_SPI(SPI.Port.kOnboardCS0, 
				edu.wpi.first.wpilibj.interfaces.Accelerometer.Range.k4G);
		
		// TODO Configure SPI-specific fields?
	}

	@Override
	public double getX() {
		return this.accelerometer.getX();
	}

	@Override
	public double getY() {
		return this.accelerometer.getY();
	}

	@Override
	public double getZ() {
		return this.accelerometer.getZ();
	}

}
