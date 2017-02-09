package com.trees;

public class AVLTree {

	static class Node {
		Node left, right;
		int data;
		int height;

		Node(int data) {
			this.data = data;
		}

	}

	private Node leftRotate(Node root) {
		Node newRoot = root.right;
		root.right = root.right.left;
		newRoot.left = root;
		root.height = getNewHeight(root);
		newRoot.height = getNewHeight(newRoot);
		return newRoot;
	}

	private Node rightRotate(Node root) {
		Node newRoot = root.left;
		root.left = root.left.right;
		newRoot.right = root;
		root.height = getNewHeight(root);
		newRoot.height = getNewHeight(newRoot);
		return newRoot;
	}

	private int getNewHeight(Node root) {
		if (root == null) {
			return 0;
		}
		return 1 + Math.max((root.left != null ? root.left.height : 0), (root.right != null ? root.right.height : 0));
	}

	private int getHeight(Node root) {
		if (root == null) {
			return 0;
		} else {
			return root.height;
		}
	}

	public Node insert(Node root, int data) {
		if (root == null) {
			return new Node(data);
		}
		if (root.data <= data) {
			root.right = insert(root.right, data);
		} else {
			root.left = insert(root.left, data);
		}
		int balance = balance(root.left, root.right);
		if (balance > 1) {
			if (getHeight(root.left.left) >= getHeight(root.left.right)) {
				root = rightRotate(root);
			} else {
				root.left = leftRotate(root.left);
				root = rightRotate(root);
			}
		} else if (balance < -1) {
			if (getHeight(root.right.right) >= getHeight(root.right.left)) {
				root = leftRotate(root);
			} else {
				root.right = rightRotate(root.right);
				root = leftRotate(root);
			}
		} else {
			root.height = getNewHeight(root);
		}
		return root;
	}

	private int balance(Node rootLeft, Node rootRight) {
		return getHeight(rootLeft) - getHeight(rootRight);
	}

	public static void main(String args[]) {
		AVLTree avlTree = new AVLTree();
		Node root = null;
		root = avlTree.insert(root, -10);
		root = avlTree.insert(root, 2);
		root = avlTree.insert(root, 13);
		root = avlTree.insert(root, -13);
		root = avlTree.insert(root, -15);
		root = avlTree.insert(root, 15);
		root = avlTree.insert(root, 17);
		root = avlTree.insert(root, 20);
	}
}
