/**
 *  @author Yunxiang He
 *  @date 06/27/2018
 */

package coding.temp;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*

There are a total of n courses you have to take, labeled from 0 to n-1.
Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]
Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?


Example 1:
    Input: 2, [[1,0]] 
    Output: true
    Explanation: There are a total of 2 courses to take. 
                 To take course 1 you should have finished course 0. 
                 So it is possible.

Example 2:
    Input: 2, [[1,0],[0,1]]
    Output: false
    Explanation: There are a total of 2 courses to take. 
                 To take course 1 you should have finished course 0, and to take course 0 you should also have finished course 1.
                 So it is impossible.
             
             
Note:
    The input prerequisites is a graph represented by a list of edges, not adjacency matrices.
    You may assume that there are no duplicate edges in the input prerequisites.

*/

public class _0207_Course_Schedule {

    public static void main(String[] args) {
        _0207_Course_Schedule test = new _0207_Course_Schedule();
        test.canFinish(3, new int[][] { { 0, 1 }, { 0, 2 }, { 1, 2 }, });
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Topological
    // the problem can be reduced to find the topological sort of the graph
    // the problem can be reduced to check whether there is a circle on the directed graph
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // build graph & indegrees
        List<Integer>[] graph = new List[numCourses];
        int[] indegrees = new int[numCourses];
        for (int[] prerequisite : prerequisites) {
            if (graph[prerequisite[0]] == null) {
                graph[prerequisite[0]] = new ArrayList<>();
            }
            graph[prerequisite[0]].add(prerequisite[1]);
            indegrees[prerequisite[1]]++;
        }

        // enque 0 indegree nodes
        Queue<Integer> oIndegrees = new LinkedList<>();
        for (int i = 0; i < indegrees.length; i++) {
            if (0 == indegrees[i]) {
                oIndegrees.offer(i);
            }
        }
        int orderSize = 0;
        // BFS
        // Delete 0 indegree vertices from the queue
        // Update the indegrees of its neighbour vertices
        //     Node: neis might be null
        while (!oIndegrees.isEmpty()) {
            orderSize++;
            List<Integer> neis = graph[oIndegrees.poll()];
            if (neis != null) {
                for (int nei : neis) {
                    indegrees[nei]--;
                    if (0 == indegrees[nei]) {
                        oIndegrees.offer(nei);
                    }
                }
            }
        }
        return orderSize == numCourses;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // DFS
    public boolean canFinish2(int numCourses, int[][] prerequisites) {
        List<Integer>[] graph = new List[numCourses];
        for (int[] prerequisite : prerequisites) {
            if (graph[prerequisite[0]] == null) {
                graph[prerequisite[0]] = new ArrayList<>();
            }
            graph[prerequisite[0]].add(prerequisite[1]);
        }
        // 0: not visited, 1: visiting, 2: visited and no circle
        int[] visited = new int[numCourses];
        for (int i = 0; i < numCourses; ++i) {
            if (visited[i] == 0) {
                if (DFS(graph, visited, i) == -1) {
                    return false;
                }
            }
        }
        return true;
    }

    private int DFS(List<Integer>[] graph, int[] visited, int i) {
        if (visited[i] == 1) {
            return -1;
        } else if (visited[i] == 0) {
            visited[i] = 1;
            List<Integer> neis = graph[i];
            if (neis != null) {
                for (int nei : neis) {
                    if (DFS(graph, visited, nei) == -1) {
                        return -1;
                    }
                }
            }
            visited[i] = 2;
        }
        return 0;
    }

}
