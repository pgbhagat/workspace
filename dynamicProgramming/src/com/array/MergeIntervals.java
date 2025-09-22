package com.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//56 Merged intervals
public class MergeIntervals {

    public int[][] merge(int[][] intervals) {
        List<int[]> mergedIntervals = new ArrayList<>();
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        for (int[] interval : intervals) {
            if (mergedIntervals.isEmpty()) {
                mergedIntervals.add(interval);
            } else {
                int[] lastInterval = mergedIntervals.get(mergedIntervals.size() - 1);
                if (lastInterval[0] <= interval[0] && interval[0] <= lastInterval[1]) {
                    lastInterval[1] = Math.max(interval[1], lastInterval[1]);
                } else {
                    mergedIntervals.add(interval);
                }
            }
        }
        return mergedIntervals.toArray(new int[mergedIntervals.size()][]);
    }
}
