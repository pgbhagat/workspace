package com.top150.two.pointers;

public class ContainerWithMostWater {
    public static int maxArea(int[] height) {
        int maxArea = 0;
        int left = 0, right = height.length - 1;
        while (left < right) {
            int width = right - left;
            maxArea = Math.max(maxArea, width * Math.min(height[left], height[right]));
            if (height[left] > height[right]) {
                right--;
            } else {
                left++;
            }
        }
        return maxArea;
    }

    public static void main(String[] args) {
        System.out.println(maxArea(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7}));
        System.out.println(maxArea(new int[]{1, 1}));
    }
}
