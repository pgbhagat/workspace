package com.binary.tree;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class PrintRootToLeadPaths {
    List<String> paths = new LinkedList<>();

    public List<String> binaryTreePaths(Node root) {
        if (root == null) {
            return null;
        }
        paths(root, new LinkedList<>());
        return paths;
    }

    private void paths(Node root, List<Integer> currentPaths) {
        if (root != null) {
            currentPaths.add(root.data);
            if (root.left != null) {
                paths(root.left, new LinkedList<>(currentPaths));
            }
            if (root.right != null) {
                paths(root.right, new LinkedList<>(currentPaths));
            }
            if (root.left == null && root.right == null) {
                paths.add(currentPaths.stream().map(String::valueOf).collect(Collectors.joining("->")));
            }
        }
    }

}
