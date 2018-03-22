package org.usfirst.frc.team4247.robot.autonomous;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Pathfinder {

	private FieldMap fieldMap;
	
	public enum Direction {
		RIGHT,
		LEFT,
		FORWARD,
		BACKWARD
	}
	
	public class Step {
		public Direction direction;
		public Position position;
	}
	
	private class Edge {
		public Node start;
		public Node end;
		public Direction direction;
		public double h; // Heuristic of how desirable this edge is (controlled by distance from obstacles)
		public double g; // Piece-wise cost of traversal (equal to distance between edge.end and endRegion)
		
		public Edge(Node start, Node end, Direction d) {
			this.start = start;
			this.end = end;
			this.direction = d;
		}
	}
	
	private class Node {
		public List<Edge> edges;
		public Position position;
		public Edge preferredStartingEdge = null;
		
		public Node(Position p) {
			this.position = p;
			this.edges = new ArrayList<Edge>(4);
		}
	}
	
	// TODO Test these!
	private static int encodePosition(Position p) {
		return (int)p.y + (FieldMap.MAX_Y * (int)p.x);
	}
	
	// TODO Test these!
	private static Position decodePosition(int slot) {
		return new Position(slot % (int)FieldMap.MAX_Y, slot / (int)FieldMap.MAX_Y);
	}

	public Pathfinder(FieldMap fieldMap) {
		// TODO Auto-generated constructor stub
		this.fieldMap = fieldMap;
	}

	public List<Pathfinder.Step> solve(Position startingPos, Region endRegion) {
		Node[] nodeMap = new Node[FieldMap.MAX_X * FieldMap.MAX_Y];
		
		// Create the map of nodes
		for (int x = 0; x < FieldMap.MAX_X; x++) {
			for (int y = 0; y < FieldMap.MAX_Y; y++) {
				Node n = new Node(new Position(x, y));
				nodeMap[encodePosition(n.position)] = n;
			}
		}
		
		// Populate the edges
		for (int i = 0; i < FieldMap.MAX_X * FieldMap.MAX_Y; i++) {
			Node n = nodeMap[i];
			
			// TODO Cleanup!
			if (n.position.x > 0) {
				// Left
				Node t = nodeMap[encodePosition(new Position(n.position.x - 1, n.position.y))];
				if (fieldMap.isNavigable(t.position)) {
					Edge e = new Edge(n, t, Direction.LEFT);
					e.h = fieldMap.calculateHeuristic(n.position, t.position);
					e.g = fieldMap.calcDistSquared(t.position, endRegion.getCentroid());
					n.edges.add(new Edge(n, t, Direction.LEFT));
				}
			} else if (n.position.x < FieldMap.MAX_X - 1) {
				// Right
				Node t = nodeMap[encodePosition(new Position(n.position.x + 1, n.position.y))];
				if (fieldMap.isNavigable(t.position)) {
					Edge e = new Edge(n, t, Direction.RIGHT);
					n.edges.add(new Edge(n, t, Direction.RIGHT));
				}
			} else if (n.position.y > 0) {
				// Forward
				Node t = nodeMap[encodePosition(new Position(n.position.x, n.position.y - 1))];
				if (fieldMap.isNavigable(t.position)) {
					n.edges.add(new Edge(n, t, Direction.FORWARD));
				}
			} else if (n.position.y < FieldMap.MAX_Y - 1) {
				// Backward
				Node t = nodeMap[encodePosition(new Position(n.position.x, n.position.y + 1))];
				if (fieldMap.isNavigable(t.position)) {
					n.edges.add(new Edge(n, t, Direction.BACKWARD));
				}
			}
		}
		
		// Start from the current location and traverse the map.
		Node startingNode = nodeMap[encodePosition(startingPos)];
		Node endingNode = null;
		List<Edge> edgeList = new LinkedList<Edge>();
		edgeList.addAll(startingNode.edges);
		
		while (!edgeList.isEmpty()) {
			// Pop the first edge
			Edge e = edgeList.remove(0);
			
			// Check to see what the most optimal comes-from would be
			Node source = e.start;
			Node target = e.end;
			
			// Remove the edge to mark it checked off.
			source.edges.remove(e);
			
			// If we don't have a preferred edge, use this one; otherwise, use the more optimal.
			if (target.preferredStartingEdge == null) {
				target.preferredStartingEdge = e;
			} else {
				if ((e.g + e.h) < (target.preferredStartingEdge.g + target.preferredStartingEdge.h)) {
					target.preferredStartingEdge = e;
				}
			}
			
			// Add all remaining edges of the newly-encountered node to the edge list.
			edgeList.addAll(target.edges);
			
			// If we find a node in the target region, end.
			if (endRegion.contains(target.position)) {
				endingNode = target;
				break;
			}
		}
		
		// If we got here without finding anything, we don't have a path to the target. Return null so we can remove
		// that target from the board.
		if (endingNode == null) {
			return null;
		}
		
		// Walk backwards to create the path.
		List<Pathfinder.Step> steps = new LinkedList<Pathfinder.Step>();
		Node currentNode = endingNode;
		while (currentNode != startingNode) {
			Edge previousEdge = currentNode.preferredStartingEdge;
			Node previousNode = previousEdge.start;
			Step s = new Step();
			s.direction = previousEdge.direction;
			s.position = previousNode.position; // TODO Should this be starting or ending position?
			steps.add(0, s);
		}
		
		return steps;
	}

}
