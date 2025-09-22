package com.citi;

import java.util.Arrays;
import java.util.Comparator;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.ThreadLocalRandom;

public class ForkAndJoin {

    static class Sum extends RecursiveTask<Integer> {
        int[] arr;
        int start;
        int end;

        public Sum(int[] arr, int start, int end) {
            this.arr = arr;
            this.start = start;
            this.end = end;
        }

        @Override
        protected Integer compute() {
            int sum = 0;
            if (end >= start && end - start <= 5) {
                for (int i = start; i <= end; i++) {
                    sum += arr[i];
                }
            } else {
                int mid = start + (end - start) / 2;
                Sum left = new Sum(arr, start, mid);
                Sum right = new Sum(arr, mid + 1, end);
                left.fork();
                int rightSum = right.compute();
                int leftSum = left.join();
                sum = rightSum + leftSum;
            }
            return sum;
        }
    }

    public static void main(String... args) {
        int[] arr = ThreadLocalRandom.current().ints(10).toArray();
        System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.stream(arr).sum());
        ForkJoinPool pool = new ForkJoinPool(5);
        ForkJoinTask<Integer> result = pool.submit(new Sum(arr, 0, arr.length - 1));
        Integer sum = result.join();
        System.out.println(sum);
    }

}
