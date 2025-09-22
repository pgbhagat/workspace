package com.top150.sliding.window;

import java.util.HashMap;
import java.util.Map;

public class MinWindowSubstr {
    public static String minWindow(String s, String t) {
        StringBuilder solution = new StringBuilder();
        StringBuilder result = new StringBuilder();
        Map<Character, Integer> charCountMap = new HashMap<>();
        for (Character c : t.toCharArray()) {
            charCountMap.put(c, charCountMap.getOrDefault(c, 0) + 1);
        }

        for (int i = 0; i < s.length(); i++) {
            Character c = s.charAt(i);
            if (result.length() == 0) {
                if (charCountMap.containsKey(c)) {
                    result.append(c);
                    charCountMap.put(c, charCountMap.get(c) - 1);
                    if (charCountMap.get(c) == 0) {
                        charCountMap.remove(c);
                    }
                    if (charCountMap.isEmpty()) {
                        if (solution.length() == 0 || result.length() < solution.length()) {
                            solution = new StringBuilder(result);
                        }
                        result = new StringBuilder();
                        for (Character ch : t.toCharArray()) {
                            charCountMap.put(ch, charCountMap.getOrDefault(c, 0) + 1);
                        }
                    }
                }
            } else {
                result.append(c);
                if (charCountMap.containsKey(c)) {
                    charCountMap.put(c, charCountMap.get(c) - 1);
                    if (charCountMap.get(c) == 0) {
                        charCountMap.remove(c);
                    }
                    if (charCountMap.isEmpty()) {
                        if (solution.length() == 0 || result.length() < solution.length()) {
                            solution = new StringBuilder(result);
                        }
                        result = new StringBuilder();
                        for (Character ch : t.toCharArray()) {
                            charCountMap.put(ch, charCountMap.getOrDefault(c, 0) + 1);
                        }
                    }
                }
            }
        }
        return solution.toString();
    }

    public static void main(String[] args) {
        //System.out.println(minWindow("ADOBECODEBANC", "ABC"));
      //  System.out.println(minWindow("a", "a"));
//System.out.println(minWindow("a", "aa"));
        System.out.println(minWindow("bdab", "ab"));
    }
}
