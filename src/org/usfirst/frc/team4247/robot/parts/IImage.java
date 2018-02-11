package org.usfirst.frc.team4247.robot.parts;

/**
 * A wrapper for the CV `Mat` class.
 */
public interface IImage {
	public String getName();
	public int getWidth();
	public int getHeight();
	
	public Object getCVCoreImage();
	public long getFrameTime();
}
