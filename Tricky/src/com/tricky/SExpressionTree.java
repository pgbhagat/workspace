package com.tricky;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;

public class SExpressionTree {

	static class Node {
		char value;
		Node left;
		Node right;
	}

	static class MultipleRootException extends Exception {

		/**
		 * 
		 */
		private static final long serialVersionUID = -470806106689537617L;

	}

	static String SExpression(String nodes) {
		String ans = "E5";
		Map<Character, Set<Character>> pairs = new HashMap<Character, Set<Character>>();
		if (nodes != null && !nodes.isEmpty()) {
			String[] tokens = nodes.split(" ");// space separated parent->child
												// pairs
			for (String pair : tokens) {
				char[] parentChild = getParentChild(pair);
				Set<Character> childs = pairs.get(parentChild[0]);
				if (childs == null) {
					childs = new TreeSet<>();
					pairs.put(parentChild[0], childs);
				}
				if (childs.size() == 2) {
					ans = "E1";// more than two childs error
					break;
				} else {
					if (childs.contains(parentChild[1])) {
						ans = "E2";// duplicate edge error
						break;
					}
					childs.add(parentChild[1]);
				}
			}
			if (isCyclePresent(pairs)) {
				ans = "E3";// cycle error
			} else {
				try {
					char root = findRoot(pairs);
					Node binaryTreeRoot = buildBinaryTree(root, pairs);
					ans = getSeq(binaryTreeRoot);
				} catch (MultipleRootException e) {
					ans = "E4";// mutiple roots
				}
			}
		}
		return ans;

	}

	private static String getSeq(Node root) {
		String thisString = null;
		if (root != null) {
			thisString = "(" + root.value;
			String left = getSeq(root.left);
			String right = getSeq(root.right);
			if (left != null) {
				thisString += left;
			}
			if (right != null) {
				thisString += right;
			}
			thisString += ")";
		}
		return thisString;
	}

	private static Node buildBinaryTree(char parent, Map<Character, Set<Character>> pairs) {
		Node root = new Node();
		root.value = parent;
		Set<Character> childs = pairs.get(parent);
		if (childs != null) {
			Object[] arrChar = childs.toArray();
			if (childs.size() == 1) {
				if ((char) arrChar[0] > parent)
					root.right = buildBinaryTree((char) arrChar[0], pairs);
				else {
					root.left = buildBinaryTree((char) arrChar[0], pairs);
				}
			} else if (childs.size() == 2) {
				if ((char) arrChar[0] > (char) arrChar[1]) {
					root.right = buildBinaryTree((char) arrChar[0], pairs);
					root.left = buildBinaryTree((char) arrChar[1], pairs);
				} else {
					root.right = buildBinaryTree((char) arrChar[1], pairs);
					root.left = buildBinaryTree((char) arrChar[0], pairs);
				}
			}
		}
		return root;
	}

	private static boolean isCyclePresent(Map<Character, Set<Character>> pairs) {
		Set<Character> childs = new HashSet<>();
		for (Entry<Character, Set<Character>> entry : pairs.entrySet()) {
			Set<Character> thisChilds = entry.getValue();
			for (Character child : thisChilds) {
				if (childs.contains(child)) {
					return true;
				} else {
					childs.add(child);
				}
			}
		}
		return false;
	}

	private static char findRoot(Map<Character, Set<Character>> pairs) throws MultipleRootException {
		Character root = null;
		for (Entry<Character, Set<Character>> entry : pairs.entrySet()) {
			char possibleRoot = entry.getKey();
			if (!isChildNode(possibleRoot, pairs)) {
				if (root == null) {
					root = possibleRoot;
				} else {
					throw new MultipleRootException();
				}
			}
		}
		return root;
	}

	private static boolean isChildNode(char possibleRoot, Map<Character, Set<Character>> pairs) {
		for (Entry<Character, Set<Character>> entry : pairs.entrySet()) {
			Set<Character> childs = entry.getValue();
			if (childs.contains(possibleRoot)) {
				return true;
			}
		}
		return false;
	}

	private static char[] getParentChild(String pair) {
		int i = 0;
		char[] parentChild = new char[2];
		for (char c : pair.trim().toCharArray()) {
			if (c == '(' || c == ')' || c == ',') {
				continue;
			}
			parentChild[i++] = c;
			if (i == 2) {
				break;
			}
		}
		return parentChild;
	}

	public static void main(String... args) {
		String nodes = "(A,B) (A,C) (B,D) (D,E) (C,F) (F,G)";
		String ans = SExpression(nodes);
		System.out.println(ans);
	}
}
