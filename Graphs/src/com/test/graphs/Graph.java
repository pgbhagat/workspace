package com.test.graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * 
 * Vertex
 * 
 * label list<Edge> parent isvisited
 * 
 * addEdge removeEdge hashCode equals getter/setter
 * 
 * 
 * 
 * Edge (Comparable)
 * 
 * Vertex from Vertex to int weight hashCode equsls getter/setter
 * 
 * 
 * Graph Set<Vertex> Set<Edge>
 * 
 * addVertex addEdge removeEdge getAllVertices getAllEdges contains(Vertex)
 * contains(Edge)
 * 
 * dsf bsf
 * 
 * 
 * 
 * 
 * @author Prashant.Bhagat
 *
 */

class Vertex {
	private String label;
	private List<Edge> neighbors;
	private Vertex parent;
	private boolean isVisited;

	Vertex(String label) {
		this.label = label;
		neighbors = new ArrayList<Edge>();
	}

	public List<Edge> getNeighbours() {
		return new ArrayList<Edge>(neighbors);
	}

	@Override
	public int hashCode() {
		return getLabel().hashCode();
	}

	@Override
	public boolean equals(Object vertex) {
		if (vertex instanceof Vertex) {
			return ((Vertex) vertex).getLabel().equalsIgnoreCase(this.getLabel());
		}
		return false;
	}

	public String getLabel() {
		return label;
	}

	public boolean addEdge(Vertex to, int weight) {
		Edge edge = new Edge(this, to, weight);
		if (neighbors.contains(edge))
			return false;
		neighbors.add(edge);
		return true;
	}

	public boolean removeEdge(Vertex to) {
		Edge edge = new Edge(this, to);
		if (!neighbors.contains(edge))
			return false;
		neighbors.remove(edge);
		return true;
	}

	public int getNeighborCount() {
		return neighbors.size();
	}

	public Vertex getParent() {
		return parent;
	}

	public void setParent(Vertex parent) {
		this.parent = parent;
	}

	public boolean isVisited() {
		return isVisited;
	}

	public void setVisited(boolean isVisited) {
		this.isVisited = isVisited;
	}

}

class Edge implements Comparable<Edge> {

	private Vertex from;
	private Vertex to;
	private int weight;

	Edge(Vertex from, Vertex to) {
		this(from, to, Integer.MAX_VALUE);
	}

	Edge(Vertex from, Vertex to, int weight) {
		this.setFrom(from);
		this.setTo(to);
		this.setWeight(weight);
	}

	@Override
	public int compareTo(Edge anotherEdge) {
		return this.getWeight() - anotherEdge.getWeight();
	}

	@Override
	public int hashCode() {
		return getFrom().hashCode() + getTo().hashCode();
	}

	@Override
	public boolean equals(Object edge) {
		if (edge instanceof Edge) {
			Edge another = (Edge) edge;
			return (another.getFrom().equals(this.getFrom()) && another.getTo().equals(this.getTo()))
					|| (another.getFrom().equals(this.getTo()) && another.getTo().equals(this.getFrom()));
		}
		return false;

	}

	public Vertex getFrom() {
		return from;
	}

	public void setFrom(Vertex from) {
		this.from = from;
	}

	public Vertex getTo() {
		return to;
	}

	public void setTo(Vertex to) {
		this.to = to;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}
}

public class Graph {

	private Map<String, Vertex> vertices;
	private Set<Edge> edges;

	Graph() {
		vertices = new HashMap<String, Vertex>();
		edges = new HashSet<Edge>();
	}

	public boolean addVertex(String label) {
		return addVertex(new Vertex(label));
	}

	private boolean addVertex(Vertex vertexToAdd) {
		if (vertices.containsKey(vertexToAdd.getLabel()))
			return false;
		this.vertices.put(vertexToAdd.getLabel(), vertexToAdd);
		return true;
	}

	private boolean addEdge(Vertex fromVertext, Vertex toVertex, int weight) {
		Edge edgeToAdd = new Edge(fromVertext, toVertex, weight);
		if (edges.contains(edgeToAdd))
			return false;
		fromVertext.addEdge(toVertex, weight);
		toVertex.addEdge(fromVertext, weight);
		edges.add(edgeToAdd);
		return true;
	}

	public boolean addEdge(String from, String to, int weight) {
		Vertex fromVertext = vertices.get(from);
		Vertex toVertex = vertices.get(to);
		
		return addEdge(fromVertext, toVertex, weight);
	}

	public boolean removeEdge(String from, String to) {
		Vertex fromVertext = vertices.get(from);
		Vertex toVertex = vertices.get(to);
		Edge edgeToRemove = new Edge(fromVertext, toVertex);
		return removeEdge(edgeToRemove);
	}

	private boolean removeEdge(Edge edgeToRemove) {
		if (!this.edges.contains(edgeToRemove)) {
			return false;
		}
		edgeToRemove.getFrom().removeEdge(edgeToRemove.getTo());
		edgeToRemove.getTo().removeEdge(edgeToRemove.getFrom());
		edges.remove(edgeToRemove);

		return true;
	}

	public List<Vertex> dfs() {
		List<Vertex> result = new ArrayList<Vertex>(vertices.size());
		Vertex startVertex = null;
		Iterator<Entry<String, Vertex>> iterator = vertices.entrySet().iterator();
		if (iterator.hasNext()) {
			startVertex = iterator.next().getValue();
			internalDFS(startVertex, result);
		}
		return result;
	}

	private void internalDFS(Vertex vertex, List<Vertex> result) {
		vertex.setVisited(true);
		for (Edge edge : vertex.getNeighbours()) {
			if (edge.getTo().isVisited())
				continue;
			internalDFS(edge.getTo(), result);
		}
		result.add(vertex);

	}

	public static void main(String[] args) {

		Graph graph = new Graph();

		graph.addVertex("a");
		graph.addVertex("b");
		graph.addVertex("c");
		graph.addVertex("d");

		graph.addEdge("a", "b", 1);
		graph.addEdge("a", "c", 2);
		graph.addEdge("b", "c", 3);
		graph.addEdge("c", "d", 4);

		List<Vertex> result = graph.dfs();
		result.stream().forEach(vertex -> System.out.println(vertex.getLabel()));

	}

}
