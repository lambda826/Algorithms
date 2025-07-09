package questions._11_graph;

import java.util.ArrayList;
import java.util.List;

/*
Given an undirected tree consisting of n vertices numbered from 0 to n-1, which has some apples in their vertices. You spend 1 second to walk over one edge of the tree.
Return the minimum time in seconds you have to spend to collect all apples in the tree, starting at vertex 0 and coming back to this vertex.

The edges of the undirected tree are given in the array edges, where edges[i] = [ai, bi] means that exists an edge connecting the vertices ai and bi.
Additionally, there is a boolean array hasApple, where hasApple[i] = true means that vertex i has an apple; otherwise, it does not have any apple.


Example 1:
    Input: n = 7, edges = [[0,1],[0,2],[1,4],[1,5],[2,3],[2,6]], hasApple = [false,false,true,false,true,true,false]
    Output: 8
    Explanation: The figure above represents the given tree where red vertices have an apple. One optimal path to collect all apples is shown by the green arrows.

Example 2:
    Input: n = 7, edges = [[0,1],[0,2],[1,4],[1,5],[2,3],[2,6]], hasApple = [false,false,true,false,false,true,false]
    Output: 6
    Explanation: The figure above represents the given tree where red vertices have an apple. One optimal path to collect all apples is shown by the green arrows.

Example 3:
    Input: n = 7, edges = [[0,1],[0,2],[1,4],[1,5],[2,3],[2,6]], hasApple = [false,false,false,false,false,false,false]
    Output: 0


Constraints:
    1 <= n <= 10^5
    edges.length == n - 1
    edges[i].length == 2
    0 <= ai < bi <= n - 1
    hasApple.length == n
*/
public class _1443_Minimum_Time_to_Collect_All_Apples_in_a_Tree {

    class Solution {

        public int minTime(int n, int[][] edges, List<Boolean> hasApple) {
            int time = dfs(createGraph(edges), hasApple, new int[n], 0);
            return time == 0 ? 0 : time - 2;
        }

        private List<Integer>[] createGraph(int[][] edges) {
            List<Integer>[] graph = new List[edges.length + 1];
            for (int[] edge : edges) {
                if (graph[edge[0]] == null) {
                    graph[edge[0]] = new ArrayList<>();
                }
                if (graph[edge[1]] == null) {
                    graph[edge[1]] = new ArrayList<>();
                }
                graph[edge[0]].add(edge[1]);
                graph[edge[1]].add(edge[0]);
            }
            return graph;
        }

        private int dfs(List<Integer>[] graph, List<Boolean> hasApple, int[] visited, int curr) {
            int time = 0;
            if (visited[curr] == 0) {
                visited[curr] = 1;
                List<Integer> children = graph[curr];
                if (children != null) {
                    for (int child : children) {
                        if (visited[child] == 0) {
                            time += dfs(graph, hasApple, visited, child);
                        }
                    }
                }
                if (time > 0 || hasApple.get(curr)) {
                    time += 2;
                }
            }
            return time;
        }
    }
}
