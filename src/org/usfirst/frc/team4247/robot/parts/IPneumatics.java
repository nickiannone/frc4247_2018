package org.usfirst.frc.team4247.robot.parts;

/**
 * Operates the Pneumatics (compressor and solenoid) interfaces.
 * 
 * TODO Come up with method overrides for configuring, determining, and using double solenoids!
 */
public interface IPneumatics {
	// Compressor methods
	public void setClosedLoopControl(boolean on);
	public boolean isClosedLoopControlEnabled();
	public boolean isCompressorEnabled();
	public boolean isCompressorPressureSwitchSet();
	public double getCompressorCurrent();
	
	// Solenoid methods
	public void setSolenoidState(boolean open);
}
