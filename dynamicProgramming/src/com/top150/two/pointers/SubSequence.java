package com.top150.two.pointers;

public class SubSequence {
    public static boolean isSubsequence(String s, String t) {
        if (s != null && t != null) {
            int sIndex = 0;
            int tIndex = 0;
            while (sIndex < s.length() && tIndex < t.length()) {
                if (s.charAt(sIndex) == t.charAt(tIndex)) {
                    sIndex++;

                }
                tIndex++;
            }
            if (sIndex == s.length()) {
                return true;
            }
        }
        return false;
    }

    public static void main(String... args) {
        System.out.println(isSubsequence("abc", "ahbgdc"));
        System.out.println(isSubsequence("axc", "ahbgdc"));
    }

}

