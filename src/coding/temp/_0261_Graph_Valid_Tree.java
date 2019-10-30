/**
 *  @author Yunxiang He
 *  @date 01/29/2018
 */

package coding.temp;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*

Given n nodes labeled from 0 to n - 1 and a graph of undirected edges (each edge is a pair of nodes), write a function to check whether these edges make up a valid tree.


Example 1:
    Input: n = 5, and edges = [[0,1], [0,2], [0,3], [1,4]]
    Output: true

Example 2:
    Input: n = 5, and edges = [[0,1], [1,2], [2,3], [1,3], [1,4]]
    Output: false


Note: 
    you can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the same as [1, 0]

 */

public class _0261_Graph_Valid_Tree {

    private List<Integer>[] graph;

    private void buildGraph(int[][] edges) {
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
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // BFS to check where there is any circle in a undirected graph   
    // All visited nodes compose a valid tree
    // All nodes being visited are attached to the tree
    // Thus if there is any edge between two nodes are being visited (when examine a node, its neighbor is visited[i] == 1)
    //    There must be a circle
    // If all nodes are visited (visited[i] != 0), the graph is a valid tree
    public boolean validTree_BFS(int n, int[][] edges) {
        graph = new ArrayList[n];
        buildGraph(edges);
        if (graph[0] == null) {
            return n == 1;
        }
        Queue<Integer> que = new LinkedList<>();
        // 0: not visited, 1: being visiting, 2: visited
        int[] visited = new int[n];
        que.add(0);
        visited[0] = 1;
        while (!que.isEmpty()) {
            int v = que.remove();
            visited[v] = 2;
            for (int nei : graph[v]) {
                if (visited[nei] != 2) {
                    if (visited[nei] == 1) {
                        return false;
                    }
                    que.add(nei);
                    visited[nei] = 1;
                }
            }
        }
        for (int v : visited) {
            if (v == 0) {
                return false;
            }
        }
        return true;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public boolean validTree_DFS(int n, int[][] edges) {
        graph = new ArrayList[n];
        buildGraph(edges);
        if (graph[0] == null) {
            return n == 1;
        }
        // 0: not visited, 1: being visited, 2: visited
        int[] visited = new int[n];
        if (!DFS(graph, visited, 0)) {
            return false;
        }
        for (int v : visited) {
            if (v == 0) {
                return false;
            }
        }
        return true;
    }

    private boolean DFS(List<Integer>[] graph, int[] visited, int curr) {
        if (visited[curr] == 2) {
            return false;
        } else if (visited[curr] == 0) {
            visited[curr] = 1;
            for (int nei : graph[curr]) {
                if (!DFS(graph, visited, nei)) {
                    return false;
                }
            }
            visited[curr] = 2;
        }
        return true;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Rooted at the same node
    public boolean validTree_UF(int n, int[][] edges) {
        if (n != edges.length + 1) {
            return false;
        }
        // Build points
        int[] root = new int[n];
        for (int i = 0; i < n; ++i) {
            root[i] = i;
        }
        for (int[] edge : edges) {
            int root1 = find(root, edge[0]);
            int root2 = find(root, edge[1]);
            if (root1 == root2) {
                return false;
            } else {
                root[root1] = root2;
            }
        }
        return true;
    }

    private int find(int[] root, int index) {
        if (root[index] == index) {
            return index;
        }
        return root[index] = find(root, root[index]);
    }

    public static void main(String[] args) {
        System.out.println(new _0261_Graph_Valid_Tree().validTree_BFS(2, new int[][] { { 1, 0 } }));
    }
}
