package questions.leetcode;

import common.GraphNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

/*

Given a reference of a node in a connected undirected graph.
Return a deep copy (clone) of the graph.

Each node in the graph contains a value (int) and a list (List[Node]) of its neighbors.
    class Node {
        public int val;
        public List<Node> neighbors;
    }


Test case format:
    For simplicity, each node's value is the same as the node's index (1-indexed).
        For example, the first node with val == 1, the second node with val == 2, and so on.
        The graph is represented in the test case using an adjacency list.
    An adjacency list is a collection of unordered lists used to represent a finite graph.
    Each list describes the set of neighbors of a node in the graph.
    The given node will always be the first node with val = 1.
    You must return the copy of the given node as a reference to the cloned graph.


Example 1:
    Input:
        adjList = [[2,4],[1,3],[2,4],[1,3]]
    Output:
        [[2,4],[1,3],[2,4],[1,3]]
    Explanation:
        There are 4 nodes in the graph.
            1st node (val = 1)'s neighbors are 2nd node (val = 2) and 4th node (val = 4).
            2nd node (val = 2)'s neighbors are 1st node (val = 1) and 3rd node (val = 3).
            3rd node (val = 3)'s neighbors are 2nd node (val = 2) and 4th node (val = 4).
            4th node (val = 4)'s neighbors are 1st node (val = 1) and 3rd node (val = 3).

Example 2:
    Input:
        adjList = [[]]
    Output:
        [[]]
    Explanation:
        Note that the input contains one empty list.
        The graph consists of only one node with val = 1 and it does not have any neighbors.

Example 3:
    Input:
        adjList = []
    Output:
        []
    Explanation:
        This an empty graph, it does not have any nodes.

Example 4:
    Input:
        adjList = [[2],[1]]
    Output:
        [[2],[1]]


Constraints:
    The number of nodes in the graph is in the range [0, 100].
    1 <= Node.val <= 100
    Node.val is unique for each node.
    There are no repeated edges and no self-loops in the graph.
    The Graph is connected and all nodes can be visited starting from the given node.

*/
public class _0133_Clone_Graph {

    class Solution_DFS {
        public GraphNode cloneGraph(GraphNode graphNode) {
            if (graphNode == null) {
                return null;
            }
            return dfs(graphNode, new HashMap<>());
        }

        private GraphNode dfs(GraphNode GraphNode, Map<Integer, GraphNode> visited) {
            if (!visited.containsKey(GraphNode.val)) {
                GraphNode cloned = new GraphNode(GraphNode.val, new ArrayList<>());
                visited.put(cloned.val, cloned);
                for (GraphNode neighbor : GraphNode.neighbors) {
                    cloned.neighbors.add(dfs(neighbor, visited));
                }
            }
            return visited.get(GraphNode.val);
        }
    }

    class Solution_BFS {
        public GraphNode cloneGraph(GraphNode node) {
            if (node == null) {
                return null;
            }
            Queue<GraphNode> queue = new ArrayDeque<>();
            Map<Integer, GraphNode> visited = new HashMap<>();
            visited.put(node.val, new GraphNode(node.val, new ArrayList<>()));
            queue.offer(node);
            while (!queue.isEmpty()) {
                GraphNode next = queue.poll();
                for (GraphNode neighbor : next.neighbors) {
                    if (!visited.containsKey(neighbor.val)) {
                        visited.put(neighbor.val, new GraphNode(neighbor.val, new ArrayList<>()));
                        queue.offer(neighbor);
                    }
                    visited.get(next.val)
                        .neighbors
                        .add(visited.get(neighbor.val));
                }
            }
            return visited.get(node.val);
        }
    }

}
