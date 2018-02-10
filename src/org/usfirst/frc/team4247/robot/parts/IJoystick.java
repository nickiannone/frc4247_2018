package org.usfirst.frc.team4247.robot.parts;

public interface IJoystick {

	public enum Axis {
		LEFT_X(1),
		LEFT_Y(2),
		SHOULDER(3),
		RIGHT_X(4),
		RIGHT_Y(5),
		DPAD(6);
		
		public int id;
		
		Axis(int id) {
			this.id = id;
		}
	}
	
	public enum Button {
		A(1),
		B(2),
		X(3),
		Y(4),
		LB(5),
		RB(6),
		BACK(7),
		START(8),
		L3(9),
		R3(10);
		
		public int id;
		
		Button(int id) {
			this.id = id;
		}
	}
	
	public int getPort();
	public double getRawAxis(Axis a);
	public boolean getButton(Button b);
}
