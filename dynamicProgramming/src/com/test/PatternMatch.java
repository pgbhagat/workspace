package com.test;

public class PatternMatch {

  public static void main(String... args) {
    PatternMatch patternMatch = new PatternMatch();
    System.out.println(patternMatch.isMatch("aaaa", "a*"));

  }

  public boolean isMatch(String s, String p) {
    if ((s == null || s.length() == 0) && (p == null || p.length() == 0)) {
      return false;
    }
    boolean[][] solution = new boolean[s.length() + 1][p.length() + 1];
    solution[0][0] = true;
    for (int i = 1; i < solution.length; i++) {
      for (int j = 1; j < solution[0].length; j++) {
        // first condition char match || ?
        if (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '.') {
          solution[i][j] = solution[i - 1][j - 1];
        } else if (p.charAt(j - 1) == '*') {
          solution[i][j] = solution[i][j - 1] || solution[i - 1][j];
        } else {
          solution[i][j] = false;
        }
      }
    }
    return solution[s.length()][p.length()];
  }

}
