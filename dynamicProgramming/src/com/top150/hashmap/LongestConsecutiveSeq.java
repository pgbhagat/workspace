package com.top150.hashmap;

import java.util.HashSet;
import java.util.Set;

public class LongestConsecutiveSeq {

    public static int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }

        int longestSequence = 0;
        for (int num : nums) {
            if (!set.contains(num - 1)) {
                int tmpSeq = 1;
                while (set.contains(num + tmpSeq)) {
                    tmpSeq++;
                }
                longestSequence = Math.max(longestSequence, tmpSeq);
            }
        }
        return longestSequence;
    }

    public static void main(String[] args) {
        System.out.println(longestConsecutive(new int[]{100, 4, 200, 1, 3, 2}));
        System.out.println(longestConsecutive(new int[]{0, 3, 7, 2, 5, 8, 4, 6, 0, 1}));
    }

}
