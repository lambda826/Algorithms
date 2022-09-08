package questions.temp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
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
    class Solution_UF {
        public int removeStones(int[][] stones) {
            Map<Integer, Integer> map = new HashMap();
            Map<Integer, Integer> height = new HashMap();
            int n = stones.length;
            int[] count = { 0 };
            Arrays.sort(stones, Comparator.comparingInt(a -> a[0]));
            for (int i = 0; i < n - 1; ++i) {
                if (stones[i][0] == stones[i + 1][0]) {
                    union(map, height, stones[i][0] * n + stones[i][1], stones[i + 1][0] * n + stones[i + 1][1], count);
                }
            }

            Arrays.sort(stones, Comparator.comparingInt(a -> a[1]));
            for (int i = 0; i < n - 1; ++i) {
                if (stones[i][1] == stones[i + 1][1]) {
                    union(map, height, stones[i][0] * n + stones[i][1], stones[i + 1][0] * n + stones[i + 1][1], count);
                }
            }

            return count[0];
        }

        private int find(Map<Integer, Integer> map, int i) {
            if (!map.containsKey(i)) {
                map.put(i, i);
            }
            if (map.get(i) == i) {
                return i;
            }
            return map.compute(i, (k, v) -> find(map, map.get(k)));
        }

        private void union(Map<Integer, Integer> map, Map<Integer, Integer> height, int i, int j, int[] count) {
            int ii = find(map, i);
            int jj = find(map, j);
            if (ii != jj) {
                ++count[0];
                if (height.getOrDefault(ii, 0) < height.getOrDefault(jj, 0)) {
                    map.put(ii, jj);
                } else {
                    map.put(jj, ii);
                    if (height.getOrDefault(ii, 0) == height.getOrDefault(jj, 0)) {
                        height.put(ii, height.getOrDefault(ii, 0) + 1);
                    }
                }

            }
        }
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
