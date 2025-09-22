package com.dynamicprogramming;

import java.util.HashMap;
import java.util.Map;

public class DecodeWays {

    public static int numDecodings(String s) {
        Map<Integer, Integer> result = new HashMap<>();
        return helper(s, 0, result);

    }

    public static int helper(String s, int i, Map<Integer, Integer> result) {

        if (s == null || s.isEmpty() || i >= s.length()) {
            return 1;
        }
        if (s.charAt(i) == '0') {
            return 0;
        }
        if (result.containsKey(i)) {
            return result.get(i);
        }
        int ways = helper(s, i + 1, result);
        if ((i + 1) < s.length()) {
            int two = Integer.parseInt(s.substring(i, i + 2));
            if (two >= 10 && two <= 26) {
                ways += helper(s, i + 2, result);
            }
        }
        result.put(i, ways);
        return ways;
    }

    public static void main(String... args) {
        System.out.println(numDecodings("12"));
    }

}
