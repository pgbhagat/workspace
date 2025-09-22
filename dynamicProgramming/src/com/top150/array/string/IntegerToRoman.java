package com.top150.array.string;

public class IntegerToRoman {

    static public int[] intValues = new int[]{1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
    static public String[] roman = new String[]{"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

    public static String integerToRoman(int number) {
        StringBuilder ret = new StringBuilder();
        for (int i = 0; i < intValues.length; i++) {
            if (number == 0) {
                break;
            }
            while (number >= intValues[i]) {
                number -= intValues[i];
                ret.append(roman[i]);
            }
        }
        return ret.toString();
    }

    public static void main(String... args) {
        System.out.println(integerToRoman(41));
        System.out.println(integerToRoman(3749));
        System.out.println(integerToRoman(58));
        System.out.println(integerToRoman(1994));


    }

}
