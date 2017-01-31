package com.trees;

import java.util.Stack;

public class KthSmallerstElementInBinarySearchTree {

	static class Node {
		int data;
		Node left;
		Node right;

		public Node(int data) {
			this.data = data;
		}
	}

	public static void main(String[] args) {

		Node root = null;
		int[] data = { 2, 3, 1, 2, -5, -3, 8, 5, 0, 9 };
		for (int i : data) {
			root = insertInBinarySearchTree(root, i);
		}
		int k = 5;
		// int k = 999;
		Node node = kthSmallestElement(root, k);
		System.out.println(k + "th smallest element is : " + (node != null ? node.data : "No such element"));

	}

	private static Node kthSmallestElement(Node root, int k) {
		Stack<Node> stack = new Stack<Node>();
		int kthElementCount = 0;
		do {
			while (root != null) {
				stack.push(root);
				root = root.left;
			}
			if (!stack.isEmpty()) {
				Node node = stack.pop();
				kthElementCount++;
				if (kthElementCount == k) {
					return node;
				} else {
					root = node.right;
				}
			}
		} while (root != null || !stack.isEmpty());

		return null;
	}

	public static Node insertInBinarySearchTree(Node root, int data) {
		Node node = new Node(data);
		if (root == null) {
			return node;
		} else {
			Node current = root;
			Node parent = root;
			while (current != null) {
				parent = current;
				if (data <= current.data) {
					current = current.left;
				} else {
					current = current.right;
				}
			}
			if (data <= parent.data) {
				parent.left = node;
			} else {
				parent.right = node;
			}
		}
		return root;
	}

}
