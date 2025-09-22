package com.top150.array.string;

public class ExcelSheetColumnTitle {

    public String convertToTitle(int columnNumber) {
        StringBuilder result = new StringBuilder();
        while (columnNumber > 0) {
            columnNumber--;
            Character value = (char) ('A' + (columnNumber % 26));
            result.insert(0, value);
            columnNumber = columnNumber / 26;
        }
        return result.toString();
    }
}
