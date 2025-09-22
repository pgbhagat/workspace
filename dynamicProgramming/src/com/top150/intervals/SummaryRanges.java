package com.top150.intervals;

import java.util.ArrayList;
import java.util.List;

public class SummaryRanges {
    public static List<String> summaryRanges(int[] nums) {
        List<String> result = new ArrayList<>();
        if (nums != null && nums.length > 0) {
            for (int i = 0; i < nums.length; i++) {
                int begin = i;
                while ((i + 1) < nums.length && (nums[i + 1] - nums[i] == 1)) {
                    i++;
                }
                int end = i;
                if (begin == end) {
                    result.add("" + nums[i]);
                } else {
                    result.add("" + nums[begin] + "->" + nums[end]);
                }
            }
        }
        return result;
    }

    public static void main(String... args) {
        System.out.println(summaryRanges(new int[]{0, 2, 3, 4, 6, 8, 9}));
        System.out.println(summaryRanges(new int[]{0, 1, 2, 4, 5, 7}));
    }
}
