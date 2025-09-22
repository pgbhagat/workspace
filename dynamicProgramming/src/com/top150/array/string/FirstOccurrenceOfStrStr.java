package com.top150.array.string;

public class FirstOccurrenceOfStrStr {
    public static int strStr(String haystack, String needle) {
        if (haystack == null || needle == null || haystack.length() < needle.length()) {
            return -1;
        }
        for (int i = 0; i <= haystack.length() - needle.length(); i++) {
            if (haystack.substring(i, i + needle.length()).equals(needle)) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String... args) {
        System.out.println(strStr("sadbutsadk", "sadk"));
        System.out.println(strStr("leetcode", "leeto"));
    }
}
