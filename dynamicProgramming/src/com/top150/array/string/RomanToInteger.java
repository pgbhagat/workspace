package com.top150.array.string;

import java.util.HashMap;
import java.util.Map;

public class RomanToInteger {

    static public Map<Character, Integer> romanToInt = new HashMap<>();

    static {
        romanToInt.put('I', 1);
        romanToInt.put('V', 5);
        romanToInt.put('X', 10);
        romanToInt.put('L', 50);
        romanToInt.put('C', 100);
        romanToInt.put('D', 500);
        romanToInt.put('M', 1000);
    }

    public static int romanToInt(String s) {
        int result = 0;
        for (int i = 0; i < s.length() - 1; i++) {
            if (romanToInt.get(s.charAt(i)) < romanToInt.get(s.charAt(i + 1))) {
                result -= romanToInt.get(s.charAt(i));
            } else {
                result += romanToInt.get(s.charAt(i));
            }
        }
        result += romanToInt.get(s.charAt(s.length() - 1));
        return result;
    }

    public static void main(String... args) {
        System.out.println(romanToInt("VI"));
        System.out.println(romanToInt("III"));
        System.out.println(romanToInt("LVIII"));
        System.out.println(romanToInt("MCMXCIV"));

    }

}
