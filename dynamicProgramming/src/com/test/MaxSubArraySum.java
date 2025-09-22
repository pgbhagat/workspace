package com.test;

public class MaxSubArraySum {

  public static void main(String... args) {
    MaxSubArraySum maxSubArraySum = new MaxSubArraySum();
    System.out.println(maxSubArraySum.maxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));
  }

  public int maxSubArray(int[] nums) {

    if (nums == null || nums.length == 0) {
      return 0;
    }

    int maxSum = nums[0];
    int sumSoFar = nums[0];

    for (int i = 1; i < nums.length; i++) {
      sumSoFar = sumSoFar + nums[i];
      if (sumSoFar > maxSum) {
        maxSum = sumSoFar;
      }
      if(sumSoFar < 0) {
        sumSoFar = 0;
      }
    }

    return maxSum;
  }

}
