package coding.leetcode.temp;

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

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Topological variation - BFS Approach.
    //      0. Build Graph and inDegree Array;
    //      1. Enqueue terminal nodes who have only one neighbor (exact 1 inDegree);
    //      2. BFS
    //          2.1 Level traverse to remove the terminal nodes from the queue;
    //          2.2 Update inDegree neighbour of current node and enqueue if it becomes a terminal node;
    //          2.3 Break while loop once we reach the point when the queue has less than 3 nodes;
    //
    // Takeaways:
    //      1. If the graph is an undirected graph, then node i is a terminal node when inDegree[i] == 1.
    //      2. Arrays.fill(arr, new ArrayList<>()); will create only one copy of the ArrayList for all entries.
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        // Edge case
        if (n == 1) {
            return new ArrayList<>() {{
                add(0);
            }};
        }

        List<Integer>[] adjacentList = new List[n];
        for (int i = 0; i < n; ++i) {
            adjacentList[i] = new ArrayList<>();
        }
        // Build Graph using Adjacent List given edges
        // Build InDegree array given edges
        int[] inDegrees = new int[n];
        for (int[] edge : edges) {
            adjacentList[edge[0]].add(edge[1]);
            adjacentList[edge[1]].add(edge[0]);
            inDegrees[edge[0]]++;
            inDegrees[edge[1]]++;
        }

        Queue<Integer> queue = new ArrayDeque<>();
        // Enqueue terminal nodes, terminal nodes have exact 1 for inDegree
        for (int i = 0; i < n; ++i) {
            if (inDegrees[i] == 1) {
                queue.offer(i);
            }
        }

        int remain = n; // Use to track the number of nodes left
        while (remain > 2) {
            int size = queue.size(); // Level traversal to dequeue all terminal nodes
            while (size-- > 0) {
                --remain;
                int curr = queue.poll();
                for (int neighbour : adjacentList[curr]) {
                    // Update inDegree neighbour of current node and enqueue if it becomes a terminal node
                    if (--inDegrees[neighbour] == 1) {
                        queue.offer(neighbour);
                    }
                }
            }
        }
        return new ArrayList<>(queue);
    }
}