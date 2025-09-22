package com.binary.tree;

import java.util.Stack;

public class InOrderTraversal {

    public void inOrder(Node root) {
        if (root == null) {
            return;
        }

        Stack<Node> stack = new Stack<>();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            System.out.println(root.data);
            root = root.right;
        }
    }
}
