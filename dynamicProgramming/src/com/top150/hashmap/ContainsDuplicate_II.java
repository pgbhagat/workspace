package com.top150.hashmap;

import java.util.HashMap;
import java.util.Map;

public class ContainsDuplicate_II {
    public static boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, Integer> indices = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (!indices.containsKey(nums[i])) {
                indices.put(nums[i], i);
            } else {
                int j = indices.get(nums[i]);
                if (k >= i - j) {
                    return true;
                }
                indices.put(nums[i], i);
            }
        }
        return false;
    }

    public static void main(String... args) {
        System.out.println(containsNearbyDuplicate(new int[]{1, 2, 3, 1}, 3));
        System.out.println(containsNearbyDuplicate(new int[]{1, 0, 1, 1}, 1));
        System.out.println(containsNearbyDuplicate(new int[]{1, 2, 3, 1, 2, 3}, 2));
    }
}
