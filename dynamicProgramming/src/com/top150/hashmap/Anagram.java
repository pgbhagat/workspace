package com.top150.hashmap;

import java.util.HashMap;
import java.util.Map;

public class Anagram {
    public static boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        Map<Character, Integer> sIndex = new HashMap<>();
        for (Character c : s.toCharArray()) {
            sIndex.put(c, sIndex.getOrDefault(c, 0) + 1);
        }
        for (Character c : t.toCharArray()) {
            if (!sIndex.containsKey(c) || sIndex.get(c) <= 0) {
                return false;
            }
            sIndex.put(c, sIndex.get(c) - 1);
        }
        return true;
    }

    public static void main(String... args) {
        System.out.println(isAnagram("anagram", "nagaram"));
    }
}
