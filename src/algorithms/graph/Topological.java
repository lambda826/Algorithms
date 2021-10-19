package algorithms.graph;

import common.GraphNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class Topological {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Given a DAG
    // DFS:
    //      Iterate every node of the Graph;
    //      For each node, if it's not visited, recursively DFS visit each neighbour;
    //      After the visiting each neighbour, add current node to head of the order list.
    public static List<GraphNode> topological_DFS(List<GraphNode> graph) {
        LinkedList<GraphNode> order = new LinkedList<>();
        Set<GraphNode> visited = new HashSet<>();
        for (GraphNode graphNode : graph) {
            DFS(graphNode, visited, order);
        }
        return order;
    }

    private static void DFS(GraphNode node, Set<GraphNode> visited, LinkedList<GraphNode> order) {
        if (!visited.add(node)) {
            for (GraphNode graphNode : node.neighbors) {
                DFS(graphNode, visited, order);
            }
            order.add(node);
        }
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Input is directed edge
    public List<Integer> topological_BFS(int[][] edges) {
        List<Integer> ordering = new ArrayList<>();
        // Build graph & inDegreeMap
        Map<Integer, List<Integer>> graph = new HashMap<>();
        Map<Integer, Integer> inDegreeMap = new HashMap<>();
        for (int[] edge : edges) {
            graph.putIfAbsent(edge[0], new ArrayList<>());
            graph.get(edge[0]).add(edge[1]);
            inDegreeMap.put(edge[1], inDegreeMap.getOrDefault(edge[1], 0) + 1);
        }

        // Enqueue zero inDegree vertices
        Queue<Integer> zeroInDegree = new LinkedList<>();
        for (int key : graph.keySet()) {
            if (!inDegreeMap.containsKey(key)) {
                zeroInDegree.add(key);
            }
        }

        // BFS
        // Delete 0 inDegree vertex from the queue, update inDegreeMap
        while (!zeroInDegree.isEmpty()) {
            int vertex = zeroInDegree.remove();
            ordering.add(vertex);
            if (graph.get(vertex) != null) {
                for (int neighbor : graph.get(vertex)) {
                    inDegreeMap.put(neighbor, inDegreeMap.get(neighbor) - 1);
                    if (inDegreeMap.get(neighbor) == 0) {
                        zeroInDegree.add(neighbor);
                    }
                }
            }
        }
        System.out.println(ordering);
        return ordering;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Input is a graph
    public static List<GraphNode> topological_BFS2(List<GraphNode> graph) {
        List<GraphNode> ordering = new LinkedList<>();

        // Build inDegreeMap
        Map<GraphNode, Integer> inDegreeMap = new HashMap<>();
        for (GraphNode vertex : graph) {
            for (GraphNode neighbor : vertex.neighbors) {
                inDegreeMap.put(neighbor, inDegreeMap.getOrDefault(neighbor, 0) + 1);
            }
        }

        // Calculate 0-inDegreeMap
        Queue<GraphNode> zeroInDegree = new LinkedList<>();
        for (GraphNode vertex : graph) {
            if (!inDegreeMap.containsKey(vertex)) {
                zeroInDegree.add(vertex);
            }
        }

        // BFS
        while (!zeroInDegree.isEmpty()) {
            GraphNode vertex = zeroInDegree.remove();
            ordering.add(vertex);
            if (vertex.neighbors != null) {
                for (GraphNode neighbor : vertex.neighbors) {
                    inDegreeMap.put(neighbor, inDegreeMap.get(neighbor) - 1);
                    if (inDegreeMap.get(neighbor) == 0) {
                        zeroInDegree.add(neighbor);
                    }
                }
            }
        }
        System.out.println(ordering);
        return ordering;
    }
}
