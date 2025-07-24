package leetcode.graph.topological;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/*

You are given an integer n, which indicates that there are n courses labeled from 1 to n.
You are also given an array relations where relations[i] = [prevCoursei, nextCoursei],
representing a prerequisite relationship between course prevCoursei and course nextCoursei: course prevCoursei has to be taken before course nextCoursei.
In one semester, you can take any number of courses as long as you have taken all the prerequisites in the previous semester for the courses you are taking.
Return the minimum number of semesters needed to take all courses. If there is no way to take all the courses, return -1.


Example 1:
    Input: n = 3, relations = [[1,3],[2,3]]
    Output: 2
    Explanation:
        The figure above represents the given graph.
        In the first semester, you can take courses 1 and 2.
        In the second semester, you can take course 3.

Example 2:
    Input: n = 3, relations = [[1,2],[2,3],[3,1]]
    Output: -1
    Explanation:
        No course can be studied because they are prerequisites of each other.


Constraints:
    1 <= n <= 5000
    1 <= relations.length <= 5000
    relations[i].length == 2
    1 <= prevCoursei, nextCoursei <= n
    prevCoursei != nextCoursei
    All the pairs [prevCoursei, nextCoursei] are unique.

*/
public class _1136_Parallel_Courses {

    class Solution {
        public int minimumSemesters(int n, int[][] relations) {
            List<Integer>[] graph = new List[n + 1];
            int[] degree = new int[n + 1];
            for (int[] relation : relations) {
                if (graph[relation[0]] == null) {
                    graph[relation[0]] = new ArrayList<>();
                }
                graph[relation[0]].add(relation[1]);
                degree[relation[1]]++;
            }

            Queue<Integer> queue = new ArrayDeque<>();
            for (int i = 1; i < degree.length; ++i) {
                if (degree[i] == 0) {
                    queue.offer(i);
                }
            }

            int semester = 0;
            int numCourse = 0;
            while (!queue.isEmpty()) {
                int size = queue.size();
                semester++;
                while (size-- > 0) {
                    int curr = queue.poll();
                    numCourse++;
                    if (graph[curr] != null) {
                        for (int next : graph[curr]) {
                            if (--degree[next] == 0) {
                                queue.offer(next);
                            }
                        }
                    }
                }
            }
            return numCourse == n ? semester : -1;
        }
    }
}
