package com.binary.tree;

//117. Populating Next Right Pointers in Each Node II
//https://www.youtube.com/watch?v=yl-fdkyQD8A
class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;
    public TreeNode next;

    public TreeNode(int _val) {
        val = _val;
    }
}

public class ConnectNodes {
    public TreeNode connect(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode head = root;
        while (head != null) {
            TreeNode dummy = new TreeNode(0);
            TreeNode tmp = dummy;
            while (head != null) {
                if (head.left != null) {
                    tmp.next = head.left;
                    tmp = tmp.next;
                }
                if (head.right != null) {
                    tmp.next = head.right;
                    tmp = tmp.next;
                }
                head = head.next;
            }
            head = dummy.next;
        }
        return root;

    }
}
