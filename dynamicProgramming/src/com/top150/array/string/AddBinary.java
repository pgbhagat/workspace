package com.top150.array.string;

public class AddBinary {
    public static String addBinary(String a, String b) {
        int carry = 0;
        StringBuilder result = new StringBuilder();

        int aIndex = a.length() - 1;
        int bIndex = b.length() - 1;

        while (aIndex >= 0 || bIndex >= 0 || carry == 1) {
            int sum = carry;
            if (aIndex >= 0) {
                sum += a.charAt(aIndex) - '0';
                aIndex--;
            }
            if (bIndex >= 0) {
                sum += b.charAt(bIndex) - '0';
                bIndex--;
            }
            result.append(sum % 2);
            carry = sum / 2;
        }
        return result.reverse().toString();
    }

}
