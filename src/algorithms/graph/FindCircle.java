/**
 *  @author Yunxiang He
 *  @date 01/12/2019
 */

package algorithms.graph;

import java.util.ArrayList;
import java.util.List;

public class FindCircle {

    public static void main(String[] args) {
        int[][] edges_noCircle = new int[][] { { 0, 1 }, { 1, 2 }, { 1, 3 }, { 1, 4 }, { 2, 5 }, { 3, 6 } };
        int[][] edges_circle = new int[][] { { 0, 1 }, { 1, 2 }, { 1, 3 }, { 1, 4 }, { 2, 5 }, { 3, 6 }, { 0, 6 } };
        findCircle(7, edges_noCircle);
        findCircle(7, edges_circle);

        System.out.println(findCircles(7, BuildGraph.buildGraph_undirected_unweighted(7, edges_circle)));
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Undirected graph
    // Union-find to find whether there is a circle
    // Vertices are labeled from 0 to n - 1
    public static boolean findCircle(int n, int[][] edges) {
        int[] roots = new int[n];
        int[] size = new int[n];
        boolean hasCircle = false;
        for (int i = 0; i < n; ++i) {
            roots[i] = i;
            size[i] = 1;
        }
        for (int[] edge : edges) {
            int r1 = find(roots, edge[0]);
            int r2 = find(roots, edge[1]);
            if (r1 == r2) {
                hasCircle = true;
                break;
            } else if (size[r1] < size[r2]) {
                size[r2] += size[r1];
                roots[r1] = r2;
            } else {
                size[r1] += size[r2];
                roots[r2] = r1;
            }
        }
        return hasCircle;
    }

    private static int find(int[] roots, int index) {
        if (index == roots[index]) {
            return index;
        }
        return roots[index] = find(roots, roots[index]);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static boolean findCircle(int n, List<Integer>[] graph) {
        int[] visited = new int[graph.length];
        boolean hashCircle = false;
        for (int i = 0; i < graph.length; ++i) {
            if (visited[i] == 0) {
                if (DFS(graph, visited, i)) {
                    hashCircle = true;
                    break;
                }
            }
        }
        return hashCircle;
    }

    private static boolean DFS(List<Integer>[] graph, int[] visited, int vertex) {
        if (visited[vertex] == 2) {
            return true;
        } else if (visited[vertex] == 0) {
            visited[vertex] = 1;
            for (int nei : graph[vertex]) {
                if (DFS(graph, visited, nei)) {
                    return true;
                }
            }
            visited[vertex] = 2;
        }
        return false;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static List<List<Integer>> findCircles(int n, List<Integer>[] graph) {
        List<List<Integer>> circleList = new ArrayList<>();
        int[] visited = new int[graph.length];
        for (int i = 0; i < graph.length; ++i) {
            if (visited[i] == 0) {
                DFS(graph, visited, circleList, new ArrayList<>(), i);
            }
        }
        return circleList;
    }

    private static void DFS(List<Integer>[] graph, int[] visited, List<List<Integer>> circleList, List<Integer> circle, int vertex) {
        if (visited[vertex] == 2) {
            circleList.add(circle);
        } else if (visited[vertex] == 0) {
            visited[vertex] = 1;
            circle.add(vertex);
            for (int nei : graph[vertex]) {
                DFS(graph, visited, nei);
            }
            circle.remove(circle.size() - 1);
            visited[vertex] = 2;
        }
    }
}
