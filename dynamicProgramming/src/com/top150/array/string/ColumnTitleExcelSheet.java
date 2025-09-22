package com.top150.array.string;

public class ColumnTitleExcelSheet {
    public static int titleToNumber(String columnTitle) {
        int result = 0;
        for (Character c : columnTitle.toCharArray()) {
            result = ((c - 'A' + 1) + result * 26);
        }
        return result;
    }
    public static void main(String...agrs) {
        System.out.println("AB -> " + titleToNumber("AB"));
    }
}
