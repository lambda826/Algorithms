/**
 *  @author Yunxiang He
 *  @date 01/10/2019
 */

package algorithms.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BuildGraph {

    public static void main(String[] args) {
        int[][] edges = { { 1, 2 }, { 2, 0 }, { 1, 3 }, { 3, 0 }, { 1, 0 } };
        String[][] edgesStr = { { "1", "2" }, { "2", "0" }, { "1", "3" }, { "3", "0" }, { "1", "0" } };
        Integer[] weights = { 1, 5, 3, 4, 2 };
        buildGraph_undirected_unweighted(4, edges);
        buildGraph_undirected_weighted(4, edges, weights);
        buildGraph_undirected_unweighted(edgesStr);
        buildGraph_undirected_unweighted(edgesStr, weights);

    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Undirected
    // Unweighted
    // The vertices are natural integers from 0 to (N - 1)
    public static List<Integer>[] buildGraph_undirected_unweighted(int N, int[][] edges) {
        List<Integer>[] graph = new List[N];
        for (int i = 0; i < graph.length; ++i) {
            graph[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }
        return graph;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Directed
    // Weighted
    // The vertices are natural integers from 0 to (N - 1)
    public static <W> Map<Integer, W>[] buildGraph_undirected_weighted(int N, int[][] edges, W[] weights) {
        Map<Integer, W>[] graph = new Map[N];
        for (int i = 0; i < graph.length; ++i) {
            graph[i] = new HashMap<Integer, W>();
        }
        for (int i = 0; i < edges.length; ++i) {
            graph[edges[i][0]].put(edges[i][1], weights[i]);
        }
        return graph;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Undirected
    // Unweighted
    // The verices are strings or of other types
    public static <V> Map<V, List<V>> buildGraph_undirected_unweighted(V[] edges[]) {
        Map<V, List<V>> graph = new HashMap<>();
        for (V[] edge : edges) {
            graph.putIfAbsent(edge[0], new ArrayList<>());
            graph.get(edge[0]).add(edge[1]);
            graph.putIfAbsent(edge[1], new ArrayList<>());
            graph.get(edge[1]).add(edge[0]);
        }
        return graph;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Directed
    // Weighted
    // The verices are strings or of other types
    public static <V, W> Map<V, Map<V, W>> buildGraph_undirected_unweighted(V[] edges[], W[] weights) {
        Map<V, Map<V, W>> graph = new HashMap<>();
        for (int i = 0; i < edges.length; ++i) {
            graph.putIfAbsent(edges[i][0], new HashMap<>());
            graph.get(edges[i][0]).put(edges[i][1], weights[i]);
        }
        return graph;
    }
}
