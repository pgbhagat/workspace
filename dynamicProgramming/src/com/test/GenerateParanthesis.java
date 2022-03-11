package com.test;

import java.util.ArrayList;
import java.util.List;

public class GenerateParanthesis {

  public static void main(String[] args) {
    GenerateParanthesis generateParanthesis = new GenerateParanthesis();
    System.out.println(generateParanthesis.generateParenthesis(3));
  }

  public List<String> generateParenthesis(int n) {
    List<String> result = new ArrayList();
    if(n <= 0) {
      return result;
    }
    find(result, new char[2 * n], 0, 0, 0, n);
    return result;
  }
  public void find(List<String> result, char [] intermediate, int pos, int opened, int closed, int max) {
    if(pos == max * 2) {
      result.add(new String(intermediate));
    } else {
      if (opened < max) {
        intermediate[pos] = '(';
        find(result, intermediate, pos + 1, opened + 1, closed, max);
      }
      if(closed < opened) {
        intermediate[pos] = ')';
        find(result, intermediate, pos + 1, opened, closed + 1, max);
      }
    }
  }


}
