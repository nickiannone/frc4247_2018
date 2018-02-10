package org.usfirst.frc.team4247.robot.parts.impl;

import org.usfirst.frc.team4247.robot.parts.ITimer;

public class Timer implements ITimer {
	
	private edu.wpi.first.wpilibj.Timer timer;
	
	public Timer() {
		this.timer = new edu.wpi.first.wpilibj.Timer();
	}

	@Override
	public void start() {
		timer.start();
	}

	@Override
	public void stop() {
		timer.stop();
	}

	@Override
	public void reset() {
		timer.reset();
	}

	@Override
	public double getMatchTime() {
		return edu.wpi.first.wpilibj.Timer.getMatchTime();
	}

	@Override
	public double get() {
		return timer.get();
	}

	@Override
	public void delay(double seconds) {
		edu.wpi.first.wpilibj.Timer.delay(seconds);
	}

	@Override
	public double getFPGATimestamp() {
		return edu.wpi.first.wpilibj.Timer.getFPGATimestamp();
	}

	@Override
	public boolean hasPeriodPassed(double period) {
		return timer.hasPeriodPassed(period);
	}

}
