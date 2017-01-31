package com.traffic.control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Junctions are mapped as vertices.<br>
 * Vertices Roads are mapped as edged
 * 
 * <p>
 * Input graph should be connected, should not have any cycle.<br>
 * Input must be in correct order edges must be entered in correct order<br>
 * </p>
 * 
 * Input edge order <b>ith source vertex, jth destination vertex,cost.</b><br>
 * Next edge input should have source vertex strictly as ith or * (ith + 1)
 * vertex</br>
 * </p>
 * 
 * <b>Limitation: No efforts are made to defect any cycle in provided graph.</b>
 * 
 * 
 * @author Prashant.Bhagat
 * 
 */
public class TrafficControl {

	private Integer totalEdges;
	private Integer minTollPrice;
	private Integer totalVertices;

	private static final Integer MIN_EDGES = 1;
	private static final Integer MIN_COST = 1;
	private static final Integer MIN_VERTICES = 2;

	private static final String delim = ",";
	private static final Integer MAX_COST = 5_000;
	private static final Integer MAX_EDGES = 10_000;
	private static final Integer MAX_VERTICES = 10_000;

	private List<Edge> allEdges = new LinkedList<Edge>();
	private List<List<Edge>> allPossiblePaths = new ArrayList<List<Edge>>();
	private List<List<Edge>> verticesToEdges = new LinkedList<List<Edge>>();

	private Map<Edge, Integer> edgeToTollPrice = new HashMap<Edge, Integer>();
	private Map<List<Edge>, Integer> pathToCost = new HashMap<List<Edge>, Integer>();
	private Map<List<Edge>, Integer> pathToToll = new HashMap<List<Edge>, Integer>();
	private Map<Edge, Integer> edgeToInputLineMapping = new LinkedHashMap<Edge, Integer>();

	class Edge {
		Integer fromVertex;
		Integer toVertex;
		Integer cost;

		@Override
		public int hashCode() {
			return fromVertex * 100 + toVertex + 10 + cost;
		}

		@Override
		public boolean equals(Object otherRoad) {
			if (otherRoad instanceof Edge) {
				return (((Edge) otherRoad).fromVertex == this.fromVertex && ((Edge) otherRoad).toVertex == this.toVertex
						&& ((Edge) otherRoad).cost == this.cost);
			}
			return false;
		}

		@Override
		public String toString() {
			return fromVertex + "->" + toVertex;
		}
	}

	public String[] getTollPlan(String[] input) {
		if (input == null || input.length < 2) {
			System.out.println("Error: Invalid input.");
		} else if (initAllVertices(input[0])) {
			if (initAllEdges(input)) {
				verticesToEdges = getVerticesToEdgesList(allEdges);
				allPossiblePaths = getAllPathsFromSourceToDestinationVertex(verticesToEdges, 1, totalVertices);
				minTollPrice = getMinimumTollPrice(allPossiblePaths);
				removePathsWithCostPath(allPossiblePaths, minTollPrice);
				if (findSolution(allPossiblePaths, 0)) {
					return formatOutput();
				} else {
					return new String[] { "No solution" };
				}

			}
		}

		return new String[] { "No solution" };

	}

	private String[] formatOutput() {
		String[] output = new String[] { "No solution" };
		int index = 0;
		int lineNumber = 0;
		if (edgeToTollPrice.size() > 0) {
			output = new String[edgeToTollPrice.size() + 1];
			output[index++] = edgeToTollPrice.size() + "," + minTollPrice;
			Iterator<Edge> iterator = edgeToInputLineMapping.keySet().iterator();
			while (iterator.hasNext()) {
				lineNumber++;
				Edge edge = iterator.next();
				if (edgeToTollPrice.containsKey(edge)) {
					output[index++] = lineNumber + "," + edgeToTollPrice.get(edge);
				}
			}
		}
		return output;
	}

	private void removePathsWithCostPath(List<List<Edge>> allPossiblePaths, Integer minTollPrice) {
		List<List<Edge>> pathsToRemove = new LinkedList<List<Edge>>();
		for (List<Edge> path : allPossiblePaths) {
			if (pathToCost.get(path) == minTollPrice) {
				pathsToRemove.add(path);
			}
		}
		allPossiblePaths.removeAll(pathsToRemove);
	}

	// recurse
	// if no solution found, backtrack
	private boolean findSolution(List<List<Edge>> allPaths, int index) {
		if (index >= allPaths.size()) {
			// processed all possible paths from source to destination vertex
			// success case
			return true;
		}
		List<Edge> path = allPaths.get(index);
		for (Edge edge : path) {
			if (validEdgePathForToll(edge, path, allPaths)) {
				// put toll for this edge and on this path
				edgeToTollPrice.put(edge, minTollPrice - pathToCost.get(path));
				pathToToll.put(path, minTollPrice - pathToCost.get(path));
				if (findSolution(allPaths, index + 1)) {
					return true;
				}
				// backtrack..remove the toll put in above line for this edge on
				// this path
				edgeToTollPrice.remove(edge);
				pathToToll.remove(path);
			}
		}
		// no edge to put toll on
		return false;
	}

	/**
	 * 
	 * one can not put toll on path where a toll is already put
	 * 
	 * one can not put toll on an edge where a toll is already put
	 * 
	 * one can not put toll on an edge which belongs to a path where any toll is
	 * put
	 * 
	 * @param edge
	 * @param path
	 * @param allPaths
	 * @return
	 */
	private boolean validEdgePathForToll(Edge edge, List<Edge> path, List<List<Edge>> allPaths) {
		if (edgeToTollPrice.containsKey(edge) || pathToToll.get(path) != null) {
			return false;// this edge or path has already a toll
		}
		List<List<Edge>> edgeBelongToPath = new ArrayList<List<Edge>>();

		for (List<Edge> eachPath : allPaths) {
			if (eachPath.contains(edge)) {
				edgeBelongToPath.add(eachPath);
			}
		}
		if (!edgeBelongToPath.isEmpty()) {
			for (List<Edge> eachPath : edgeBelongToPath) {
				for (Edge eachEdge : eachPath) {
					if (edgeToTollPrice.containsKey(eachEdge)) {
						return false;// since a toll is already put
					}
				}
			}
		}
		return true;
	}

