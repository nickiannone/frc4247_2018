package org.usfirst.frc.team4247.robot.autonomous;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The FieldMap class is used for mapping the best path
 * through the field, using an approximate A* algorithm.
 * 
 * @author NickIannone
 */
public class FieldMap {
	
	// The number of inches per field unit. Corresponds to roughly half a cube's width.
	// Keep in mind that cubes are *slightly* less that 2 units tall; this will be
	// important when it comes to picking up a cube.
	// ONLY USED FOR TRANSLATING INTO AND OUT OF XY COORDINATES
	// VERTICAL HEIGHT IS ALWAYS MEASURED IN INCHES BECAUSE THE GRID DOESN'T USE IT!
	public static final float INCHES_PER_UNIT = 6.5f;
	
	// TODO List the switch and scale heights in inches!
	public static final int SWITCH_HEIGHT = 0;
	public static final int SCALE_HEIGHT = 0;
	
	// These JUST SO HAPPEN to work out to within an inch or two of the right values.
	public static final int MAX_X = 50;
	public static final int MAX_Y = 100;
	
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
	}
	
	public class Position {
		public Position(double x, double y) {
			this.x = x;
			this.y = y;
		}
		
		public double x;
		public double y;
	}
	
	/**
	 * The TargetType enum represents the different types of
	 * targets that we can represent on the map. The base types
	 * are Positions (which we use to navigate), power cubes,
	 * Platform near the scale, the Switch plate, and the
	 * Scale plate. Switch Plate, Scale Plate, and Platform
	 * targets are intended to serve as representations of
	 * the safe area in which a Cube can be dropped, and as
	 * such will be located above their actual positions.
	 */
	public enum TargetType {
		POSITION,
		PLATFORM,
		SWITCH_PLATE,
		SCALE_PLATE,
		START_POINT
	}
	
	/**
	 * The Alliance enum represents the alliance that a
	 * Target corresponds to; in the case of Portals and
	 * Platforms, this is set before the match starts; in
	 * the case of Switch Plates and Scale Plates, this
	 * is assigned after the GSC is received during
	 * Auto Init based on manual indexing of the plates.
	 * Rather than using Red and Blue and having to
	 * factor in what side we're on, we just use Ours 
	 * and Theirs to represent whether to seek or avoid
	 * a specific target.
	 */
	public enum Alliance {
		NEUTRAL,
		OURS,
		THEIRS
	}
	
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
	}
	
	public enum Stack {
		NEAR,
		FAR,
		NONE
	}
	
	/**
	 * What angle to approach a cube from. When generating a map to the nearest cube,
	 * these values are added to the cube's starting location.
	 * 
	 * <ul>
	 * 	<li>ABOVE - Approach from 2 units more in the Y direction.</li>
	 *  <li>BELOW - Approach from 2 units less in the Y direction.</li>
	 *  <li>LEFT - Approach from 2 units less in the X direction.</li>
	 *  <li>RIGHT - Approach from 2 units more in the X direction.</li>
	 * </ul>
	 */
	public enum Approach {
		ABOVE(0, 2),
		BELOW(0, -2),
		LEFT(-2, 0),
		RIGHT(2, 0);
		
		Approach(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		public int x;
		public int y;
	}
	
	public enum StartPosition {
		// TODO Add these in from the bitmap!
		LEFT(new Region()),
		CENTER(new Region()),
		RIGHT(new Region());
		
		StartPosition(Region region) {
			this.region = region;
		}
		
		public Region region;
	}
	
	public class Cube {
		public Cube(Region region, Stack stack, Approach approach, float height) {
			this.region = region;
			this.stack = stack;
			this.approach = approach;
			this.height = height;
		}
		
		public Region region;
		public Stack stack;
		public Approach approach;
		public float height = 0.0f;
	}
	
	/**
	 * An obstacle to be avoided in pathfinding.
	 */
	public class Obstacle {
		public Obstacle(Region[] regions) {
			this.regions = regions;
		}
		
		public Region[] regions;
	}
	
	// =====================================================
	
	private List<Target> targets = new ArrayList<>();
	private List<Cube> cubes = new ArrayList<>();
	private List<Obstacle> obstacles = new ArrayList<>();
	private Region robotPosition = null;
	private StartPosition startPosition = null;
	
	public FieldMap(String gsc, StartPosition start) {
		// Set the robot's starting position
		this.startPosition = start;
		this.robotPosition = start.region;
		
		// Parse the game-specific code
		boolean leftNearSwitch = gsc.charAt(0) == 'L';
		boolean leftScale = gsc.charAt(1) == 'L';
		boolean leftFarSwitch = gsc.charAt(2) == 'L';
		
		// Add switches and scales
		Collections.addAll(targets,
			// Switches
			new Target(
				new Region(14, 22, 19, 28),
				leftNearSwitch ? Alliance.OURS : Alliance.THEIRS,
				TargetType.SWITCH_PLATE,
				SWITCH_HEIGHT
			),
			new Target(
				new Region(30, 22, 35, 28),
				leftNearSwitch ? Alliance.THEIRS : Alliance.OURS,
				TargetType.SWITCH_PLATE,
				SWITCH_HEIGHT
			),
			new Target(
				new Region(14, 71, 19, 77),
				leftFarSwitch ? Alliance.OURS : Alliance.THEIRS,
				TargetType.SWITCH_PLATE,
				SWITCH_HEIGHT
			),
			new Target(
				new Region(30, 71, 35, 77),
				leftFarSwitch ? Alliance.THEIRS : Alliance.OURS,
				TargetType.SWITCH_PLATE,
				SWITCH_HEIGHT
			),
			// Scales
			new Target(
				new Region(11, 46, 16, 53),
				leftScale ? Alliance.OURS : Alliance.THEIRS,
				TargetType.SCALE_PLATE,
				SCALE_HEIGHT
			),
			new Target(
				new Region(33, 46, 38, 53),
				leftScale ? Alliance.THEIRS : Alliance.OURS,
				TargetType.SCALE_PLATE,
				SCALE_HEIGHT
			)
		);
		
		// Add the first stack of cubes
		Collections.addAll(cubes, 
			new Cube(new Region(24, 16, 25, 17), Stack.NEAR, Approach.BELOW, 0),  // Bottom front center
			new Cube(new Region(23, 18, 24, 19), Stack.NEAR, Approach.BELOW, 0),  // Bottom middle left
			new Cube(new Region(25, 18, 26, 19), Stack.NEAR, Approach.BELOW, 0),  // Bottom middle right
			new Cube(new Region(22, 20, 23, 21), Stack.NEAR, Approach.BELOW, 0),  // Bottom back left
			new Cube(new Region(24, 20, 25, 21), Stack.NEAR, Approach.BELOW, 0),  // Bottom back center
			new Cube(new Region(26, 20, 27, 21), Stack.NEAR, Approach.BELOW, 0),  // Bottom back right
			new Cube(new Region(24, 18, 25, 19), Stack.NEAR, Approach.BELOW, 11), // Mid front center
			new Cube(new Region(23, 20, 24, 21), Stack.NEAR, Approach.BELOW, 11), // Mid back left
			new Cube(new Region(25, 20, 26, 21), Stack.NEAR, Approach.BELOW, 11), // Mid back right
			new Cube(new Region(24, 20, 25, 21), Stack.NEAR, Approach.BELOW, 22)  // Top
		);
		
		// TODO Add the near row of cubes
		
		// TODO Add the far row of cubes
		
		// TODO Add the far stack of cubes
		
		// TODO Add obstacles
	}

	public Position getCurrentPosition() {
		return robotPosition.getCentroid();
	}
	
	public StartPosition getStartPosition() {
		return startPosition;
	}
	
	public Target findNearestAlliedTarget() {
		Position p = this.getCurrentPosition();
		Target nearest = null;
		double distance = 1000.0;
		for (Target t : this.targets) {
			if (t.alliance == Alliance.OURS) {
				double newDistance = this.calcDistSquared(p, t.region.getCentroid());
				if (nearest == null || newDistance < distance) {
					distance = newDistance;
					nearest = t;
				}
			}
		}
		return nearest;
	}
	
	// Fast distance approximation between two points.
	public double calcDistSquared(Position a, Position b) {
		double xDiff = b.x - a.x;
		double yDiff = b.y - a.y;
		return (xDiff * xDiff) + (yDiff * yDiff);
	}
}
