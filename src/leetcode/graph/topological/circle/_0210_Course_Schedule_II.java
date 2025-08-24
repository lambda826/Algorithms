package leetcode.graph.topological.circle;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/*
Description:
    There are a total of numCourses courses you must take, labeled from 0 to numCourses - 1.
    You are given an array prerequisites where prerequisites[i] = [ai, bi] meaning you must take course bi
    before course ai (i.e., bi → ai). Return one valid ordering of courses you can take to finish all courses.
    If there are many valid answers, return any of them. If it is impossible to finish all courses, return an empty array.


Examples:
    Example 1:
        Input: numCourses = 2, prerequisites = [[1,0]]
        Output: [0,1]
        Explanation: You must take 0 before 1.

    Example 2:
        Input: numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
        Output: [0,2,1,3]
        Explanation: 0 must be before 1 and 2; 1 and 2 must be before 3. [0,1,2,3] is also valid.

    Example 3:
        Input: numCourses = 1, prerequisites = []
        Output: [0]


Constraints:
    1 <= numCourses <= 2000
    0 <= prerequisites.length <= numCourses * (numCourses - 1)
    prerequisites[i].length == 2
    0 <= ai, bi < numCourses
    ai != bi
    All pairs [ai, bi] are distinct.
*/
public class _0210_Course_Schedule_II {

    /**
     * <h2>210. Course Schedule II — Kahn Topological Sort (BFS)</h2>
     *
     * <h3>Approach</h3>
     * Build adjacency lists for edges {@code bi -> ai} and an {@code indegree} array.
     * Enqueue all nodes with {@code indegree == 0}. Repeatedly pop a node, append it to the order,
     * and decrease the indegree of its neighbors; whenever a neighbor's indegree becomes 0, enqueue it.
     * If we output exactly {@code numCourses} nodes, we have a valid ordering; otherwise, a cycle exists.
     *
     * <h3>Algorithm</h3>
     * <ol>
     *   <li>Initialize {@code List<Integer>[] g} with {@code numCourses} empty lists.</li>
     *   <li>For each pair {@code [a, b]}, do {@code g[b].add(a)} and {@code indegree[a]++}.</li>
     *   <li>Push all nodes {@code i} with {@code indegree[i] == 0} into a queue.</li>
     *   <li>While queue not empty: pop {@code u}, append to answer; for each {@code v in g[u]}, do
     *       {@code --indegree[v]} and if it becomes 0, enqueue {@code v}.</li>
     *   <li>If the answer length is {@code numCourses}, return it; else return empty array.</li>
     * </ol>
     *
     * <h3>Complexity</h3>
     * Let {@code n = numCourses} and {@code m = prerequisites.length}.
     * <ul>
     *   <li>Time: {@code O(n + m)} — each node and edge processed at most once.</li>
     *   <li>Space: {@code O(n + m)} — adjacency list + indegree array + queue.</li>
     * </ul>
     */
    static class Solution_Topological {
        public int[] findOrder(int numCourses, int[][] prerequisites) {
            List<Integer>[] g = new List[numCourses];
            for (int i = 0; i < numCourses; i++) {
                g[i] = new ArrayList<>();
            }

            int[] indegree = new int[numCourses];
            for (int[] p : prerequisites) {
                int a = p[0];
                int b = p[1];
                g[b].add(a);        // b -> a
                indegree[a]++;      // indegree of a
            }

            Queue<Integer> q = new ArrayDeque<>();
            for (int i = 0; i < numCourses; i++) {
                if (indegree[i] == 0) {
                    q.offer(i);
                }
            }

            int[] order = new int[numCourses];
            int idx = 0;

            while (!q.isEmpty()) {
                int u = q.poll();
                order[idx] = u;
                idx++;

                for (int v : g[u]) {
                    indegree[v]--;
                    if (indegree[v] == 0) {
                        q.offer(v);
                    }
                }
            }

            if (idx == numCourses) {
                return order;
            }
            return new int[0];
        }
    }

    /**
     * <h2>210. Course Schedule II — DFS (postorder) with cycle detection</h2>
     *
     * <h3>Approach</h3>
     * Run DFS on the directed graph and use a 3-color array to detect cycles
     * ({@code 0 = unvisited, 1 = visiting, 2 = done}). On exit (postorder) of a node, append it to the answer.
     * Reverse/post-fill to obtain a topological order. If any back-edge (to {@code visiting}) is found, return empty.
     *
     * <h3>Algorithm</h3>
     * <ol>
     *   <li>Build adjacency lists {@code g[b].add(a)} for each prerequisite {@code [a,b]}.</li>
     *   <li>DFS each unvisited node. Upon entering, mark {@code visiting}; upon leaving, mark {@code done}
     *       and write the node into the result from right to left.</li>
     *   <li>If a back-edge to a {@code visiting} node is encountered, a cycle exists &rarr; return empty.</li>
     * </ol>
     *
     * <h3>Complexity</h3>
     * With {@code n = numCourses}, {@code m = prerequisites.length}:
     * <ul>
     *   <li>Time: {@code O(n + m)} — each node and edge explored once.</li>
     *   <li>Space: {@code O(n + m)} — adjacency list plus recursion stack up to {@code O(n)}.</li>
     * </ul>
     */
    static class Solution_DFS {
        public int[] findOrder(int numCourses, int[][] prerequisites) {
            List<Integer>[] g = new List[numCourses];
            for (int i = 0; i < numCourses; i++) {
                g[i] = new ArrayList<>();
            }
            for (int[] p : prerequisites) {
                int a = p[0];
                int b = p[1];
                g[b].add(a); // b -> a
            }

            int[] state = new int[numCourses]; // 0=unvisited, 1=visiting, 2=done
            int[] res = new int[numCourses];
            // store write index in res[0], fill from right to left
            res[0] = numCourses - 1;

            for (int i = 0; i < numCourses; i++) {
                if (state[i] == 0) {
                    boolean cycle = dfs(i, g, state, res);
                    if (cycle) {
                        return new int[0];
                    }
                }
            }
            return res;
        }

        private boolean dfs(int u, List<Integer>[] g, int[] state, int[] res) {
            if (state[u] == 1) {
                return true; // cycle
            }
            if (state[u] == 2) {
                return false; // already processed
            }

            state[u] = 1; // visiting
            for (int v : g[u]) {
                boolean cycle = dfs(v, g, state, res);
                if (cycle) {
                    return true;
                }
            }
            state[u] = 2; // done

            int writeIdx = res[0];
            res[writeIdx] = u;
            res[0] = writeIdx - 1;
            return false;
        }
    }
}