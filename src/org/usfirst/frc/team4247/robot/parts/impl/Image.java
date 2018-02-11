package org.usfirst.frc.team4247.robot.parts.impl;

import org.opencv.core.Mat;
import org.usfirst.frc.team4247.robot.parts.IImage;

public class Image implements IImage {
	
	private String name;
	private Mat image;
	private long frameTime;

	public Image(String name, Mat image, long frameTime) {
		this.name = name;
		this.image = image;
		this.frameTime = frameTime;
	}

	@Override
	public String getName() {
		return this.name;
	}
	
	@Override
	public int getWidth() {
		return this.image.width();
	}

	@Override
	public int getHeight() {
		return this.image.height();
	}

	@Override
	public Object getCVCoreImage() {
		return image;
	}
	
	@Override
	public long getFrameTime() {
		return frameTime;
	}
}
