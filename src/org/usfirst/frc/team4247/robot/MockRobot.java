package org.usfirst.frc.team4247.robot;

import org.usfirst.frc.team4247.robot.parts.IRobotParts;
import org.usfirst.frc.team4247.robot.parts.mock.MockRobotParts;

public class MockRobot implements IRobot {
	
	private IRobotParts robotParts;
	private IRobotLogic robotLogic;
	
	public MockRobot() {
		this.robotParts = new MockRobotParts();
		this.robotLogic = new RobotLogic(this.robotParts);
	}

	@Override
	public IRobotParts getRobotParts() {
		return this.robotParts;
	}

	@Override
	public IRobotLogic getRobotLogic() {
		return this.robotLogic;
	}

	@Override
	public void robotInit() {
		this.robotLogic.robotInit();
	}

	@Override
	public void robotPeriodic() {
		this.robotLogic.robotPeriodic();
	}

	@Override
	public void autonomousInit() {
		this.robotLogic.autonomousInit();
	}

	@Override
	public void autonomousPeriodic() {
		this.robotLogic.autonomousPeriodic();
	}

	@Override
	public void teleopInit() {
		this.robotLogic.teleopInit();
	}

	@Override
	public void teleopPeriodic() {
		this.robotLogic.teleopPeriodic();
	}

	@Override
	public void disabledInit() {
		this.robotLogic.disabledInit();
	}

	@Override
	public void disabledPeriodic() {
		this.robotLogic.disabledPeriodic();
	}
}
