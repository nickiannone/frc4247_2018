package org.usfirst.frc.team4247.robot.parts.impl;

import org.usfirst.frc.team4247.robot.parts.IDriverStation;

public class DriverStation implements IDriverStation {
	
	@Override
	public String getGameSpecificMessage() {
		return edu.wpi.first.wpilibj.DriverStation.getInstance().getGameSpecificMessage();
	}

	@Override
	public Alliance getAlliance() {
		return Alliance.valueOf(edu.wpi.first.wpilibj.DriverStation.getInstance().getAlliance().name().toUpperCase());
	}
	
	@Override
	public int getLocation() {
		return edu.wpi.first.wpilibj.DriverStation.getInstance().getLocation();
	}
}
