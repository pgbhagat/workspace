package com.top150.hashmap;

import java.util.HashMap;
import java.util.Map;

public class RansomNote {
    public static boolean canConstruct(String ransomNote, String magazine) {
        Map<Character, Integer> magazineChars = new HashMap<>();
        for (Character c : magazine.toCharArray()) {
            magazineChars.put(c, magazineChars.getOrDefault(c, 0) + 1);
        }
        for (Character c : ransomNote.toCharArray()) {
            if (!magazineChars.containsKey(c) || magazineChars.get(c) <= 0) {
                return false;
            }
            magazineChars.put(c, magazineChars.get(c) - 1);
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(canConstruct("aa", "aab"));
    }
}
