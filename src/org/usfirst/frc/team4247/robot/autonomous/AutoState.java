package org.usfirst.frc.team4247.robot.autonomous;

/**
 * This is a neat package for all data that needs to be returned from the
 * Driver's state detection system. It provides a means for the driver to
 * feed back information from the robot to both the Autonomous algorithm
 * and the Navigator.
 */
public class AutoState {

	// Whether or not we've somehow fallen off-task and need a new set
	public boolean needsReevaluation;
	
	// Whether or not we need to check the camera for something.
	// Since vision processing is computationally expensive, we do this
	// instead of any task processing for a single frame.
	public boolean needsVisionProcessing;

	// Whether or not we've finished the current state.
	public boolean stateComplete;
	
	// Iff stateComplete, we need to know which state to go to next.
	// This is something that the driver should know, so we put it here.
	public State nextState;
	

}
