package com.trees;

public class LargestBinarySearchTree {

	static class Node {
		int data;
		Node left, right;
	}

	static class LargestBST {
		Node root;
		boolean isBST = false;// this is must...

		int totalNode;
		int min = Integer.MIN_VALUE;
		int max = Integer.MAX_VALUE;

		public String toString() {
			if (root == null) {
				return "No binary search tree found";
			}
			return "Largest BST: rooted at - " + root.data + ", size of BST - " + totalNode + ", min - " + min
					+ ", max - " + max;
		}
	}

	public static void main(String[] args) {
		Node root = createBinaryTree();
		LargestBST largestBST = getLargestBinarySearchTree(root);
		System.out.println(largestBST);

	}

	private static LargestBST getLargestBinarySearchTree(Node root) {
		LargestBST largestBST = null;
		if (root != null) {
			largestBST = new LargestBST();
			if (root.left == null && root.right == null) {
				largestBST.root = root;
				largestBST.totalNode = 1;
				largestBST.max = root.data;
				largestBST.min = root.data;
				largestBST.isBST = true;
			} else {
				LargestBST leftBST = getLargestBinarySearchTree(root.left);
				LargestBST rightBST = getLargestBinarySearchTree(root.right);
				if (leftBST != null && rightBST != null && leftBST.isBST && rightBST.isBST && root.data > leftBST.max
						&& root.data <= rightBST.min) {// must check
					largestBST.root = root;
					largestBST.totalNode = leftBST.totalNode + rightBST.totalNode + 1;
					largestBST.min = leftBST.min;
					largestBST.max = rightBST.max;
					largestBST.isBST = true;
				} else {
					if (leftBST != null && rightBST != null) {
						if (leftBST.totalNode > rightBST.totalNode) {
							largestBST = leftBST;
						} else {
							largestBST = rightBST;
						}
						largestBST.isBST = false;// isBST will be false
					} else if (leftBST == null) {
						largestBST = rightBST;
					} else {
						largestBST = leftBST;
					}

				}
			}
		}
		return largestBST;

	}

	private static Node createBinaryTree() {
		Node root = new Node();
		root.data = 19;
		root.left = new Node();
		root.right = new Node();

		root.left.data = 4;
		root.left.left = new Node();
		root.left.right = new Node();

		root.left.left.data = 2;
		root.left.left.left = new Node();
		root.left.left.right = new Node();

		root.left.left.left = new Node();
		root.left.left.right = new Node();

		root.left.left.left.data = 1;
		root.left.left.right.data = 3;

		root.left.right.data = 5;
		root.left.right.right = new Node();
		root.left.right.right.data = 6;

		root.right.data = 11;
		root.right.left = new Node();
		root.right.right = new Node();

		root.right.left.data = 9;
		root.right.left.left = new Node();
		root.right.left.right = new Node();
		root.right.left.left.data = 8;
		root.right.left.right.data = 10;

		root.right.right.data = 12;

		return root;
	}

}
