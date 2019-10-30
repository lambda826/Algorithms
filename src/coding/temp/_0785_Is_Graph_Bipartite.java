/**
 *  @author Yunxiang He
 *  @date 06/22/2018
 */

package coding.temp;

import java.util.LinkedList;
import java.util.Queue;

/*

Given an undirected graph, return true if and only if it is bipartite.

Recall that a graph is bipartite if we can split it's set of nodes into two independent subsets A and B such that every edge in the graph has one node in A and another node in B.

The graph is given in the following form: graph[i] is a list of indexes j for which the edge between nodes i and j exists.  
Each node is an integer between 0 and graph.length - 1.  
There are no self edges or parallel edges: graph[i] does not contain i, and it doesn't contain any element twice.


Example 1:
    Input: [[1,3], [0,2], [1,3], [0,2]]
    Output: true
    Explanation: 
    The graph looks like this:
    0----1
    |    |
    |    |
    3----2
    We can divide the vertices into two groups: {0, 2} and {1, 3}.

Example 2:
    Input: [[1,2,3], [0,2], [0,1,3], [0,2]]
    Output: false
    Explanation: 
    The graph looks like this:
    0----1
    | \  |
    |  \ |
    3----2
    We cannot find a way to divide the set of nodes into two independent subsets.
 

Note:
    graph will have length in range [1, 100].
    graph[i] will contain integers in range [0, graph.length - 1].
    graph[i] will not contain i or duplicate values.
    The graph is undirected: if any element j is in graph[i], then i will be in graph[j].

*/

public class _0785_Is_Graph_Bipartite {

    public static void main(String[] args) {
        System.out.println(new _0785_Is_Graph_Bipartite().isBipartite_BFS(new int[][] { { 1, 3 }, { 0, 2 }, { 1, 3 }, { 0, 2 } }));

    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public boolean isBipartite_BFS(int[][] graph) {
        int[] color = new int[graph.length];
        Queue<Integer> que = new LinkedList<>();
        for (int i = 0; i < graph.length; ++i) {
            if (color[i] == 0) {
                que.add(i);
                color[i] = -1;
                int temp;
                int k;
                while (!que.isEmpty()) {
                    temp = que.remove();
                    k = color[temp];
                    for (int nei : graph[temp]) {
                        if (color[nei] == 0) {
                            color[nei] = -k;
                            que.add(nei);
                        } else if (color[nei] == k) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public boolean isBipartite_DFS(int[][] graph) {
        int[] color = new int[graph.length];
        for (int i = 0; i < graph.length; ++i) {
            if (color[i] == 0 && !DFS(graph, color, i, 1)) {
                return false;
            }
        }
        return true;
    }

    private boolean DFS(int[][] graph, int[] color, int n, int c) {
        color[n] = c;
        for (int nei : graph[n]) {
            if (color[n] == color[nei]) {
                return false;
            }
            if (color[nei] == 0 && !DFS(graph, color, nei, -color[n])) {
                return false;
            }
        }
        return true;
    }
}
