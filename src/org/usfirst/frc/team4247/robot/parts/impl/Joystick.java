package org.usfirst.frc.team4247.robot.parts.impl;

import org.usfirst.frc.team4247.robot.parts.IJoystick;

public class Joystick implements IJoystick {
	
	private edu.wpi.first.wpilibj.Joystick joystick;
	
	public Joystick(int port) {
		this.joystick = new edu.wpi.first.wpilibj.Joystick(port);
	}

	@Override
	public double getRawAxis(Axis a) {
		return this.joystick.getRawAxis(a.id);
	}

	@Override
	public boolean getButton(Button b) {
		return this.joystick.getRawButton(b.id);
	}

	@Override
	public int getPort() {
		return this.joystick.getPort();
	}

	@Override
	public POV getPOVDirection() {
		int direction = this.joystick.getPOV();
		for (POV pov : POV.values()) {
			if (pov.value == direction) {
				return pov;
			}
		}
		return POV.CENTER;
	}

}
