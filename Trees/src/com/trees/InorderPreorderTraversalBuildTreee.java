package com.trees;

class Node {
	int data;
	Node left, right;

	Node(int value) {
		data = value;
	}

}

public class InorderPreorderTraversalBuildTreee {
	static int preOrderIndex = 0;

	private Node buildTree(int[] preOrder, int[] inOrder, int inStart, int inEnd) {
		Node node = null;

		if (inStart > inEnd || (preOrderIndex > preOrder.length - 1)) {
			return node;
		}

		node = new Node(preOrder[preOrderIndex++]);
		if (inStart == inEnd) {
			return node;
		}
		int nodeIndex = search(inOrder, inStart, inEnd, node.data);
		node.left = buildTree(preOrder, inOrder, inStart, nodeIndex - 1);
		node.right = buildTree(preOrder, inOrder, nodeIndex + 1, inEnd);

		return node;

	}

	private int search(int[] inOrder, int inStart, int inEnd, int data) {
		for (int i = inStart; i <= inEnd; i++) {
			if (inOrder[i] == data) {
				return i;
			}
		}
		return 0;
	}

	public static void main(String[] args) {
		int[] preOrder = new int[] { 4, 2, 5, 1, 6, 3 };
		int[] inOrder = new int[] { 1, 2, 4, 5, 3, 6 };
		
		if (preOrder.length != inOrder.length) {
			System.exit(-1);
		}
		InorderPreorderTraversalBuildTreee instance = new InorderPreorderTraversalBuildTreee();
		InorderPreorderTraversalBuildTreee.preOrderIndex = 0;
		Node root = instance.buildTree(preOrder, inOrder, 0, inOrder.length - 1);
		instance.preOrder(root);

	}

	private void preOrder(Node root) {
		if (root == null) {
			return;
		}
		System.out.println(root.data);
		preOrder(root.left);
		preOrder(root.right);
	}

}
