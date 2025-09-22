package com.top150.hashmap;

import java.util.HashMap;
import java.util.Map;

public class WordPattern {
    public static boolean wordPattern(String pattern, String s) {
        String[] words = s.split(" ");
        Map<String, Integer> sIndex = new HashMap<>();
        Map<Character, Integer> pIndex = new HashMap<>();

        if (words.length != pattern.length()) {
            return false;
        }
        for (int i = 0; i < pattern.length(); i++) {
            if (!pIndex.containsKey(pattern.charAt(i))) {
                pIndex.put(pattern.charAt(i), i);
            }
            if (!sIndex.containsKey(words[i])) {
                sIndex.put(words[i], i);
            }
            if (!pIndex.get(pattern.charAt(i)).equals(sIndex.get(words[i]))) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(wordPattern("abba", "dog cat cat dog"));
    }
}
