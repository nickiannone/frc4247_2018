package org.usfirst.frc.team4247.robot.parts.impl;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.drive.Vector2d;
import edu.wpi.first.wpilibj.hal.HAL;
import edu.wpi.first.wpilibj.hal.FRCNetComm.tInstances;
import edu.wpi.first.wpilibj.hal.FRCNetComm.tResourceType;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;

public class MyMecanumDrive extends MecanumDrive {
	
	public MyMecanumDrive(SpeedController frontLeftMotor, SpeedController rearLeftMotor,
            SpeedController frontRightMotor, SpeedController rearRightMotor) {
		super(frontLeftMotor, rearLeftMotor, frontRightMotor, rearRightMotor);
		m_frontLeftMotor = frontLeftMotor;
		m_frontRightMotor = frontRightMotor;
		m_rearLeftMotor = rearLeftMotor;
		m_rearRightMotor = rearRightMotor;
	}
	
	private boolean m_reported = false;

	private SpeedController m_frontLeftMotor, m_frontRightMotor, m_rearLeftMotor, m_rearRightMotor;
	
	@Override
	public void driveCartesian(double ySpeed, double xSpeed, double zRotation, double gyroAngle) {
		if (!m_reported) {
			HAL.report(tResourceType.kResourceType_RobotDrive, 4,
					tInstances.kRobotDrive_MecanumCartesian);
			m_reported = true;
		}
		
		ySpeed = limit(ySpeed);
		ySpeed = applyDeadband(ySpeed, m_deadband);
		
		xSpeed = limit(xSpeed);
		xSpeed = applyDeadband(xSpeed, m_deadband);
		
		Vector2d input = new Vector2d(ySpeed, xSpeed);
		input.rotate(-gyroAngle);
		
		double[] wheelSpeeds = new double[4];
		wheelSpeeds[MotorType.kFrontLeft.value] = input.x + input.y + zRotation;
		wheelSpeeds[MotorType.kFrontRight.value] = input.x - input.y - zRotation;
		wheelSpeeds[MotorType.kRearLeft.value] = -input.x + input.y - zRotation;
		wheelSpeeds[MotorType.kRearRight.value] = -input.x - input.y + zRotation;

		normalize(wheelSpeeds);
		
		m_frontLeftMotor.set(wheelSpeeds[MotorType.kFrontLeft.value] * m_maxOutput);
		m_frontRightMotor.set(wheelSpeeds[MotorType.kFrontRight.value] * m_maxOutput);
		m_rearLeftMotor.set(wheelSpeeds[MotorType.kRearLeft.value] * m_maxOutput);
		m_rearRightMotor.set(wheelSpeeds[MotorType.kRearRight.value] * m_maxOutput);
		
		m_safetyHelper.feed();
	}

}
