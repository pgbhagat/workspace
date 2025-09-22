package com.top150.two.pointers;

public class ValidPalindrome {
    public static boolean isPalindrome(String s) {
        if (s != null) {
            s = s.toLowerCase();
            int start = 0;
            int end = s.length() - 1;
            while (start < end) {
                while ( start <= (s.length() - 1) && !isAlphaNumeric(s.charAt(start))) {
                    start++;
                }
                while (end >= 0 && !isAlphaNumeric(s.charAt(end))) {
                    end--;
                }
                if (start < end) {
                    if (s.charAt(start) != s.charAt(end)) {
                        return false;
                    }
                    start++;
                    end--;
                }
            }
        }
        return true;
    }

    private static boolean isAlphaNumeric(char c) {
        if ((c >= '0' && c <= '9') || (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(isPalindrome("A man, a plan, a canal: Panama"));
        System.out.println(isPalindrome("race a car"));
    }
}
