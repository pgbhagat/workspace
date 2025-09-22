package com.binary.tree;

public class LargestBST {

    class MinMax {
        int min;
        int max;
        boolean isBST;
        int size;

        MinMax() {
            int min = 0;
            int max = 0;
            isBST = false;
            size = 0;
        }
    }

    public MinMax largestBST(Node root) {
        if (root == null) {
            return new MinMax();
        }
        if (root.left == null && root.right == null) {
            MinMax result = new MinMax();
            result.isBST = true;
            result.size = 1;
            result.min = result.max = root.data;
        }
        MinMax left = largestBST(root.left);
        MinMax right = largestBST(root.right);

        MinMax result = new MinMax();
        if (!left.isBST || !right.isBST
                || left.max > root.data || right.min > root.data) {
            result.isBST = false;
            result.size = Math.max(left.size, right.size);
        } else {
            result.isBST = true;
            result.size = left.size + right.size + 1;
            result.min = root.left != null ? left.min : root.data;
            result.max = root.right != null ? right.max : root.data;
        }
        return result;

    }
}
