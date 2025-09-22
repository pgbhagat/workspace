package com.top150.array.string;

import java.util.Arrays;

public class RotateArrayKElements {

    public static void rotate(int[] num, int k) {
        k = k % num.length;
        if (k != 0) {
            int[] tmp = new int[num.length];
            for (int i = 0; i < num.length; i++) {
                tmp[(i + k) % num.length] = num[i];
            }
            for (int i = 0; i < tmp.length; i++) {
                num[i] = tmp[i];
            }
        }
    }


    public static void main(String[] args) {

        int[] num = new int[]{0, 0, 0, 1, 1, 2, 2, 2, 2, 3, 4, 5, 5, 5, 5, 6};
        rotate(num, 1);
        System.out.println(Arrays.toString(num));
    }
}
