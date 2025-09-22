package com.binary.tree;

import java.util.LinkedList;
import java.util.List;

public class RootToLeafSum {
    List<Integer> result = new LinkedList<>();

    public boolean sumExists(Node root, int sum, List<Integer> result) {
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null) {
            if (root.data == sum) {
                result.add(root.data);
                return true;
            }
        }
        if (sumExists(root.left, sum - root.data, result)) {
            result.add(root.data);
            return true;
        }
        if (sumExists(root.right, sum - root.data, result)) {
            result.add(root.data);
            return true;
        }

        return false;
    }

    public static void main(String... args) {
        List<Integer> result = new LinkedList<>();
        RootToLeafSum solution = new RootToLeafSum();
        Node tree = new Node(5, null, null);
        tree.left = new Node(6, null, null);
        tree.right = new Node(7, null, null);
        solution.sumExists(tree, 10, result);
        System.out.println(result);
    }

}
