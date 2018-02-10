package org.usfirst.frc.team4247.robot.parts;

public interface ICamera {
	public void init();
	
	public IImage getImage() throws TimeoutException;
	public void putImage(IImage image);
}
