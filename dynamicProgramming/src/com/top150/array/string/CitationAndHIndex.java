package com.top150.array.string;

public class CitationAndHIndex {
    //https://www.youtube.com/watch?v=mgG5KFTvfPw

    public static int hIndexBruteForce(int[] citations) {
        int maxHIndex = 0;
        for (int hIndex = 1; hIndex <= citations.length; hIndex++) {
            int count = 0;
            for (int j = 0; j <= citations.length - 1; j++) {
                if (citations[j] >= hIndex) {
                    count++;
                }
                if (count >= hIndex) {
                    maxHIndex = Math.max(maxHIndex, hIndex);
                    break;
                }
            }
        }
        return maxHIndex;
    }

    public static int hIndexOptimized(int[] citations) {
        int[] bucket = new int[citations.length + 1];
        for (int i = 0; i <= citations.length - 1; i++) {
            int bucketNumber = citations[i];
            if (bucketNumber >= citations.length) {
                bucketNumber = bucket.length - 1;
            }
            bucket[bucketNumber]++;
        }
        int count = 0;
        for (int hIndex = bucket.length - 1; hIndex >= 0; hIndex--) {
            count = count + bucket[hIndex];
            if (count >= hIndex)
                return hIndex;
        }
        return 0;
    }

    public static void main(String... args) {
        System.out.println("Hindex is=" + hIndexBruteForce(new int[]{3, 0, 1, 5, 6}));
        System.out.println("Hindex is=" + hIndexBruteForce(new int[]{1, 3, 1}));
        System.out.println("Hindex is=" + hIndexBruteForce(new int[]{1}));

        System.out.println("Hindex is=" + hIndexOptimized(new int[]{3, 0, 1, 5, 6}));
        System.out.println("Hindex is=" + hIndexOptimized(new int[]{1, 3, 1})); //0,2,0,1
        System.out.println("Hindex is=" + hIndexOptimized(new int[]{1}));

    }
}
