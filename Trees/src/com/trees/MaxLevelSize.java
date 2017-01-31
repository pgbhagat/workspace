package com.trees;

import java.util.LinkedList;
import java.util.Queue;

public class MaxLevelSize {
	class Node {
		int value;
		Node left, right;
	}

	public Node buildATree(int count) {
		if (count == 0)
			return null;
		Node root = new Node();
		root.value = count;
		root.left = buildATree(count - 1);
		root.right = buildATree(count - 1);
		return root;

	}

	public int getMaxLevelSize(Node root) {

		int max = 0;
		if (root == null) {
			return max;
		}

		Queue<Node> queue = new LinkedList<Node>();
		queue.add(root);
		queue.add(null);

		while (!queue.isEmpty()) {
			Node node = queue.poll();
			if (node == null) {
				if (!queue.isEmpty()) {
					queue.add(null);
				}
			} else {
				System.out.println(node.value);
				if (node.left != null)
					queue.add(node.left);
				if (node.right != null) {
					queue.add(node.right);
				}
			}
		}

		return max;
	}

	public static void main(String[] args) {
		MaxLevelSize object = new MaxLevelSize();
		Node root = object.buildATree(6);
		object.getMaxLevelSize(root);

	}

}
