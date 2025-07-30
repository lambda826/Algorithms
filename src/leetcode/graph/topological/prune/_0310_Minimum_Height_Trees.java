package leetcode.graph.topological.prune;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/*

A tree is an undirected graph in which any two vertices are connected by exactly one path.
In other words, any connected graph without simple cycles is a tree.

Given a tree of n nodes labelled from 0 to n - 1,
and an array of n - 1 edges where edges[i] = [ai, bi] indicates that there is an undirected edge between the two nodes ai and bi in the tree,
you can choose any node of the tree as the root. When you select a node x as the root, the result tree has height h.
Among all possible rooted trees, those with minimum height (i.e. min(h)) are called minimum height trees (MHTs).

Return a list of all MHTs' root labels. You can return the answer in any order.

The height of a rooted tree is the number of edges on the longest downward path between the root and a leaf.


Example 1:
    Input:
        n = 4,
        edges = [[1,0],[1,2],[1,3]]
    Output:
        [1]
    Explanation:
        As shown, the height of the tree is 1 when the root is the node with label 1 which is the only MHT.

Example 2:
    Input:
        n = 6,
        edges = [[3,0],[3,1],[3,2],[3,4],[5,4]]
    Output:
        [3,4]

Example 3:
    Input:
        n = 1,
        edges = []
    Output:
        [0]

Example 4:
    Input:
        n = 2,
        edges = [[0,1]]
    Output:
        [0,1]


Constraints:
    1 <= n <= 2 * 10^4
    edges.length == n - 1
    0 <= ai, bi < n
    ai != bi
    All the pairs (ai, bi) are distinct.
    The given input is guaranteed to be a tree and there will be no repeated edges.

*/
public class _0310_Minimum_Height_Trees {

    class Solution {
        public List<Integer> findMinHeightTrees(int n, int[][] edges) {
            if (n == 1) {
                return List.of(0);
            }
            List<Integer>[] graph = new List[n];
            int[] degree = new int[n];
            for (int i = 0; i < graph.length; ++i) {
                graph[i] = new ArrayList<>();
            }
            for (int[] edge : edges) {
                graph[edge[0]].add(edge[1]);
                graph[edge[1]].add(edge[0]);
                degree[edge[0]]++;
                degree[edge[1]]++;
            }
            Queue<Integer> queue = new ArrayDeque<>();
            for (int i = 0; i < degree.length; ++i) {
                if (degree[i] == 1) {
                    queue.offer(i);
                }
            }

            int left = n;
            while (left > 2) {
                int size = queue.size();
                left -= size;
                while (size-- > 0) {
                    int curr = queue.poll();
                    degree[curr]--;
                    for (int next : graph[curr]) {
                        if (--degree[next] == 1) {
                            queue.offer(next);
                        }
                    }
                }
            }
            return new ArrayList<>(queue);
        }
    }
}