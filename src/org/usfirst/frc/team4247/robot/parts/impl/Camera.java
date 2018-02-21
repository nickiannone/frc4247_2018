package org.usfirst.frc.team4247.robot.parts.impl;

import org.opencv.core.Mat;
import org.usfirst.frc.team4247.robot.parts.ICamera;
import org.usfirst.frc.team4247.robot.parts.IImage;
import org.usfirst.frc.team4247.robot.parts.TimeoutException;

import edu.wpi.cscore.AxisCamera;
import edu.wpi.cscore.CvSource;
import edu.wpi.first.wpilibj.CameraServer;

public class Camera implements ICamera {
	
	private AxisCamera axisCamera;

	@Override
	public void init() {
		// TODO If this is already configured, discard the previous config and reconnect?
		axisCamera = CameraServer.getInstance().addAxisCamera("10.42.47.11"); // TODO Make the team number configurable?
		CameraServer.getInstance().startAutomaticCapture(axisCamera);
	}

	@Override
	public IImage getImage() throws TimeoutException {
		Mat image = new Mat(); // TODO Configure this with default frame settings?
		long frameTime = CameraServer.getInstance().getVideo(axisCamera).grabFrame(image);
		return new Image("Camera", image, frameTime);
	}

	@Override
	public void putImage(IImage image) {
		CvSource source = CameraServer.getInstance().putVideo(image.getName(), image.getWidth(), image.getHeight());
		try {
			source.putFrame((Mat) image.getCVCoreImage());
		} catch (ClassCastException ex) {
			// Just swallow the error
			return;
		}
	}
}
