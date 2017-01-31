package com.trees;

public class BSTUsingPreOrderTraversal {

	int preOrderIndex = 0;

	public static void main(String[] args) {
		int[] preorder = new int[] { 5, 3, 1, 2, 4, 8, 7, 6, 9 };
		BSTUsingPreOrderTraversal main = new BSTUsingPreOrderTraversal();
		Node root = main.constructBST(preorder, preorder[0], Integer.MIN_VALUE, Integer.MAX_VALUE);
		traverse(root);

	}

	private static void traverse(Node root) {
		System.out.println("Preorder - ");
		preOrder(root);
		System.out.println("Inorder - ");
		inOrder(root);
	}

	private static void inOrder(Node root) {
		if (root != null) {
			inOrder(root.left);
			System.out.println(root.data);
			inOrder(root.right);
		}
	}

	private static void preOrder(Node root) {
		if (root != null) {
			System.out.println(root.data);
			preOrder(root.left);
			preOrder(root.right);
		}
	}

	private Node constructBST(int[] preorder, int data, int minValue, int maxValue) {
		Node node = null;

		if (preOrderIndex < preorder.length && preorder[preOrderIndex] > minValue
				&& preorder[preOrderIndex] < maxValue) {
			node = new Node(data);
			preOrderIndex++;
			
			if (preOrderIndex < preorder.length) {
				node.left = constructBST(preorder, preorder[preOrderIndex], minValue, data);
				node.right = constructBST(preorder, preorder[preOrderIndex], data, maxValue);
			}

		}

		return node;
	}

	static class Node {
		int data;
		Node left;
		Node right;

		Node(int data) {
			this.data = data;
			left = null;
			right = null;
		}

	}

}
