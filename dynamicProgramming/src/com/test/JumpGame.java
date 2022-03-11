package com.test;

import java.util.Map;

public class JumpGame {

  static Index[] indices;

  public static void main(String[] args) {
    System.out.println(canJump(new int[]{2, 3, 1, 1, 4}));
    System.out.println(canJump(new int[]{3, 2, 1, 0, 4}));
  }

  public static boolean canJumpFromIndex(int position, int[] nums) {
    Map<Character, String> mapping = Map
        .of('2', "abc", '3', "def", '4', "ghi", '5', "jkl", '6', "mno", '7', "pqrs", '8', "tuv", '9', "wxyz");

    if (indices[position] == Index.UNKNOWN) {
      int furthestJump = Math.min(position + nums[position], nums.length - 1);
      for (int i = position + 1; i <= furthestJump; i++) {
        if (canJumpFromIndex(i, nums)) {
          indices[position] = Index.GOOD;
          break;
        }
      }
      indices[position] = (indices[position] == Index.UNKNOWN ? Index.BAD : indices[position]);
    }
    return indices[position] == Index.GOOD;

  }

  public static boolean canJump(int[] nums) {
    indices = new Index[nums.length];
    for (int i = 0; i < indices.length; i++) {
      indices[i] = Index.UNKNOWN;
    }
    indices[indices.length - 1] = Index.GOOD;

    return canJumpFromIndex(0, nums);
  }

  enum Index {
    GOOD, BAD, UNKNOWN
  }


}
