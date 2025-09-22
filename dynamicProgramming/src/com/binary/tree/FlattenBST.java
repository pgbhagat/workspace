package com.binary.tree;

//https://www.youtube.com/watch?v=NOKVBiJwkD0
public class FlattenBST {
    public void flatten(Node root) {
        if (root == null) {
            return;
        }
        Node leftTmp = root.left;
        Node rightTmp = root.right;
        root.left = null;

        flatten(leftTmp);
        flatten(rightTmp);

        root.right = leftTmp;
        Node current = root;
        while (current.right != null) {
            current = current.right;
        }
        current.right = rightTmp;
    }
}
