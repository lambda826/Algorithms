package questions._11_graph.bipartition;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/*

We want to split a group of n people (labeled from 1 to n) into two groups of any size.
Each person may dislike some other people, and they should not go into the same group.

Given the integer n and the array dislikes where dislikes[i] = [ai, bi] indicates that the person labeled ai does not like the person labeled bi,
return true if it is possible to split everyone into two groups in this way.


Example 1:
    Input:
        n = 4,
        dislikes = [[1,2],[1,3],[2,4]]
    Output:
        true
    Explanation:
        group1 [1,4] and group2 [2,3].

Example 2:
    Input:
        n = 3,
        dislikes = [[1,2],[1,3],[2,3]]
    Output:
        false

Example 3:
    Input:
        n = 5,
        dislikes = [[1,2],[2,3],[3,4],[4,5],[1,5]]
    Output:
        false


Constraints:
    1 <= n <= 2000
    0 <= dislikes.length <= 10^4
    dislikes[i].length == 2
    1 <= dislikes[i][j] <= n
    ai < bi
    All the pairs of dislikes are unique.
*/
public class _0886_Possible_Bipartition {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    class Solution_BFS {

        public boolean possibleBipartition(int n, int[][] dislikes) {
            List<Integer>[] graph = buildGraph(n, dislikes);
            int[] color = new int[n + 1];
            Deque<Integer> deque = new ArrayDeque<>();
            for (int i = 0; i < graph.length; ++i) {
                if (color[i] == 0) {
                    deque.offer(i);
                    color[i] = 1;
                    while (!deque.isEmpty()) {
                        int k = deque.poll();
                        if (graph[k] != null) {
                            for (int nei : graph[k]) {
                                if (color[nei] == 0) {
                                    color[nei] = -color[k];
                                    deque.offer(nei);
                                } else if (color[nei] == color[k]) {
                                    return false;
                                }
                            }
                        }
                    }
                }
            }
            return true;
        }

        private List<Integer>[] buildGraph(int n, int[][] dislikes) {
            List<Integer>[] graph = new List[n + 1];
            for (int[] dislike : dislikes) {
                if (graph[dislike[0]] == null) {
                    graph[dislike[0]] = new ArrayList<>();
                }
                if (graph[dislike[1]] == null) {
                    graph[dislike[1]] = new ArrayList<>();
                }
                graph[dislike[0]].add(dislike[1]);
                graph[dislike[1]].add(dislike[0]);
            }
            return graph;
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    class Solution_DFS {

        public boolean possibleBipartition(int n, int[][] dislikes) {
            List<Integer>[] graph = buildGraph(n, dislikes);
            int[] colors = new int[n + 1];
            for (int i = 0; i < graph.length; ++i) {
                if (colors[i] == 0 && !dfs(graph, colors, i, 1)) {
                    return false;
                }
            }
            return true;
        }

        private boolean dfs(List<Integer>[] graph, int[] colors, int i, int color) {
            colors[i] = color;
            if (graph[i] != null) {
                for (int nei : graph[i]) {
                    if (colors[nei] == color
                        || colors[nei] == 0 && !dfs(graph, colors, nei, -color)) {
                        return false;
                    }
                }
            }
            return true;
        }

        private List<Integer>[] buildGraph(int n, int[][] dislikes) {
            List<Integer>[] graph = new List[n + 1];
            for (int[] dislike : dislikes) {
                if (graph[dislike[0]] == null) {
                    graph[dislike[0]] = new ArrayList<>();
                }
                if (graph[dislike[1]] == null) {
                    graph[dislike[1]] = new ArrayList<>();
                }
                graph[dislike[0]].add(dislike[1]);
                graph[dislike[1]].add(dislike[0]);
            }
            return graph;
        }
    }
}
