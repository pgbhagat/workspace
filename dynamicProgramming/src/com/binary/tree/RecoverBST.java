package com.binary.tree;

public class RecoverBST {
    private Node first;
    private Node second;
    private Node prev;

    public void recoverTree(Node root) {
        inorderTraversal(root);
        int tmp = first.data;
        first.data = second.data;
        second.data = tmp;

    }

    private void inorderTraversal(Node root) {
        if (root == null) {
            return;
        }
        inorderTraversal(root.left);
        if (prev != null && prev.data > root.data) {
            if (first == null) {
                first = prev;
            }
            second = root;
        }
        prev = root;
        inorderTraversal(root.right);
    }

}
