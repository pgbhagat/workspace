package com.top150.array.string;

import java.util.Arrays;

public class RemoveElement {

    public static int removeElement(int a[], int element) {
        int i = 0;
        for (int j = 0; j < a.length; j++) {
            if (a[j] != element) {
                a[i] = a[j];
                i++;
            }
        }
        return i;
    }


    public static void main(String... args) {
        //int a[] = new int[]{2};
        int a[] = new int[]{0, 1, 2, 2, 3, 0, 4, 2};
        System.out.println(removeElement(a, 2));
        System.out.println(Arrays.toString(a));
        System.out.println(removeElement(new int[]{3, 2, 2, 3}, 3));
        System.out.println(Arrays.toString(new int[]{3, 2, 2, 3}));


    }
}
