package com.test;

import java.util.LinkedList;
import java.util.Queue;

public class LevelOrderTreeTraversal {

  public static void main(String... args) {
    LevelOrderTreeTraversal levelOrderTreeTraversal = new LevelOrderTreeTraversal();
    Node root = levelOrderTreeTraversal.buildTree();
    levelOrderTreeTraversal.traverse(root);

  }

  public Node buildTree() {
    Node root = new Node();
    root.data = 0;
    root.left = new Node();
    root.right = new Node();
    root.left.data = 1;
    root.right.data = 2;
    return root;
  }

  public void traverse(Node root) {
    Queue<Node> queue = new LinkedList<Node>();
    if (root != null) {
      queue.add(root);
      while (!queue.isEmpty()) {
        Node node = queue.remove();
        System.out.println(node.data);
        if (node.left != null) {
          queue.add(node.left);
        }
        if (node.right != null) {
          queue.add(node.right);
        }
      }
    }

  }

  class Node {

    int data;
    Node left;
    Node right;
  }


}
