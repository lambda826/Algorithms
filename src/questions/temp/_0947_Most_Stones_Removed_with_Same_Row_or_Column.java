/**
 *  @author Yunxiang He
 *  @date 03/13/2019
 */

package questions.temp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*

On a 2D plane, we place stones at some integer coordinate points.  
Each coordinate point may have at most one stone.
Now, a move consists of removing a stone that shares a column or row with another stone on the grid.

What is the largest possible number of moves we can make?
 

Example 1:
    Input: stones = [[0,0],[0,1],[1,0],[1,2],[2,1],[2,2]]
    Output: 5

Example 2:
    Input: stones = [[0,0],[0,2],[1,1],[2,0],[2,2]]
    Output: 3

Example 3:
    Input: stones = [[0,0]]
    Output: 0
 

Note:
    1 <= stones.length <= 1000
    0 <= stones[i][j] < 10^4

*/

public class _0947_Most_Stones_Removed_with_Same_Row_or_Column {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // this problem is reduced to find the # of edges of the graph
    // it is a connectivity problem of graph
    int[] root;
    int[] size;

    public int removeStones_UF(int[][] stones) {
        int n = stones.length;
        root = new int[n];
        size = new int[n];
        // init
        for (int i = 0; i < n; ++i) {
            root[i] = i;
            size[i] = 1;
        }
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                // union
                if (stones[i][0] == stones[j][0] || stones[i][1] == stones[j][1]) {
                    int r1 = find(i);
                    int r2 = find(j);
                    if (r1 != r2) {
                        if (size[r1] < size[r2]) {
                            root[r1] = r2;
                            size[r2] += size[r1];
                        } else {
                            root[r2] = r1;
                            size[r1] += size[r2];
                        }
                    }
                }
            }
        }
        int sum = 0;
        for (int i = 0; i < n; ++i) {
            if (root[i] == i) {
                sum += size[i] - 1;
            }
        }
        return sum;
    }

    private int find(int idx) {
        if (root[idx] == idx) {
            return idx;
        }
        return root[idx] = find(root[idx]);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private int sum = 0;

    public int removeStones_DFS(int[][] stones) {
        int n = stones.length;
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                if (stones[i][0] == stones[j][0] || stones[i][1] == stones[j][1]) {
                    graph.putIfAbsent(i, new ArrayList<>());
                    graph.putIfAbsent(j, new ArrayList<>());
                    graph.get(i).add(j);
                    graph.get(j).add(i);
                }
            }
        }
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; ++i) {
            if (!visited[i]) {
                visited[i] = true;
                dfs(graph, visited, i);
            }
        }
        return sum;
    }

    private void dfs(Map<Integer, List<Integer>> graph, boolean[] visited, int i) {
        // if node i has neighbors, then add up to sum
        if (graph.get(i) != null) {
            for (int nei : graph.get(i)) {
                if (!visited[nei]) {
                    ++sum;
                    visited[nei] = true;
                    dfs(graph, visited, nei);
                }

            }
        }
    }
}
