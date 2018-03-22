package org.usfirst.frc.team4247.robot.parts.impl;

import org.usfirst.frc.team4247.robot.RobotUtils;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.drive.Vector2d;
import edu.wpi.first.wpilibj.hal.HAL;
import edu.wpi.first.wpilibj.hal.FRCNetComm.tInstances;
import edu.wpi.first.wpilibj.hal.FRCNetComm.tResourceType;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;

public class MyMecanumDrive extends MecanumDrive {
	
	private double frontLeftSpeed = 0.0;
	private double frontRightSpeed = 0.0;
	private double rearLeftSpeed = 0.0;
	private double rearRightSpeed = 0.0;
	
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
	
	private static final double MAX_DELTA = 0.2;
	
	@Override
	public void driveCartesian(double ySpeed, double xSpeed, double zRotation, double gyroAngle) {
		if (!m_reported) {
			HAL.report(tResourceType.kResourceType_RobotDrive, 4,
					tInstances.kRobotDrive_MecanumCartesian);
			m_reported = true;
		}
		
		// NOTE Invert the X- and Y- speed because we swapped the motors on accident
		ySpeed = -ySpeed;
		
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
		
		double newFrontLeftSpeed = wheelSpeeds[MotorType.kFrontLeft.value];
		double newFrontRightSpeed = wheelSpeeds[MotorType.kFrontRight.value];
		double newRearLeftSpeed = wheelSpeeds[MotorType.kRearLeft.value];
		double newRearRightSpeed = wheelSpeeds[MotorType.kRearRight.value];
		
		// Debounce the speeds
		frontLeftSpeed = RobotUtils.debounce(newFrontLeftSpeed, frontLeftSpeed, MAX_DELTA);
		frontRightSpeed = RobotUtils.debounce(newFrontRightSpeed, frontRightSpeed, MAX_DELTA);
		rearLeftSpeed = RobotUtils.debounce(newRearLeftSpeed, rearLeftSpeed, MAX_DELTA);
		rearRightSpeed = RobotUtils.debounce(newRearRightSpeed, rearRightSpeed, MAX_DELTA);
		
		m_frontLeftMotor.set(frontLeftSpeed * m_maxOutput);
		m_frontRightMotor.set(frontRightSpeed * m_maxOutput);
		m_rearLeftMotor.set(rearLeftSpeed * m_maxOutput);
		m_rearRightMotor.set(rearRightSpeed * m_maxOutput);
		
		m_safetyHelper.feed();
	}

}
