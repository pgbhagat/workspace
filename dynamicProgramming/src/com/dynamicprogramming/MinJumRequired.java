package com.dynamicprogramming;

import java.util.Arrays;

public class MinJumRequired {

    public static void main(String... args) {
        minJumpRequired(new int[]{2, 3, 1, 1, 2, 4, 2, 0, 1, 1});
    }

    public static void minJumpRequired(int steps[]) {
        int[] fromIndex = new int[steps.length];
        int[] minJumpRequireAt = new int[steps.length];

        fromIndex[0] = -1;
        Arrays.fill(minJumpRequireAt, Integer.MAX_VALUE);
        minJumpRequireAt[0] = 0;

        for (int i = 1; i < steps.length; i++) {
            for (int j = 0; j < i; j++) {
                if (steps[j] >= (i - j)) {
                    if (minJumpRequireAt[i] > 1 + minJumpRequireAt[j]) {
                        minJumpRequireAt[i] = 1 + minJumpRequireAt[j];
                        fromIndex[i] = j;
                    }
                }
            }
        }
        System.out.println("from index = " + Arrays.toString(fromIndex));
        System.out.println("minJumpRequireAt = " + Arrays.toString(minJumpRequireAt));

    }
}
