package org.usfirst.frc.team4247.robot.parts.impl;

import org.usfirst.frc.team4247.robot.parts.IAccelerometer;
import edu.wpi.first.wpilibj.ADXL345_I2C;
import edu.wpi.first.wpilibj.I2C;

public class Accelerometer implements IAccelerometer {
	
	private edu.wpi.first.wpilibj.interfaces.Accelerometer accelerometer;
	
	public Accelerometer() {
		// TODO Use I2C or SPI?
		// TODO Configure range (2G, 4G, 8G, or 16G)?
		this.accelerometer = new ADXL345_I2C(I2C.Port.kOnboard,
				edu.wpi.first.wpilibj.interfaces.Accelerometer.Range.k4G);
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
