package com.top150.array.string;

import java.util.HashMap;
import java.util.Map;

public class IsomorphicStrings {

    public static boolean isIsomorphic(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        Map<Character, Character> sMap = new HashMap<>();
        Map<Character, Character> tMap = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            Character c = s.charAt(i);
            if (sMap.containsKey(c)) {
                if (sMap.get(c) != t.charAt(i)) {
                    return false;
                }
            } else {
                if (tMap.containsKey(t.charAt(i))) {
                    if (tMap.get(t.charAt(i)) != c) {
                        return false;
                    }
                }
                tMap.put(t.charAt(i), c);
                sMap.put(c, t.charAt(i));
            }
        }
        return true;
    }
}
