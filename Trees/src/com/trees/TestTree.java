package com.trees;
import java.util.ArrayList;
import java.util.List;

public class TestTree {

	class Node {
		int data;
		Node left;
		Node right;

		Node(int data) {
			this.data = data;
		}

		public void addNode(int data) {
			Node node = new Node(data);
			Node rootNode = this;
			Node tmp = rootNode;
			while (rootNode != null) {
				tmp = rootNode;
				if (node.data <= rootNode.data) {
					rootNode = rootNode.left;
				} else {
					rootNode = rootNode.right;
				}
			}
			if (node.data <= tmp.data) {
				tmp.left = node;
			} else {
				tmp.right = node;
			}
		}

	}

	public Node buildATree(int[] numbers) {
		Node root = new Node(numbers[0]);
		for (int i = 1; i < numbers.length; i++) {
			root.addNode(numbers[i]);
		}
		return root;
	}

	public static void inorder(Node root) {
		if (root == null)
			return;
		inorder(root.left);
		System.out.println(root.data);
		inorder(root.right);
	}

	public static void printLeafPath(Node root, List<Node> path) {
		if (root == null)
			return;
		List<Node> newPath = new ArrayList<Node>();
		newPath.addAll(path);
		newPath.add(root);
		if (root.left == null && root.right == null) {
			System.out.println("Path - ");
			print(newPath);
		} else {
			printLeafPath(root.left, newPath);
			printLeafPath(root.right, newPath);
		}
	}

	private static void print(List<Node> path) {
		path.stream().forEach((node) -> System.out.println(node.data));

	}
	
	public static Node mirror(Node root){
		if (root == null){
			return root;
		} 
		Node left = mirror(root.left);
		Node right = mirror(root.right);
		root.left = right;
		root.right = left;
		return root;
	}

	public static void main(String[] args) {
		/**
		 * 
		 * 				 3
		 *            1    4   
		 *              2     7
		 *                  
		 * 
		 * 
		 */
		
		
		TestTree tree = new TestTree();
		TestTree.Node root = tree.buildATree(new int[] { 3, 4, 7, 1, 2, 4, 7, 8, 5, 6, 12 });
		System.out.println("build the tree");
		TestTree.inorder(root);
		System.out.println("all leaf paths");
		//TestTree.printLeafPath(root, new ArrayList<Node>());
		TestTree.mirror(root);
		System.out.println("mirror");
		inorder(root);

	}

}
