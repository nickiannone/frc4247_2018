package org.usfirst.frc.team4247.robot.parts.impl;

import org.usfirst.frc.team4247.robot.parts.IPairedSolenoid;

import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.Solenoid;

public class PairedSolenoid implements IPairedSolenoid {
	
	private Solenoid solenoid1;
	private Solenoid solenoid2;
	
	private Position pos;
	
	public PairedSolenoid(int pcmCANId, int port1, int port2) {
		this.solenoid1 = new Solenoid(pcmCANId, port1);
		this.solenoid2 = new Solenoid(pcmCANId, port2);
		this.reset();
	}

	@Override
	public void setPosition(Position p) {
		switch (p) {
		case IDLE:
			this.solenoid1.set(false);
			this.solenoid2.set(false);
			break;
		case ENGAGED_1:
			this.solenoid1.set(true);
			this.solenoid2.set(false);
			break;
		case ENGAGED_2:
			this.solenoid1.set(false);
			this.solenoid2.set(true);
			break;
		}
		this.pos = p;
	}

	@Override
	public Position getPosition() {
		return this.pos;
	}

	@Override
	public void reset() {
		this.setPosition(Position.IDLE);
	}

}
