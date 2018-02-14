package org.usfirst.frc.team4247.robot.parts;

public interface IJoystick {

	public enum Axis {
		LEFT_X(0, false),
		LEFT_Y(1, true),
		RIGHT_X(2, false),
		RIGHT_Y(3, true);
		
		public int id;
		public boolean inverted;
		
		Axis(int id, boolean inverted) {
			this.id = id;
			this.inverted = inverted;
		}
	}
	
	public enum Button {
		A(1),
		B(2),
		X(0),
		Y(3),
		LB(4),
		RB(5),
		LT(6),
		RT(7),
		BACK(8),
		START(9),
		LSTICK(10),
		RSTICK(11);
		
		public int id;
		
		Button(int id) {
			this.id = id;
		}
	}
	
	public enum POV {
		CENTER(-1),
		UP(0),
		DOWN(180),
		LEFT(270),
		RIGHT(90),
		UP_LEFT(315),
		UP_RIGHT(45),
		DOWN_LEFT(225),
		DOWN_RIGHT(135);
		
		public int value;
		
		POV(int value) {
			this.value = value;
		}
	}
	
	public int getPort();
	public double getRawAxis(Axis a);
	public boolean getButton(Button b);
	public POV getPOVDirection();
}
