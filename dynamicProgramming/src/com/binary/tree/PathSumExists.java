package com.binary.tree;

import java.util.List;

public class PathSumExists {

    public boolean doesPathSumExists(Node root, int sum, List<Node> path) {
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null && sum == root.data) {
            path.add(root);
            return true;
        }
        if (doesPathSumExists(root.left, sum - root.data, path)) {
            path.add(root);
            return true;
        }
        if (doesPathSumExists(root.right, sum - root.data, path)) {
            path.add(root);
            return true;
        }
        return false;

    }
}
