package com.top150.array.string;

public class RemoveDupsFromSortedArray {
    public static int removeDuplicates(int a[]) {
        int j = 1;
        for (int i = 1; i < a.length; i++) {
            if (a[i] != a[i - 1]) {
                a[j] = a[i];
                j++;
            }
        }
        return j;
    }

    public static void main(String... args) {
        System.out.println(removeDuplicates(new int[]{2, 2, 3, 4, 5, 6, 6, 7, 9,}));
        System.out.println(removeDuplicates(new int[]{2, 3, 4, 5, 6, 7, 9,}));
        System.out.println(removeDuplicates(new int[]{2}));
        System.out.println(removeDuplicates(new int[]{2, 2, 2}));
        System.out.println(removeDuplicates(new int[]{1, 1, 2}));
    }
}
