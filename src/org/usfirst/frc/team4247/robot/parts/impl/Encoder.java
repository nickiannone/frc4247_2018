package org.usfirst.frc.team4247.robot.parts.impl;

import org.usfirst.frc.team4247.robot.parts.IEncoder;

public class Encoder implements IEncoder {

	private edu.wpi.first.wpilibj.Encoder encoder;
	private boolean reverseDirection;
	
	public Encoder(int channelA, int channelB, boolean reverse) {
		this.encoder = new edu.wpi.first.wpilibj.Encoder(channelA, channelB, reverse);
		this.reverseDirection = reverse;
	}
	
	@Override
	public int get() {
		return this.encoder.get();
	}
	
	@Override
	public boolean getDirection() {
		return this.encoder.getDirection();
	}
	
	@Override
	public void reset() {
		this.encoder.reset();
	}
	
	@Override
	public double getRate() {
		return this.encoder.getRate();
	}
	
	@Override
	public double getDistance() {
		return this.encoder.getDistance();
	}
	
	@Override
	public void setReverseDirection(boolean reverseDirection) {
		this.encoder.setReverseDirection(reverseDirection);
		this.reverseDirection = reverseDirection;
	}
	
	@Override
	public boolean getReverseDirection() {
		return this.reverseDirection;
	}
	
	@Override
	public void setDistancePerPulse(double distance) {
		this.encoder.setDistancePerPulse(distance);
	}
	
	@Override
	public double getDistancePerPulse() {
		return this.encoder.getDistancePerPulse();
	}
}
