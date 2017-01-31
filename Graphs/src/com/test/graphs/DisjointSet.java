package com.test.graphs;

import java.util.HashMap;
import java.util.Map;

public class DisjointSet {

	private Map<Integer, Node> nodes = new HashMap<>();

	class Node {
		int data;
		int rank;
		Node parent;

		public Node findSet() {
			Node parent = this.parent;
			if (this == parent)
				return this;
			this.parent = parent.findSet();
			return this.parent;
		}
	}

	public int findSet(int data) {
		Node node = nodes.get(data);
		return node.findSet().data;
	}

	public void makeSet(int data) {
		if (nodes.containsKey(data))
			return;

		Node node = new Node();
		node.data = data;
		node.rank = 0;
		node.parent = node;

		nodes.put(data, node);
	}

	public void union(int data1, int data2) {
		Node node1 = nodes.get(data1);
		Node node2 = nodes.get(data2);

		Node parent1 = node1.findSet();
		Node parent2 = node2.findSet();

		if (parent1 == parent2)
			return;
		if (parent1.rank == parent2.rank) {
			parent2.parent = parent1;
			parent1.rank++;
		} else if (parent1.rank > parent2.rank) {
			parent2.parent = parent1;
		} else {
			parent1.parent = parent2;
		}
	}

	public static void main(String[] args) {
		DisjointSet set = new DisjointSet();
		set.makeSet(1);
		set.makeSet(2);
		set.makeSet(3);
		set.makeSet(4);
		set.makeSet(5);
		set.makeSet(6);

		set.union(1, 2);
		set.union(2, 3);
		set.union(4, 5);
		set.union(5, 6);
		set.union(4, 3);

		System.out.println("find set of 3 - " + set.findSet(3));
		System.out.println("find set of 6 - " + set.findSet(6));

	}

}
