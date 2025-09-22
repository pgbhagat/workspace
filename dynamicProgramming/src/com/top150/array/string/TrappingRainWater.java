package com.top150.array.string;

public class TrappingRainWater {

    public static int trappedRainWater(int[] height) {
        int[] leftMax = new int[height.length];
        int[] rightMax = new int[height.length];

        leftMax[0] = height[0];
        for (int i = 1; i < height.length; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], height[i]);
        }
        rightMax[height.length - 1] = height[height.length - 1];
        for (int i = height.length - 2; i >= 0; i--) {
            rightMax[i] = Math.max(rightMax[i + 1], height[i]);
        }
        int maxRainTrapped = 0;
        for (int i = 0; i < height.length; i++) {
            int tmp = Math.min(leftMax[i], rightMax[i]) - height[i];
            if (tmp > 0) {
                maxRainTrapped += tmp;
            }
        }
        return maxRainTrapped;

    }

    public static void main(String... args) {
        System.out.println(trappedRainWater(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}));
        System.out.println(trappedRainWater(new int[]{4, 2, 0, 3, 2, 5}));
    }
}
