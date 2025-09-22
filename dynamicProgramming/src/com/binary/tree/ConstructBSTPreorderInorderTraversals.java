package com.binary.tree;

import java.util.HashMap;
import java.util.Map;

public class ConstructBSTPreorderInorderTraversals {
    private int preorderIndex = 0;
    private Map<Integer, Integer> elementIndexMap = new HashMap<>();

    public Node buildTree(int[] preorder, int[] inorder) {
        for (int i = 0; i < inorder.length; i++) {
            elementIndexMap.put(inorder[i], i);
        }
        return helper(preorder, 0, inorder.length - 1);
    }

    private Node helper(int[] preorder, int start, int end) {
        if (end < start) {
            return null;
        }
        Node root = new Node(preorder[preorderIndex], null, null);
        preorderIndex++;
        int index = elementIndexMap.get(root.data);
        root.left = helper(preorder, start, index - 1);
        root.right = helper(preorder, index + 1, end);
        return root;
    }
}
