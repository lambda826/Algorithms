/**
 *  @author Yunxiang He
 *  @date 06/27/2018
 */

package questions.temp;

import java.util.ArrayList;
import java.util.List;

/*

Given a directed, acyclic graph of N nodes.  
Find all possible paths from node 0 to node N-1, and return them in any order.

The graph is given as follows:  the nodes are 0, 1, ..., graph.length - 1.  
graph[i] is a list of all nodes j for which the edge (i, j) exists.


Example:
    Input: 
        [[1,2], [3], [3], []] 
    Output: 
        [[0,1,3],[0,2,3]] 
    Explanation: 
        The graph looks like this:
        0--->1
        |    |
        v    v
        2--->3
        There are two paths: 0 -> 1 -> 3 and 0 -> 2 -> 3.


Note:
    The number of nodes in the graph will be in the range [2, 15].
    You can print different paths in any order, but you should keep the order of nodes inside one path.

*/

public class _0797_All_Paths_From_Source_to_Target {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public List<List<Integer>> allPathsSourceTarget_DFS(int[][] graph) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> tempPath = new ArrayList<>();
        tempPath.add(0);
        dfs(graph, res, tempPath, 0, graph.length - 1);
        return res;
    }

    private void dfs(int[][] graph, List<List<Integer>> res, List<Integer> temp, int node, int target) {
        if (node == target) {
            res.add(new ArrayList<>(temp));
        } else {
            for (int nei : graph[node]) {
                temp.add(nei);
                dfs(graph, res, temp, nei, target);
                temp.remove(temp.size() - 1);
            }
        }
    }
}
