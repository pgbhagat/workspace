package com.top150.sliding.window;

import java.util.HashSet;
import java.util.Set;

public class LongestSubStrNonRepeatingChars {
    public static int lengthOfLongestSubstring(String s) {
        int left = 0;
        int longest = 0;
        Set<Character> set = new HashSet<>();
        for (int right = 0; right < s.length(); right++) {
            while (set.contains(s.charAt(right))) {
                set.remove(s.charAt(left));
                left++;
            }
            set.add(s.charAt(right));
            longest = Math.max(longest, right - left + 1);
        }
        return longest;
    }

    public static void main(String... args) {
        System.out.println(lengthOfLongestSubstring("abcabcbb"));
        System.out.println(lengthOfLongestSubstring("bbbbb"));
        System.out.println(lengthOfLongestSubstring("pwwkew"));

    }
}
