package questions._20_unionFind;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/*

You are given an array of variable pairs equations and an array of real numbers values,
    where equations[i] = [Ai, Bi] and values[i] represent the equation Ai / Bi = values[i].
Each Ai or Bi is a string that represents a single variable.

You are also given some queries, where queries[j] = [Cj, Dj] represents the jth query where you must find the answer for Cj / Dj = ?.
Return the answers to all queries. If a single answer cannot be determined, return -1.0.

Note:
    The input is always valid.
    You may assume that evaluating the queries will not result in division by zero and that there is no contradiction.


Example 1:
    Input:
        equations = [["a","b"],["b","c"]],
        values = [2.0,3.0],
        queries = [["a","c"],["b","a"],["a","e"],["a","a"],["x","x"]]
    Output:
        [6.00000,0.50000,-1.00000,1.00000,-1.00000]
    Explanation:
        Given:
            a / b = 2.0, b / c = 3.0
        queries are:
            a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ?
        return:
            [6.0, 0.5, -1.0, 1.0, -1.0 ]

Example 2:
    Input:
        equations = [["a","b"],["b","c"],["bc","cd"]],
        values = [1.5,2.5,5.0],
        queries = [["a","c"],["c","b"],["bc","cd"],["cd","bc"]]
    Output:
        [3.75000,0.40000,5.00000,0.20000]

Example 3:
    Input:
        equations = [["a","b"]],
        values = [0.5],
        queries = [["a","b"],["b","a"],["a","c"],["x","y"]]
    Output:
        [0.50000,2.00000,-1.00000,-1.00000]


Constraints:
    1 <= equations.length <= 20
    equations[i].length == 2
    1 <= Ai.length, Bi.length <= 5
    values.length == equations.length
    0.0 < values[i] <= 20.0
    1 <= queries.length <= 20
    queries[i].length == 2
    1 <= Cj.length, Dj.length <= 5
    Ai, Bi, Cj, Dj consist of lower case English letters and digits.

*/

public class _0399_Evaluate_Division {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    class Solution_UF {

        public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {

            Map<String, String> path = new HashMap<>();
            Map<String, Double> ratioToRoot = new HashMap<>();
            Map<String, Integer> height = new HashMap<>();

            double[] answer = new double[queries.size()];
            Arrays.fill(answer, -1.0);

            for (int i = 0; i < equations.size(); ++i) {
                String m = equations.get(i).get(0);
                String n = equations.get(i).get(1);
                String mm = find(m, path, ratioToRoot);
                String nn = find(n, path, ratioToRoot);
                // union
                if (!mm.equals(nn)) {
                    int mmh = height.getOrDefault(mm, 0);
                    int nnh = height.getOrDefault(nn, 0);
                    if (mmh < nnh) {
                        path.put(mm, nn);
                        ratioToRoot.put(mm, values[i] * ratioToRoot.get(n) / ratioToRoot.get(m));
                    } else {
                        path.put(nn, mm);
                        ratioToRoot.put(nn, ratioToRoot.get(m) / (values[i] * ratioToRoot.get(n)));
                        if (mmh == nnh) {
                            height.put(mm, mmh + 1);
                        }
                    }
                }
            }

            for (int i = 0; i < queries.size(); ++i) {
                String m = queries.get(i).get(0);
                String n = queries.get(i).get(1);
                if (path.containsKey(m) && path.containsKey(n)) {
                    String mm = find(m, path, ratioToRoot);
                    String nn = find(n, path, ratioToRoot);
                    if (mm.equals(nn)) {
                        answer[i] = ratioToRoot.get(m) / ratioToRoot.get(n);
                    }
                }
            }
            return answer;
        }

        private String find(String num, Map<String, String> path, Map<String, Double> ratioToRoot) {
            if (!path.containsKey(num)) {
                path.put(num, num);
                ratioToRoot.put(num, 1.0);
            }
            if (path.get(num).equals(num)) {
                return num;
            }
            String root = find(path.get(num), path, ratioToRoot);
            ratioToRoot.put(num, ratioToRoot.get(num) * ratioToRoot.get(path.get(num)));
            path.put(num, root);
            return root;
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    Map<String, Map<String, Double>> graph = new HashMap<>();

    public double[] calcEquation_DFS(String[][] equations, double[] values, String[][] queries) {
        buildGraph(equations, values);
        double[] res = new double[queries.length];
        for (int i = 0; i < queries.length; ++i) {
            if (!graph.containsKey(queries[i][0]) || !graph.containsKey(queries[i][1])) {
                res[i] = -1.0;
            } else if (queries[i][0] == queries[i][1]) {
                res[i] = 1.0;
            } else {
                res[i] = DFS(queries[i][0], queries[i][1], new HashSet<>(), 1.0);
            }
        }
        return res;
    }

    private double DFS(String from, String to, Set<String> visited, double curr) {
        if (from.equals(to)) {
            return curr;
        } else if (!visited.contains(from)) {
            visited.add(from);
            for (Map.Entry<String, Double> next : graph.get(from).entrySet()) {
                double temp = DFS(next.getKey(), to, visited, curr * next.getValue());
                if (temp != -1.0) {
                    return temp;
                }
            }
        }
        return -1.0;
    }

    private Map<String, Map<String, Double>> buildGraph(String[][] equations, double[] values) {
        Map<String, Map<String, Double>> graph = new HashMap<>();
        for (int i = 0; i < equations.length; i++) {
            graph.putIfAbsent(equations[i][0], new HashMap<>());
            graph.get(equations[i][0]).put(equations[i][1], values[i]);
            graph.putIfAbsent(equations[i][1], new HashMap<>());
            graph.get(equations[i][1]).put(equations[i][0], 1.0 / values[i]);
        }
        return graph;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private boolean isFound;
    private double temp;

    public double[] calcEquation_DFS2(String[][] equations, double[] values, String[][] queries) {
        graph = buildGraph(equations, values);
        double[] res = new double[queries.length];
        for (int i = 0; i < queries.length; i++) {
            if (!graph.containsKey(queries[i][0]) || !graph.containsKey(queries[i][1])) {
                res[i] = -1.0;
            } else if (queries[i][0] == queries[i][1]) {
                res[i] = 1.0;
            } else {
                temp = 1.0;
                isFound = false;
                DFS2(queries[i][0], queries[i][1], new HashSet<>(), 1.0);
                if (isFound) {
                    res[i] = temp;
                } else {
                    res[i] = -1.0;
                }
            }
        }
        return res;
    }

    private void DFS2(String root, String target, Set<String> visited, double curr) {
        if (!isFound) {
            if (root.equals(target)) {
                isFound = true;
                temp = curr;
            }
            if (!visited.contains(root)) {
                visited.add(root);
                for (Map.Entry<String, Double> node : graph.get(root).entrySet()) {
                    DFS2(node.getKey(), target, visited, curr * node.getValue());
                }
            }
        }
    }

}
