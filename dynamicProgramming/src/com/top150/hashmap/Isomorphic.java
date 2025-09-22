package com.top150.hashmap;

import java.util.HashMap;
import java.util.Map;

public class Isomorphic {
    public static boolean isIsomorphic(String s, String t) {
        Map<Character, Integer> sIndex = new HashMap<>();
        Map<Character, Integer> tIndex = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            if (!sIndex.containsKey(s.charAt(i))) {
                sIndex.put(s.charAt(i), i);
            }
            if (!tIndex.containsKey(t.charAt(i))) {
                tIndex.put(t.charAt(i), i);
            }
            if (!sIndex.get(s.charAt(i)).equals(tIndex.get(t.charAt(i)))) {
                return false;
            }
        }
        return true;
    }

    public static void main(String... agrs) {
        System.out.println(isIsomorphic("egg", "add"));
    }
}
