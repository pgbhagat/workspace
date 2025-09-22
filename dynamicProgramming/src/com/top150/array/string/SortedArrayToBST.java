package com.top150.array.string;

public class SortedArrayToBST {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;


        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }

    }

    public static TreeNode sortedArrayToBST(int[] nums) {
        return build(nums, 0, nums.length - 1);
    }

    public static TreeNode build(int[] nums, int low, int high) {
        if (low > high) {
            return null;
        }
        int mid = low + (high - low) / 2;
        return new TreeNode(nums[mid], build(nums, low, mid - 1), build(nums, mid + 1, high));
    }

    public static void printInOrder(TreeNode root) {
        if (root != null) {
            printInOrder(root.left);
            System.out.print(root.val + " ");
            printInOrder(root.right);
        }
    }


    public static void main(String... agrs) {

        int[] nums1 = {-10, -3, 0, 5, 9};
        System.out.println("Input Array: [-10, -3, 0, 5, 9]");
        TreeNode root1 = sortedArrayToBST(nums1);
        System.out.print("In-order traversal of the BST: ");
        printInOrder(root1);
        System.out.println("\n(Expected root: 0, with -3 and 9 as children, etc.)");

        // Test Case 2
        int[] nums2 = {1, 3};
        System.out.println("\nInput Array: [1, 3]");
        TreeNode root2 = sortedArrayToBST(nums2);
        System.out.print("In-order traversal of the BST: ");
        printInOrder(root2);
        System.out.println("\n(Expected root: 3, with 1 as left child.)");

        // Test Case 3 (Empty array)
        int[] nums3 = {};
        System.out.println("\nInput Array: []");
        TreeNode root3 = sortedArrayToBST(nums3);
        System.out.print("In-order traversal of the BST: ");
        printInOrder(root3);
        System.out.println("\n(Expected: an empty line)");
    }
}
