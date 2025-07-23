package leetcode.graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1.
You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course ai first if you want to take course bi.

For example, the pair [0, 1] indicates that you have to take course 0 before you can take course 1.
Prerequisites can also be indirect. If course a is a prerequisite of course b, and course b is a prerequisite of course c, then course a is a prerequisite of course c.

You are also given an array queries where queries[j] = [uj, vj]. For the jth query, you should answer whether course uj is a prerequisite of course vj or not.

Return a boolean array answer, where answer[j] is the answer to the jth query.


Example 1:
    Input: numCourses = 2, prerequisites = [[1,0]], queries = [[0,1],[1,0]]
    Output: [false,true]
    Explanation: The pair [1, 0] indicates that you have to take course 1 before you can take course 0.
    Course 0 is not a prerequisite of course 1, but the opposite is true.

Example 2:
    Input: numCourses = 2, prerequisites = [], queries = [[1,0],[0,1]]
    Output: [false,false]
    Explanation: There are no prerequisites, and each course is independent.

Example 3:
    Input: numCourses = 3, prerequisites = [[1,2],[1,0],[2,0]], queries = [[1,0],[1,2]]
    Output: [true,true]


Constraints:
    2 <= numCourses <= 100
    0 <= prerequisites.length <= (numCourses * (numCourses - 1) / 2)
    prerequisites[i].length == 2
    0 <= ai, bi <= numCourses - 1
    ai != bi
    All the pairs [ai, bi] are unique.
    The prerequisites graph has no cycles.
    1 <= queries.length <= 104
    0 <= ui, vi <= numCourses - 1
    ui != vi

*/
public class _1462_Course_Schedule_IV {

    class Solution {
        public List<Boolean> checkIfPrerequisite(int numCourses, int[][] prerequisites, int[][] queries) {
            List<Integer>[] graph = buldGraph(numCourses, prerequisites);
            List<Boolean> res = new ArrayList<>();
            boolean[][] reachable = new boolean[numCourses][numCourses];
            for (int[] query : queries) {
                dfs(query[0], reachable, graph);
                res.add(reachable[query[0]][query[1]]);
            }
            return res;
        }

        private void dfs(int curr, boolean[][] reachable, List<Integer>[] graph) {
            if (!reachable[curr][curr] && graph[curr] != null) {
                for (int nei : graph[curr]) {
                    dfs(nei, reachable, graph);
                    for (int i = 0; i < reachable.length; ++i) {
                        reachable[curr][i] |= reachable[nei][i];
                    }
                }
            }
            reachable[curr][curr] = true;
        }

        private List<Integer>[] buldGraph(int numCourses, int[][] prerequisites) {
            List<Integer>[] graph = new List[numCourses];
            for (int[] prerequisite : prerequisites) {
                if (graph[prerequisite[0]] == null) {
                    graph[prerequisite[0]] = new ArrayList<>();
                }
                graph[prerequisite[0]].add(prerequisite[1]);
            }
            return graph;
        }
    }

    class Solution2 {
        public List<Boolean> checkIfPrerequisite(int numCourses, int[][] prerequisites, int[][] queries) {
            Set<Integer>[] graph = buldGraph(numCourses, prerequisites);
            List<Boolean> res = new ArrayList<>();
            boolean[] visited = new boolean[numCourses];
            for (int[] query : queries) {
                res.add(prerequisite(query[0], graph, visited).contains(query[1]));
            }
            return res;
        }

        private Set<Integer>[] buldGraph(int numCourses, int[][] prerequisites) {
            Set<Integer>[] graph = new Set[numCourses];
            for (int[] prerequisite : prerequisites) {
                if (graph[prerequisite[0]] == null) {
                    graph[prerequisite[0]] = new HashSet<>();
                }
                graph[prerequisite[0]].add(prerequisite[1]);
            }
            return graph;
        }

        private Set<Integer> prerequisite(int curr, Set<Integer>[] graph, boolean[] visited) {
            Set<Integer> newNei = new HashSet<>();
            if (graph[curr] != null) {
                newNei.addAll(graph[curr]);
                if (!visited[curr]) {
                    for (int nei : graph[curr]) {
                        newNei.addAll(prerequisite(nei, graph, visited));
                    }
                }
            }
            visited[curr] = true;
            graph[curr] = newNei;
            return graph[curr];
        }
    }
}
