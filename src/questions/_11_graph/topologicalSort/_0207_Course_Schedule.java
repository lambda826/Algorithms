package questions._11_graph.topologicalSort;

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

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Topological sorting - dfs Approach: find a cycle in a graph.
    //      0. Build Graph using Adjacent List representation;
    //      1. For each node, visit if it is not visited;
    //      2. Update the visit status of the current node to be 1 (in the frontier);
    //          2.1 Recursively visit each neighbour (for loop) of the current node;
    //          2.2 Update the visit status of the current node to be 2 (finished visit);
    //
    // Takeaways:
    //      1. Adjacent List (List<Integer>[] adjacentList = new List[numberOfNodes]) to represent a DAG;
    //      2. Use an int[] array to indicate the status of the node:
    //          visited[i] == 0 means node i is unvisited;
    //          visited[i] == 1 means node i is in frontier;
    //          visited[i] == 2 means node i is visited;

    class Solution_DFS {
        public boolean canFinish(int numCourses, int[][] prerequisites) {
            List<Integer>[] adjacentList = buildGraph(numCourses, prerequisites);
            int[] visited = new int[numCourses];
            for (int i = 0; i < numCourses; ++i) {
                if (visited[i] == 0 && containsCycleDFS(adjacentList, i, visited)) {
                    return false;
                }
            }
            return true;
        }

        private boolean containsCycleDFS(List<Integer>[] adjacentList, int current, int[] visited) {
            if (visited[current] == 0) {
                visited[current] = 1; // Current node is in frontier
                if (adjacentList[current] != null) {
                    for (int next : adjacentList[current]) {
                        // If the next node is already in the frontier
                        if (visited[next] == 1 || containsCycleDFS(adjacentList, next, visited)) {
                            return true;
                        }
                    }
                }
                visited[current] = 2; // Current node is visited
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

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Topological sorting - BFS Approach: find a cycle in a graph.
    //      0. Build Graph using Adjacent List representation and In-Degree Array for each node;
    //      1. Enqueue all nodes with zero In-Degree value;
    //      2. BFS
    //          2.1 Add current visiting node into order list;
    //          2.2 Update In-Degrees of each neighbour of current visiting node;
    //          2.3 Enqueue the neighbour if the updated In-Degree becomes zero;
    //      3. Check the size of the order list;
    class Solution_BFS {
        public boolean canFinish(int numCourses, int[][] prerequisites) {
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
            List<Integer> order = new ArrayList<>();
            while (!zeroInDegreeQueue.isEmpty()) {
                int curr = zeroInDegreeQueue.poll();
                order.add(curr);
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
            return order.size() == numCourses;
        }
    }
}