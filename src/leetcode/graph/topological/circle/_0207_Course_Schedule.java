package leetcode.graph.topological.circle;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/*
Description:
    There are a total of numCourses courses labeled from 0 to numCourses - 1.
    You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi
    before course ai (i.e., bi → ai).
    Return true if you can finish all courses. Otherwise, return false.


Examples:
    Example 1:
        Input: numCourses = 2, prerequisites = [[1,0]]
        Output: true
        Explanation: To take course 1 you must take course 0 first, which is possible.

    Example 2:
        Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
        Output: false
        Explanation: There is a cycle 0 → 1 → 0, so it's impossible to finish all courses.


Constraints:
    1 <= numCourses <= 100000
    0 <= prerequisites.length <= 5000
    prerequisites[i].length == 2
    0 <= ai, bi < numCourses
    All pairs prerequisites[i] are unique
*/
public class _0207_Course_Schedule {

    /**
     * <h2>207. Course Schedule — Kahn Topological Sort (BFS)</h2>
     *
     * <h3>Approach</h3>
     * Build a directed graph from prerequisites (edges {@code bi -> ai}). Maintain an indegree array.
     * Push all nodes with indegree 0 into a queue and repeatedly remove them, decreasing the indegree
     * of their neighbors. If we can remove all {@code numCourses} nodes, there is no cycle and all
     * courses can be finished.
     *
     * <h3>Algorithm</h3>
     * <ol>
     *   <li>Adjacency list {@code g[0..numCourses-1]} and {@code indegree[ ]} initialized from prerequisites.</li>
     *   <li>Enqueue all nodes with {@code indegree[u] == 0}.</li>
     *   <li>BFS: pop {@code u}; for each {@code v in g[u]}, decrement {@code indegree[v]}; if it becomes 0, enqueue.</li>
     *   <li>Count how many nodes are popped; return {@code count == numCourses}.</li>
     * </ol>
     *
     * <h3>Complexity</h3>
     * Let {@code n = numCourses}, {@code m = prerequisites.length}.
     * <ul>
     *   <li>Time: {@code O(n + m)} — each node and edge is processed at most once.</li>
     *   <li>Space: {@code O(n + m)} — adjacency list + indegree + queue.</li>
     * </ul>
     */
    static class Solution_Topological {
        public boolean canFinish(int numCourses, int[][] prerequisites) {
            List<Integer>[] g = new List[numCourses];
            for (int i = 0; i < numCourses; i++) {
                g[i] = new ArrayList<>();
            }

            int[] indegree = new int[numCourses];
            for (int[] p : prerequisites) {
                int a = p[0];
                int b = p[1];
                g[b].add(a);      // b -> a
                indegree[a]++;    // a's indegree
            }

            Queue<Integer> q = new ArrayDeque<>();
            for (int i = 0; i < numCourses; i++) {
                if (indegree[i] == 0) {
                    q.offer(i);
                }
            }

            int taken = 0;
            while (!q.isEmpty()) {
                int u = q.poll();
                taken++;

                for (int v : g[u]) {
                    indegree[v]--;
                    if (indegree[v] == 0) {
                        q.offer(v);
                    }
                }
            }

            return taken == numCourses;
        }
    }

    /**
     * <h2>207. Course Schedule — DFS Cycle Detection</h2>
     *
     * <h3>Approach</h3>
     * Depth-first search with a 3-color (state) array {@code 0=unvisited, 1=visiting, 2=done}.
     * If during DFS we visit an edge to a node that is currently {@code visiting}, we have a back-edge (cycle).
     * No cycle implies all courses can be finished.
     *
     * <h3>Algorithm</h3>
     * <ol>
     *   <li>Build adjacency list {@code g} from prerequisites.</li>
     *   <li>For each node, if unvisited, DFS. Mark node {@code visiting} on entry, {@code done} on exit.</li>
     *   <li>If you ever reach a {@code visiting} node from the current path, return false (cycle).</li>
     * </ol>
     *
     * <h3>Complexity</h3>
     * Let {@code n = numCourses}, {@code m = prerequisites.length}.
     * <ul>
     *   <li>Time: {@code O(n + m)} — each node/edge is explored once.</li>
     *   <li>Space: {@code O(n + m)} — adjacency list plus recursion stack up to {@code O(n)}.</li>
     * </ul>
     */
    static class Solution_DFS_FindCircle {
        public boolean canFinish(int numCourses, int[][] prerequisites) {
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
            for (int i = 0; i < numCourses; i++) {
                if (state[i] == 0) {
                    boolean hasCycle = dfsHasCycle(g, state, i);
                    if (hasCycle) {
                        return false;
                    }
                }
            }
            return true;
        }

        private boolean dfsHasCycle(List<Integer>[] g, int[] state, int u) {
            if (state[u] == 1) {
                return true;
            } else {
                state[u] = 1;
                for (int v : g[u]) {
                    if (dfsHasCycle(g, state, v)) {
                        return true;
                    }
                }
                state[u] = 2;
            }
            return false;
        }
    }

}