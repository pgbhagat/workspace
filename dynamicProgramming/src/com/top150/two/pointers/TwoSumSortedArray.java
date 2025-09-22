package com.top150.two.pointers;

import java.util.Arrays;

public class TwoSumSortedArray {

    public static int[] twoSum(int[] numbers, int target) {
        int[] solution = new int[2];
        int start = 0;
        int end = numbers.length - 1;
        while (start < end) {
            if (numbers[start] + numbers[end] == target) {
                solution[0] = start + 1;
                solution[1] = end + 1;
                break;
            } else if (numbers[start] + numbers[end] > target) {
                end--;
            } else if (numbers[start] + numbers[end] < target) {
                start++;
            }
        }
        return solution;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(twoSum(new int[]{2, 7, 11, 15}, 9)));
        System.out.println(Arrays.toString(twoSum(new int[]{2, 3, 4}, 6)));
        System.out.println(Arrays.toString(twoSum(new int[]{-1, 0}, -1)));

    }

}
