package com.test;

public class MinJumpGame {

    public static void main(String[] args) {
        System.out.println(minJump(new int[]{2, 3, 1, 1, 4}));
        System.out.println(minJump(new int[]{3, 2, 1, 0, 4}));
    }

    public static int minJump(int[] nums) {
        int jump = 0;
        int currentEnd = 0;
        int currentFurthest = 0;

        for (int i = 0; i < nums.length - 1; i++) {
            currentFurthest = Math.max(currentFurthest, i + nums[i]);
            if (i == currentEnd) {
                jump++;
                currentEnd = currentFurthest;
            }
        }
        return jump;
    }


}
