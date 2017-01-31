package com.trees;
import java.util.Arrays;

public class SortedArrayToBST {

	static class Node {
		int data;
		Node left, right;

		Node(int data) {
			this.data = data;
		}
	}

	public static void main(String[] args) {
		int[] input = new int[] { 777, 1, 2, 1, 2, 4, 6, 3, 2, 45, 6, 23, 56, 342, 76, 21, 19 };
		Arrays.sort(input);
		Node root = buildBST(input, 0, input.length - 1);
		System.out.println("inorder traversal -");
		inOrder(root);
		
	}

	private static void inOrder(Node root) {
		if(root!=null){
			inOrder(root.left);
			System.out.print(root.data + " ");
			inOrder(root.right);
		}
	}

	private static Node buildBST(int[] input, int low, int high) {
		Node root = null;
		if (low <= high) {
			int mid = low + (high - low) / 2;
			root = new Node(input[mid]);
			root.left = buildBST(input, low, mid - 1);
			root.right = buildBST(input, mid + 1, high);
		}

		return root;
	}

}
