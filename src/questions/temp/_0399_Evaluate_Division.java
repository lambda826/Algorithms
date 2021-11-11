/**
 *  @author Yunxiang He
 *  @date 06/27/2018
 */

package questions.temp;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*

Equations are given in the format A / B = k, where A and B are variables represented as strings, and k is a real number (floating point number). 
Given some queries, return the answers. If the answer does not exist, return -1.0.


Example:
    Given a / b = 2.0, b / c = 3.0. 
    queries are: a / c = ?, b / a = ?, a / equations = ?, a / a = ?, x / x = ? . 
    return [6.0, 0.5, -1.0, 1.0, -1.0 ].

    The input is: vector<pair<string, string>> equations, vector<double>& values, vector<pair<string, string>> queries , where equations.size() == values.size(), and the values are positive. 
    This represents the equations. Return vector<double>.

    According to the example above:
        equations = [ ["a", "b"], ["b", "c"] ],
        values = [2.0, 3.0],
        queries = [ ["a", "c"], ["b", "a"], ["a", "equations"], ["a", "a"], ["x", "x"] ]. 
        The input is always valid. You may assume that evaluating the queries will result in no division by zero and there is no contradiction.

*/

public class _0399_Evaluate_Division {

    public static void main(String[] args) {
        _0399_Evaluate_Division _0399_Evaluate_Division = new _0399_Evaluate_Division();
        _0399_Evaluate_Division.calcEquation_DFS(new String[][] { { "a", "b" }, { "b", "c" }, }, new double[] { 2.0, 3.0, }, new String[][] { { "a", "c" }, });
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    Map<String, String> root = new HashMap<>();
    Map<String, Double> ratio2root = new HashMap<>();

    public double[] calcEquation(String[][] equations, double[] values, String[][] queries) {
        String r1;
        String r2;
        for (int i = 0; i < equations.length; ++i) {
            r1 = find(equations[i][0]);
            r2 = find(equations[i][1]);
            if (!r1.equals(r2)) {
                root.put(r1, r2);
                ratio2root.put(r1, values[i] * ratio2root.get(equations[i][1]) / ratio2root.get(equations[i][0]));
            }
        }
        double[] res = new double[queries.length];
        for (int i = 0; i < queries.length; ++i) {
            if (ratio2root.containsKey(queries[i][0]) && ratio2root.containsKey(queries[i][1])) {
                if (queries[i][0].equals(queries[i][1])) {
                    res[i] = 1.0;
                } else if (find(queries[i][0]).equals(find(queries[i][1]))) {
                    res[i] = ratio2root.get(queries[i][0]) / ratio2root.get(queries[i][1]);
                } else {
                    res[i] = -1.0;
                }
            } else {
                res[i] = -1.0;
            }
        }
        return res;
    }

    private String find(String index) {
        if (!root.containsKey(index)) {
            root.put(index, index);
            ratio2root.put(index, 1.0);
        }
        if (root.get(index).equals(index)) {
            return index;
        }
        String temp = find(root.get(index));
        ratio2root.put(index, ratio2root.get(index) * ratio2root.get(root.get(index)));
        root.put(index, temp);
        return temp;
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
