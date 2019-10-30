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

Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes), write a function to find the number of connected components in an undirected graph.


Example 1:
    Input: n = 5 and edges = [[0, 1], [1, 2], [3, 4]]
    
         0          3
         |          |
         1 --- 2    4 
    Output: 2

Example 2:
    Input: n = 5 and edges = [[0, 1], [1, 2], [2, 3], [3, 4]]
    
         0           4
         |           |
         1 --- 2 --- 3
    
    Output:  1


Note:
    You can assume that no duplicate edges will appear in edges. 
    Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges.

*/

public class _0323_Number_of_Connected_Components_in_an_Undirected_Graph {

    public static void main(String[] args) {
        int[][] edges = { { 1, 0 }, { 2, 6 }, { 1, 7 }, { 5, 1 }, { 6, 4 }, { 7, 0 }, { 0, 5 }, { 5, 1 }, { 6, 4 } };
        int n = 8;
        _0323_Number_of_Connected_Components_in_an_Undirected_Graph test = new _0323_Number_of_Connected_Components_in_an_Undirected_Graph();
        System.out.println(test.countComponents_BFS(n, edges));
    }

    // Build unweighted undirected graph
    // Every node should record its neighbors
    private List<Integer>[] buildGraph(int n, int[][] edges) {
        List<Integer>[] graph = new List[n];
        for (int[] edge : edges) {
            if (graph[edge[0]] == null) {
                graph[edge[0]] = new ArrayList<>();
            }
            graph[edge[0]].add(edge[1]);
            if (graph[edge[1]] == null) {
                graph[edge[1]] = new ArrayList<>();
            }
            graph[edge[1]].add(edge[0]);
        }
        return graph;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int countComponents_DFS(int n, int[][] edges) {
        List<Integer>[] graph = buildGraph(n, edges);
        int count = 0;
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                DFS(graph, visited, i);
                count++;
            }
        }
        return count;
    }

    private void DFS(List<Integer>[] graph, boolean[] visited, int i) {
        visited[i] = true;
        if (graph[i] != null) {
            for (int nei : graph[i]) {
                if (!visited[nei]) {
                    DFS(graph, visited, nei);
                }
            }
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // There might be single point in the graph
    public int countComponents_BFS(int n, int[][] edges) {
        List<Integer>[] graph = buildGraph(n, edges);
        int count = 0;
        boolean[] visited = new boolean[n];
        Queue<Integer> que = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                count++;
                que.add(i);
                while (!que.isEmpty()) {
                    int node = que.remove();
                    visited[node] = true;
                    if (graph[node] != null) {
                        for (int nei : graph[node]) {
                            if (!visited[nei]) {
                                que.add(nei);
                            }
                        }
                    }
                }
            }
        }
        return count;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    int[] root;
    int[] sz;
    int res;

    public int countComponents_UF(int n, int[][] edges) {
        res = n;
        root = new int[n];
        sz = new int[n];
        for (int i = 0; i < n; i++) {
            root[i] = i;
            sz[i] = 1;
        }
        for (int[] edge : edges) {
            union(edge[0], edge[1]);
        }
        return res;
    }

    private void union(int index1, int index2) {
        int root1 = find(index1);
        int root2 = find(index2);
        if (root1 != root2) {
            res--;
            if (sz[root1] > sz[root2]) {
                sz[root1] += sz[root2];
                root[root2] = root1;
            } else {
                sz[root2] += sz[root1];
                root[root1] = root2;
            }
        }
    }

    private int find(int index) {
        if (root[index] == index) {
            return index;
        }
        return root[index] = find(root[index]);
    }

}
