package leetcode.graph.topological;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/*

There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1.
You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.

For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
Return the ordering of courses you should take to finish all courses. If there are many valid answers, return any of them.
If it is impossible to finish all courses, return an empty array.


Example 1:
    Input:
        numCourses = 2,
        prerequisites = [[1,0]]
    Output:
        [0,1]
    Explanation:
        There are a total of 2 courses to take. To take course 1 you should have finished course 0.
        So the correct course order is [0,1].

Example 2:
    Input:
        numCourses = 4,
        prerequisites = [[1,0],[2,0],[3,1],[3,2]]
    Output:
        [0,2,1,3]
    Explanation:
        There are a total of 4 courses to take.
        To take course 3 you should have finished both courses 1 and 2.
        Both courses 1 and 2 should be taken after you finished course 0.
        So one correct course order is [0,1,2,3].
        Another correct ordering is [0,2,1,3].

Example 3:
    Input:
        numCourses = 1,
        prerequisites = []
    Output:
        [0]


Constraints:
    1 <= numCourses <= 2000
    0 <= prerequisites.length <= numCourses * (numCourses - 1)
    prerequisites[i].length == 2
    0 <= ai, bi < numCourses
    ai != bi
    All the pairs [ai, bi] are distinct.

*/

public class _0210_Course_Schedule_II {

    // Topological sorting - BFS Approach: find a cycle in a graph.
    //      0. Build Graph using Adjacent List representation and In-Degree Array for each node;
    //      1. Enqueue all nodes with zero In-Degree value;
    //      2. BFS
    //          2.1 Add current visiting node into order list;
    //          2.2 Update In-Degrees of each neighbour of current visiting node;
    //          2.3 Enqueue the neighbour if the updated In-Degree becomes zero;
    //      3. Check the size of the order list;
    class Solution_Topological {
        public int[] findOrder_Topological(int numCourses, int[][] prerequisites) {
            // Build Graph using Adjacent List with given edges
            List<Integer>[] adjacentList = new List[numCourses];
            int[] inDegrees = new int[numCourses];
            for (int[] prerequisite : prerequisites) {
                if (adjacentList[prerequisite[1]] == null) {
                    adjacentList[prerequisite[1]] = new ArrayList<>();
                }
                adjacentList[prerequisite[1]].add(prerequisite[0]);
                inDegrees[prerequisite[0]]++; // Build inDegree array
            }

            // Enqueue nodes with zero inDegree
            Queue<Integer> zeroInDegreeQueue = new ArrayDeque<>();
            for (int i = 0; i < inDegrees.length; ++i) {
                if (inDegrees[i] == 0) {
                    zeroInDegreeQueue.offer(i);
                }
            }

            // BFS
            int index = 0;
            int[] order = new int[numCourses];
            while (!zeroInDegreeQueue.isEmpty()) {
                int curr = zeroInDegreeQueue.poll();
                order[index++] = curr;
                if (adjacentList[curr] != null) {
                    for (int next : adjacentList[curr]) {
                        // -1 inDegree for each neighbour of current node
                        // Enqueue if inDegree of the neighbour is zero
                        if (--inDegrees[next] == 0) {
                            zeroInDegreeQueue.offer(next);
                        }
                    }
                }
            }
            return index == order.length ? order : new int[0];
        }
    }

    // Topological sorting - dfs Approach
    //      0. Build Graph using Adjacent List representation;
    //      1. For each node, visit if it is not visited;
    //      2. Update the visit status of the current node to be 1 (in the frontier);
    //          2.1 Recursively visit each neighbour (for loop) of the current node;
    //          2.2 Update the visit status of the current node to be 2 (finished visit);
    //          2.3 Update order list;
    class Solution_DFS {
        public int[] findOrder(int numCourses, int[][] prerequisites) {
            List<Integer>[] adjacentList = buildGraph(numCourses, prerequisites);
            int[] visited = new int[numCourses];
            int[] res = new int[numCourses];
            // We save the index in the first element of the res;
            // This is possible because we add the course into the res array from the tail to the head;
            // So res[0] will be overridden at last.
            res[0] = res.length - 1;
            for (int i = 0; i < numCourses; ++i) {
                if (visited[i] == 0) {
                    if (containsCycle(adjacentList, i, visited, res)) {
                        return new int[0];
                    }
                }
            }
            return res;
        }

        private boolean containsCycle(List<Integer>[] adjacentList, int current, int[] visited, int[] order) {
            if (visited[current] == 0) {
                visited[current] = 1; // Current node is in frontier
                if (adjacentList[current] != null) {
                    for (int next : adjacentList[current]) {
                        // If the next node is already in the frontier
                        if (visited[next] == 1 || containsCycle(adjacentList, next, visited, order)) {
                            return true;
                        }
                    }
                }
                visited[current] = 2; // Current node is visited
                order[order[0]--] = current; // Add current node into the order list after completing visiting the current node
            }
            return false;
        }

        private List<Integer>[] buildGraph(int numCourses, int[][] prerequisites) {
            // Build graph as Adjacent List representation given edges
            List<Integer>[] adjacentList = new List[numCourses];
            for (int[] prerequisite : prerequisites) {
                // Initialize an ArrayList if it's first time visit the node
                if (adjacentList[prerequisite[1]] == null) {
                    adjacentList[prerequisite[1]] = new ArrayList<>();
                }
                adjacentList[prerequisite[1]].add(prerequisite[0]); // Add the neighbour into neighbour list
            }
            return adjacentList;
        }
    }


}