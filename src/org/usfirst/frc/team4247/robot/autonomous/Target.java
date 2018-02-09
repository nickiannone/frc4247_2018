package org.usfirst.frc.team4247.robot.autonomous;

import org.usfirst.frc.team4247.robot.autonomous.FieldMap.Alliance;
import org.usfirst.frc.team4247.robot.autonomous.FieldMap.TargetType;

/**
 * The Target class represents a specific scoring region
 * within the Arcade field. These could be 
 */
public class Target {
	public Target(Region region, Alliance alliance, TargetType type, int height) {
		this.region = region;
		this.alliance = alliance;
		this.type = type;
		this.height = height;
	}
	
	public Target(Region region, Alliance alliance, TargetType type) {
		this.region = region;
		this.alliance = alliance;
		this.type = type;
	}
	
	public Region region;
	public Alliance alliance;
	public TargetType type;
	public int height = 0; // Inches off the ground
	
	public Region findClosestApproachPositionTo(Position currentPosition) {
		// TODO Auto-generated method stub
		return null;
	}
}