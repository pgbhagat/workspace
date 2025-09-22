package com.graph;

import java.util.*;

//https://www.youtube.com/watch?v=EgI5nU9etnU
public class CourseSchedule {

    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> preReqs = new HashMap<>();
        for (int i = 0; i < numCourses; i++) {
            preReqs.put(i, new ArrayList<>());
        }

        for (int[] prereq : prerequisites) {
            preReqs.get(prereq[0]).add(prereq[1]);
        }
        Set<Integer> visited = new HashSet<>();
        for (int i = 0; i < numCourses; i++) {
            if (!dfs(i, preReqs, visited)) {
                return false;
            }
        }
        return true;
    }

    private static boolean dfs(int course, Map<Integer, List<Integer>> preReqs, Set<Integer> visited) {
        if (visited.contains(course)) {
            return false;
        }
        visited.add(course);
        for (int preReq : preReqs.get(course)) {
            if (!dfs(preReq, preReqs, visited)) {
                return false;
            }
        }
        visited.remove(course);
        preReqs.get(course).clear();

        return true;
    }

    public static void main(String... args) {
        System.out.println(canFinish(2, new int[][]{{1, 0}, {0, 1}}));
    }

}
