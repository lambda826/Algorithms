package algorithms.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BuildGraph {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Undirected
    // Unweighted
    // The vertices are natural integers from 0 to (N - 1)
    public static List<Integer>[] buildGraph_Undirected_Unweighted(int N, int[][] edges) {
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

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Undirected
    // Unweighted
    // The vertices are strings or of other types
    public static <V> Map<V, List<V>> buildGraph_Undirected_Unweighted(V[][] edges) {
        Map<V, List<V>> graph = new HashMap<>();
        for (V[] edge : edges) {
            graph.putIfAbsent(edge[0], new ArrayList<>());
            graph.get(edge[0]).add(edge[1]);
            graph.putIfAbsent(edge[1], new ArrayList<>());
            graph.get(edge[1]).add(edge[0]);
        }
        return graph;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Directed
    // Weighted
    // The vertices are natural integers from 0 to (N - 1)
    public static <W> Map<Integer, W>[] buildGraph_Undirected_Weighted(int N, int[][] edges, W[] weights) {
        Map<Integer, W>[] graph = new Map[N];
        for (int i = 0; i < graph.length; ++i) {
            graph[i] = new HashMap<>();
        }
        for (int i = 0; i < edges.length; ++i) {
            graph[edges[i][0]].put(edges[i][1], weights[i]);
        }
        return graph;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Directed
    // Weighted
    // The vertices are strings or of other types
    public static <V, W> Map<V, Map<V, W>> buildGraph_Undirected_Unweighted(V[][] edges, W[] weights) {
        Map<V, Map<V, W>> graph = new HashMap<>();
        for (int i = 0; i < edges.length; ++i) {
            graph.putIfAbsent(edges[i][0], new HashMap<>());
            graph.get(edges[i][0]).put(edges[i][1], weights[i]);
        }
        return graph;
    }
}