package algorithms.graph.connectivity;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Kosaraju {

    private Map<Character, List<Character>> adjacentList = new HashMap<>();
    private Map<Character, List<Character>> transposeAdjacentList = new HashMap<>();
    private Set<Set<Character>> scc = new HashSet<>();
    private int[] visited = new int[26];

    public void kosaraju(char[][] edges) {
        for (char[] edge : edges) {
            // Build graph using Adjacent List with given edges
            List<Character> neighbour = adjacentList.getOrDefault(edge[0], new ArrayList<>());
            adjacentList.putIfAbsent(edge[0], neighbour);
            neighbour.add(edge[1]);

            // Build transpose graph using Adjacent List with given edges
            List<Character> _neighbour = transposeAdjacentList.getOrDefault(edge[1], new ArrayList<>());
            transposeAdjacentList.putIfAbsent(edge[1], _neighbour);
            _neighbour.add(edge[0]);
        }

        // Build Topological order
        Deque<Character> topological = new ArrayDeque<>();
        for (char node : adjacentList.keySet()) {
            DFS_Topological(topological, node);
        }

        // Build Strong Connected Components
        while (!topological.isEmpty()) {
            char node = topological.pollFirst();
            if (visited[node - 'a'] != 3) {
                scc.add(DFS_StrongConnectedComponent(node, new HashSet<>()));
            }
        }
        System.out.println(scc);
    }

    private void DFS_Topological(Deque<Character> topological, char node) {
        if (visited[node - 'a'] == 0) {
            visited[node - 'a'] = 1;
            for (char neighbour : adjacentList.get(node)) {
                DFS_Topological(topological, neighbour);
            }
            topological.offerFirst(node); // Add to the top of the stack when finish visiting
            visited[node - 'a'] = 2;
        }
    }

    private Set<Character> DFS_StrongConnectedComponent(char node, Set<Character> scc) {
        if (visited[node - 'a'] != 3) {
            visited[node - 'a'] = 3;
            scc.add(node);
            if (transposeAdjacentList.containsKey(node)) {
                for (char nei : transposeAdjacentList.get(node)) {
                    DFS_StrongConnectedComponent(nei, scc);
                }
            }
        }
        return scc;
    }
}