	private int getMinimumTollPrice(List<List<Edge>> allPaths) {
		int minTollPrice = 0;
		int costPath = 0;
		for (List<Edge> path : allPaths) {
			costPath = 0;
			for (Edge edge : path) {
				costPath += edge.cost;
			}
			pathToCost.put(path, costPath);
			if (costPath > minTollPrice) {
				minTollPrice = costPath;
			}
		}
		return minTollPrice;
	}

	private List<List<Edge>> getAllPathsFromSourceToDestinationVertex(List<List<Edge>> verticesToEdges, int source,
			int destination) {
		List<List<Edge>> result = new ArrayList<List<Edge>>();
		List<Edge> edges = verticesToEdges.get(source);
		for (Edge edge : edges) {
			if (edge.toVertex == destination) {
				List<Edge> path = new ArrayList<Edge>();
				path.add(edge);
				result.add(path);
			} else {
				List<List<Edge>> intermiatePath = getAllPathsFromSourceToDestinationVertex(verticesToEdges,
						edge.toVertex, destination);
				for (List<Edge> tmpList : intermiatePath) {
					tmpList.add(edge);
				}
				result.addAll(intermiatePath);
			}
		}
		return result;
	}

	private List<List<Edge>> getVerticesToEdgesList(List<Edge> allEdges) {
		List<List<Edge>> result = new LinkedList<List<Edge>>();
		for (int i = 0; i <= totalVertices; i++) {
			result.add(new LinkedList<TrafficControl.Edge>());
		}
		for (Edge edge : allEdges) {
			List<Edge> edges = result.get(edge.fromVertex);
			if (edges == null) {
				edges = new ArrayList<TrafficControl.Edge>();
				result.add(edge.fromVertex, edges);
			}
			edges.add(edge);
		}
		return result;
	}

	/**
	 * input validation for edges i.e. roads
	 * 
	 * @param input
	 * @return
	 */
	private boolean initAllEdges(String[] input) {
		boolean success = true;
		String[] tokens;
		for (int i = 1; i < input.length; i++) {
			String line = input[i];
			tokens = line.split(delim);
			if (tokens.length == 3) {
				Edge edge = new Edge();
				try {
					edge.fromVertex = Integer.parseInt(tokens[0].trim());
					edge.toVertex = Integer.parseInt(tokens[1].trim());
					edge.cost = Integer.parseInt(tokens[2].trim());
					if (edge.fromVertex <= 0 || edge.fromVertex > totalVertices) {
						success = false;
						System.out.println("Error: invalid input,<from> junction number must in range [1-"
								+ totalVertices + "], error line: " + line);
					}
					if (edge.toVertex <= 0 || edge.toVertex > totalVertices) {
						success = false;
						System.out.println("Error: invalid input,<to> junction number must in range [1-" + totalVertices
								+ "], error line: " + line);
					}
					if (edge.cost < MIN_COST || edge.cost > MAX_COST) {
						success = false;
						System.out.println("Error: road cost must be in range [" + MIN_COST + "-" + MAX_COST
								+ "], error line: " + line);
					}
					allEdges.add(edge);
					edgeToInputLineMapping.put(edge, i);
				} catch (NumberFormatException e) {
					success = false;
					System.out.println(
							"Error: Invalid input line for fromJunction,ToJunction,Cost; expected comma separated integers, errornous line:  "
									+ line);
				}
			} else {
				success = false;
				System.out.println("Error: Invalid road line input: " + line
						+ ", it must three comma separated integers representing <source junction, destination junction, road cost>");
			}
		}
		if (allEdges.size() != totalEdges) {
			success = false;
			System.out.println("Error: Total road entries should match with: " + totalVertices);
		}
		return success;
	}

	/**
	 * input validation for vertices i.e. junctions
	 * 
	 * @param firstLine
	 * @return
	 */
	private boolean initAllVertices(String firstLine) {
		boolean success = true;
		String[] tokens = firstLine.split(delim);
		if (tokens.length == 2) {
			String strVertices = tokens[0];
			String strEdge = tokens[1];
			try {
				this.totalVertices = Integer.parseInt(strVertices.trim());
			} catch (NumberFormatException e) {
				System.out.println(
						"Error: Invalid input for total junctions, errornous junctions value:  " + strVertices);
				success = false;
			}
			try {
				this.totalEdges = Integer.parseInt(strEdge.trim());
			} catch (NumberFormatException e) {
				success = false;
				System.out.println("Error: Invalid input for total roads, errornous road value:  " + strVertices);
			}
		} else {
			success = false;
			System.out.println("Error: Invalid input for total junctions and road, errornous line:  " + firstLine);
		}
		if (!(MIN_VERTICES <= totalEdges && totalEdges <= MAX_VERTICES)) {
			success = false;
			System.out.println("Error: Total junctions must be in range [" + MIN_VERTICES + "-" + MAX_VERTICES
					+ "]. Provided value: " + totalEdges);
		}
		if (!(MIN_EDGES <= totalVertices && totalVertices <= MAX_EDGES)) {
			success = false;
			System.out.println("Error: Total roads must be in range [" + MIN_EDGES + "-" + MAX_EDGES
					+ "]. Provided value: " + totalEdges);
		}

		return success;
	}

}
