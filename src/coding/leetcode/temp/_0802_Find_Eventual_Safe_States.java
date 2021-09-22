/**
 *  @author Yunxiang He
 *  @date 06/21/2018
 */

package coding.leetcode.temp;

import java.util.ArrayList;
import java.util.List;

/*

In a directed graph, we start at some node and every turn, walk along a directed edge of the graph.
If we reach a node that is terminal (that is, it has no outgoing directed edges), we stop.

Now, say our starting node is eventually safe if and only if we must eventually walk to a terminal node.  
More specifically, there exists a natural number K so that for any choice of where to walk, we must have stopped at a terminal node in less than K steps.

Which nodes are eventually safe?  
Return them as an array in sorted order.

The directed graph has N nodes with labels 0, 1, ..., N-1, where N is the length of graph.  
The graph is given in the following form: graph[i] is a list of labels j such that (i, j) is a directed edge of the graph.


Example:
    Input: graph = [[1,2],[2,3],[5],[0],[5],[],[]]
    Output: [2,4,5,6]
    Here is a diagram of the above graph.


Note:
    graph will have length at most 10000.
    The number of edges in the graph will not exceed 32000.
    Each graph[i] will be a sorted list of different integers, chosen within the range [0, graph.length - 1].

*/

public class _0802_Find_Eventual_Safe_States {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Find circle
    // Any node that is connected to the circle should be excluded
    public List<Integer> eventualSafeNodes(int[][] graph) {
        List<Integer> res = new ArrayList<>();
        // 0: not visited, 1: hasCircle, 2: noCircle
        int[] visited = new int[graph.length];
        for (int i = 0; i < graph.length; ++i) {
            if (!DFS(graph, visited, i)) {
                res.add(i);
            }
        }
        return res;
    }

    private boolean DFS(int[][] graph, int[] visited, int node) {
        // if visited[node] == 1, circle, return true;
        // if visited[node] == 2, noCircle, return false;
        if (visited[node] != 0) {
            return visited[node] == 1;
        } else {
            visited[node] = 1;
            for (int nei : graph[node]) {
                if (DFS(graph, visited, nei)) {
                    return true;
                }
            }
            visited[node] = 2;
            return false;
        }
    }
}
