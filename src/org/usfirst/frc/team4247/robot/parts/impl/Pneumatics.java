package org.usfirst.frc.team4247.robot.parts.impl;

import org.usfirst.frc.team4247.robot.parts.IPneumatics;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Solenoid;

public class Pneumatics implements IPneumatics {

	private Compressor compressor;
	private Solenoid solenoid;
	private boolean isClosedLoopControl;
	
	public Pneumatics(int pcmCANId, int solenoidChannel) {
		this.compressor = new Compressor(pcmCANId);
		this.solenoid = new Solenoid(pcmCANId, solenoidChannel);
		this.compressor.setClosedLoopControl(true);
		this.isClosedLoopControl = true;
	}

	@Override
	public void setClosedLoopControl(boolean on) {
		this.compressor.setClosedLoopControl(on);
		this.isClosedLoopControl = on;
	}
	
	@Override
	public boolean isClosedLoopControlEnabled() {
		return this.isClosedLoopControl;
	}

	@Override
	public boolean isCompressorEnabled() {
		return this.compressor.enabled();
	}

	@Override
	public boolean isCompressorPressureSwitchSet() {
		return this.compressor.getPressureSwitchValue();
	}

	@Override
	public double getCompressorCurrent() {
		return this.compressor.getCompressorCurrent();
	}

	@Override
	public void setSolenoidState(boolean open) {
		this.solenoid.set(open);
	}
}
