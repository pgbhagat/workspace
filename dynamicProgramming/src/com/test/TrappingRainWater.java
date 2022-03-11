package com.test;

public class TrappingRainWater {

  public static void main(String[] args) {
    TrappingRainWater trappingRainWater = new TrappingRainWater();
    System.out.println(trappingRainWater.trap(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}));
  }

  public int trap(int[] height) {
    int answer = 0;
    if (height == null || height.length <= 1) {
      return answer;
    }
    int[] leftMax = new int[height.length];
    int[] rightMax = new int[height.length];

    int leftMaxSoFar = height[0];
    for (int i = 1; i < height.length; i++) {
      leftMaxSoFar = Math.max(leftMaxSoFar, height[i]);
      leftMax[i] = leftMaxSoFar;
    }

    int rightMaxSoFar = height[height.length - 1];
    rightMax[height.length - 1] = rightMaxSoFar;
    for (int i = height.length - 2; i >= 0; i--) {
      rightMaxSoFar = Math.max(rightMaxSoFar, height[i]);
      rightMax[i] = rightMaxSoFar;
    }

    for (int i = 0; i < leftMax.length; i++) {
      answer += (Math.min(leftMax[i], rightMax[i]) - height[i]);
    }
    return answer;
  }

}
