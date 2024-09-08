package questions.temp;

/*

In this problem, a tree is an undirected graph that is connected and has no cycles.

You are given a graph that started as a tree with n nodes labeled from 1 to n, with one additional edge added.
The added edge has two different vertices chosen from 1 to n, and was not an edge that already existed.
The graph is represented as an array edges of length n where edges[i] = [ai, bi] indicates that there is an edge between nodes ai and bi in the graph.

Return an edge that can be removed so that the resulting graph is a tree of n nodes.
If there are multiple answers, return the answer that occurs last in the input.


Example 1:
    Input:
        edges = [[1,2], [1,3], [2,3]]
    Output:
        [2,3]

Example 2:
    Input:
        edges = [[1,2],[2,3],[3,4],[1,4],[1,5]]
    Output:
        [1,4]


Constraints:
    n == edges.length
    3 <= n <= 1000
    edges[i].length == 2
    1 <= ai < bi <= edges.length
    ai != bi
    There are no repeated edges.
    The given graph is connected.

*/
public class _0684_Redundant_Connection {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    class Solution_UF {
        public int[] findRedundantConnection(int[][] edges) {
            int[] parent = new int[edges.length + 1];
            int[] height = new int[edges.length + 1];
            for (int i = 0; i < parent.length; ++i) {
                parent[i] = i;
            }
            for (int[] edge : edges) {
                int ii = find(parent, edge[0]);
                int jj = find(parent, edge[1]);
                if (ii == jj) {
                    return edge;
                } else {
                    if (height[ii] < height[jj]) {
                        parent[ii] = jj;
                    } else {
                        parent[jj] = ii;
                        if (height[ii] == height[jj]) {
                            ++height[ii];
                        }
                    }
                }
            }
            return null;
        }

        private int find(int[] parent, int i) {
            return parent[i] == i ? i : (parent[i] = find(parent, parent[i]));
        }

    }

}
