package leetcode.graph.topological.circle;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/*

There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1.
You are given an array prerequisites where prerequisites[i] = [a(i), b(i)] indicates that you must take course bi first if you want to take course a(i).
    For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
Return true if you can finish all courses. Otherwise, return false.


Example 1:
    Input:
        numCourses = 2,
        prerequisites = [[1,0]]
    Output:
        true
    Explanation:
        There are a total of 2 courses to take.
        To take course 1 you should have finished course 0. So it is possible.

Example 2:
    Input:
        numCourses = 2,
        prerequisites = [[1,0],[0,1]]
    Output:
        false
    Explanation:
        There are a total of 2 courses to take.
        To take course 1 you should have finished course 0, and to take course 0 you should also have finished course 1.
        So it is impossible.


Constraints:
    1 <= numCourses <= 10^5
    0 <= prerequisites.length <= 5000
    prerequisites[i].length == 2
    0 <= a(i), b(i) < numCourses
    All the pairs prerequisites[i] are unique.

*/

public class _0207_Course_Schedule {

    class Solution_Topological {
        public boolean canFinish(int numCourses, int[][] prerequisites) {
            List<Integer>[] graph = new List[numCourses];
            int[] indegree = new int[numCourses];
            buildGraph(prerequisites, graph, indegree);
            Queue<Integer> zeroIndegree = new ArrayDeque<>();
            for (int i = 0; i < graph.length; ++i) {
                if (indegree[i] == 0) {
                    zeroIndegree.offer(i);
                }
            }
            int n = 0;
            while (!zeroIndegree.isEmpty()) {
                int course = zeroIndegree.poll();
                n++;
                if (graph[course] != null) {
                    for (int next : graph[course]) {
                        indegree[next]--;
                        if (indegree[next] == 0) {
                            zeroIndegree.offer(next);
                        }
                    }
                }
            }
            return n == numCourses;
        }

        private void buildGraph(int[][] prerequisites, List<Integer>[] graph, int[] indegree) {
            for (int[] prerequisite : prerequisites) {
                if (graph[prerequisite[1]] == null) {
                    graph[prerequisite[1]] = new ArrayList<>();
                }
                graph[prerequisite[1]].add(prerequisite[0]);
                indegree[prerequisite[0]]++;
            }
        }
    }

    class Solution_DFS_FindCircle {
        public boolean canFinish(int numCourses, int[][] prerequisites) {
            List<Integer>[] graph = new List[numCourses];
            buildGraph(prerequisites, graph);
            int[] visited = new int[numCourses];
            for (int i = 0; i < numCourses; ++i) {
                if (visited[i] == 0 && findCycle(graph, visited, i)) {
                    return false;
                }
            }
            return true;
        }

        private boolean findCycle(List<Integer>[] graph, int[] visited, int n) {
            if (visited[n] == 1) {
                return true;
            } else {
                visited[n] = 1;
                if (graph[n] != null) {
                    for (int next : graph[n]) {
                        if (visited[next] != 2) {
                            if (findCycle(graph, visited, next)) {
                                return true;
                            }
                        }
                    }
                }
                visited[n] = 2;
                return false;
            }
        }

        private void buildGraph(int[][] prerequisites, List<Integer>[] graph) {
            for (int[] prerequisite : prerequisites) {
                if (graph[prerequisite[1]] == null) {
                    graph[prerequisite[1]] = new ArrayList<>();
                }
                graph[prerequisite[1]].add(prerequisite[0]);
            }
        }
    }

}