/**
 *  @author: Yunxiang He
 *  @date  : 2018-10-30
 */

package questions.problems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class Task_Master {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int maxNumTask(int[] a, int[] b) {
        // Build graph, calculate indegree
        Map<Integer, List<Integer>> graph = new HashMap<>();
        Map<Integer, Integer> indegree = new HashMap<>();
        for (int i = 0; i < a.length; i++) {
            graph.putIfAbsent(b[i], new ArrayList<>());
            graph.get(b[i]).add(a[i]);
            indegree.put(a[i], indegree.getOrDefault(a[i], 0) + 1);
        }

        // Enque 0-indegree
        Queue<Integer> _0_indegree = new LinkedList<Integer>();
        for (int key : graph.keySet()) {
            if (!indegree.containsKey(key)) {
                _0_indegree.add(key);
            }
        }

        // BFS
        List<Integer> ordering = new ArrayList<>();
        while (!_0_indegree.isEmpty()) {
            int next = _0_indegree.remove();
            ordering.add(next);
            if (graph.get(next) != null) {
                for (int neighbour : graph.get(next)) {
                    indegree.put(neighbour, indegree.get(neighbour) - 1);
                    if (indegree.get(neighbour) == 0) {
                        _0_indegree.add(neighbour);
                    }
                }
            }
        }

        return ordering.size();
    }

    public static void main(String[] args) {
        System.out.println(new Task_Master().maxNumTask(new int[] { 1, 2, 3, 4, 6, 5 }, new int[] { 7, 6, 4, 1, 2, 1 }));
    }
}
