package org.usfirst.frc.team4247.robot.autonomous;

/**
 * The Region class represents an individual region of the map.
 * Regions are defined by units of half-cubes (6.5") of distance.
 */
public class Region {
	public Region(int xMin, int yMin, int xMax, int yMax) {
		this.xMin = xMin;
		this.yMin = yMin;
		this.xMax = xMax;
		this.yMax = yMax;
	}
	
	public int xMin;
	public int xMax;
	public int yMin;
	public int yMax;
	
	public Position getCentroid() {
		return new Position((double)xMax - (double)xMin, (double)yMax - (double)yMin);
	}

	public double getApproachAngle(Region region) {
		// TODO Auto-generated method stub
		return 0;
	}

	public boolean contains(Position position) {
		return (position.x >= xMin && position.x <= xMax && position.y >= yMin && position.y <= yMax);
	}
}