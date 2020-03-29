/**
 *  @author Yunxiang He
 *  @date 10/26/2018
 */

package algorithms.graph;

import common.GraphNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class Topological {

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Input is directed edges
    public List<Integer> topological(int[][] esges) {
        List<Integer> ordering = new ArrayList<>();
        // Build graph, inDegreeMap
        Map<Integer, List<Integer>> graph = new HashMap<>();
        Map<Integer, Integer> inDegreeMap = new HashMap<>();
        for (int[] esge : esges) {
            graph.putIfAbsent(esge[0], new ArrayList<>());
            graph.get(esge[0]).add(esge[1]);
            inDegreeMap.put(esge[1], inDegreeMap.getOrDefault(esge[1], 0) + 1);
        }

        // Enqueue 0 indegree vertices
        Queue<Integer> oIndegree = new LinkedList<>();
        for (int key : graph.keySet()) {
            if (!inDegreeMap.containsKey(key)) {
                oIndegree.add(key);
            }
        }

        // BFS
        // Delete 0 indegree vertex from the queue, update `indegreeMap
        while (!oIndegree.isEmpty()) {
            int vertex = oIndegree.remove();
            ordering.add(vertex);
            if (graph.get(vertex) != null) {
                for (int neighbor : graph.get(vertex)) {
                    inDegreeMap.put(neighbor, inDegreeMap.get(neighbor) - 1);
                    if (inDegreeMap.get(neighbor) == 0) {
                        oIndegree.add(neighbor);
                    }
                }
            }
        }
        System.out.println(ordering);
        return ordering;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Input is a graph
    public static List<GraphNode> topologicalOrdering2(List<GraphNode> graph) {
        List<GraphNode> ordering = new LinkedList<>();

        // Build inDegreeMap
        Map<GraphNode, Integer> inDegreeMap = new HashMap<>();
        for (GraphNode vertex : graph) {
            for (GraphNode neighbor : vertex.neighbors) {
                inDegreeMap.put(neighbor, inDegreeMap.getOrDefault(neighbor, 0) + 1);
            }
        }

        // Calculate 0-inDegreeMap
        Queue<GraphNode> oIndegree = new LinkedList<>();
        for (GraphNode vertex : graph) {
            if (!inDegreeMap.containsKey(vertex)) {
                oIndegree.add(vertex);
            }
        }

        // BFS
        while (!oIndegree.isEmpty()) {
            GraphNode vertex = oIndegree.remove();
            ordering.add(vertex);
            if (vertex.neighbors != null) {
                for (GraphNode neighbor : vertex.neighbors) {
                    inDegreeMap.put(neighbor, inDegreeMap.get(neighbor) - 1);
                    if (inDegreeMap.get(neighbor) == 0) {
                        oIndegree.add(neighbor);
                    }
                }
            }
        }
        System.out.println(ordering);
        return ordering;
    }
}
