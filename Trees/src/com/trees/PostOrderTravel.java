package com.trees;

import java.util.Stack;

public class PostOrderTravel {

	static class Node {
		int data;
		Node left;
		Node right;
	}

	public static void postOrderUsingSingleStack(Node root) {
		if (root == null) {
			return;
		}
		Stack<Node> stack = new Stack<PostOrderTravel.Node>();

		do {
			while (root != null) {
				stack.push(root);
				root = root.left;
			}
			if (!stack.isEmpty()) {
				Node tmp = stack.peek().right;
				if (tmp == null) {
					tmp = stack.pop();
					System.out.print(tmp.data + " ");
					while (!stack.isEmpty() && stack.peek().right == tmp) {
						tmp = stack.pop();
						System.out.print(tmp.data + " ");
					}
					// root is still null...
				} else {
					// root points to right subtree..since right half still not
					// visited
					root = tmp;

				}
			}

		} while (root != null || !stack.isEmpty());
	}

	public static void postOrderUsingTwoStack(Node root) {
		if (root == null) {
			return;
		}
		Stack<Node> stack1 = new Stack<PostOrderTravel.Node>();
		Stack<Node> stack2 = new Stack<PostOrderTravel.Node>();
		stack1.push(root);
		while (!stack1.isEmpty()) {
			Node tmp = stack1.pop();
			stack2.push(tmp);
			if (tmp.left != null) {
				stack1.push(tmp.left);
			}
			if (tmp.right != null) {
				stack1.push(tmp.right);
			}
		}
		while (!stack2.isEmpty()) {
			System.out.print(stack2.pop().data + " ");
		}

	}

	public static Node buildTree() {
		Node root = new Node();
		root.data = 7;
		Node left = new Node();
		left.data = 9;
		Node right = new Node();
		right.data = 8;

		root.left = left;
		root.right = right;

		left.left = new Node();
		left.left.data = 4;
		left.right = new Node();
		left.right.data = 2;
		left.right.left = new Node();
		left.right.right = new Node();
		left.right.left.data = 1;
		left.right.right.data = 1;

		root.right.left = new Node();
		root.right.left.data = 9;

		return root;

	}

	public static void main(String[] args) {
		Node root = buildTree();
		postOrderUsingTwoStack(root);
		System.out.println();
		postOrderUsingSingleStack(root);
	}

}
