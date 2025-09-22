package com.top150.array.string;

import java.util.Arrays;

public class MergeSortedArrays {

    public void mergeSortedArrays(int[] m, int mSize, int[] n, int nSize) {
        if (m != null && n != null) {
            if (m.length == mSize + nSize) {
                int i = mSize - 1;
                int j = nSize - 1;
                int k = m.length - 1;
                while (i >= 0 && j >= 0) {
                    if (m[i] >= n[j]) {
                        m[k] = m[i];
                        i--;
                    } else {
                        m[k] = n[j];
                        j--;
                    }
                    k--;
                }
                while (j >= 0) {
                    m[k] = n[j];
                    j--;
                    k--;
                }
            }
        }

    }

    public static void main(String... args) {
        int[] num1 = {2, 3, 5, 7, 8, 10};
        int[] num2 = {0, 3, 7, 9, 11, 17, 21, 36};

        int[] result = new int[num1.length + num2.length];
        System.arraycopy(num1, 0, result, 0, num1.length);
        MergeSortedArrays mergeSortedArrays = new MergeSortedArrays();
        mergeSortedArrays.mergeSortedArrays(result, num1.length, num2, num2.length);
        System.out.println(Arrays.toString(result));

    }
}
