package coding.leetcode._11_graph;

import common.GraphNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/*

Given the head of a graph, return a deep copy (clone) of the graph. 
Each node in the graph contains a label (int) and a list (List[GraphNode]) of its neighbors. 
There is an edge between the given node and each of the nodes in its neighbors.


OJ's undirected graph serialization (so you can understand error output):
Nodes are labeled uniquely.
We use # as a separator for each node, and , as a separator for node label and each neighbor of the node.
 

As an example, consider the serialized graph {0,1,2#1,2#2,2}.

The graph has a total of three nodes, and therefore contains three parts as separated by #.
    First node is labeled as 0. Connect node 0 to both nodes 1 and 2.
    Second node is labeled as 1. Connect node 1 to node 2.
    Third node is labeled as 2. Connect node 2 to node 2 (itself), thus forming a self-cycle.
 

Visually, the graph looks like the following:

       1
      / \
     /   \
    0 --- 2
         / \
         \_/


Note: 
    The information about the tree serialization is only meant so that you can understand error output if you get a wrong answer. 
    You don't need to understand the serialization to solve the problem.

*/

public class _0133_Clone_Graph {

    class Node {

        public int val;
        public List<Node> neighbors;

        public Node() {
            val = 0;
            neighbors = new ArrayList<>();
        }

        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<>();
        }

        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    class Solution_DFS {

        public Node cloneGraph(Node node) {
            return DFS(node, new HashMap<>());
        }

        private Node DFS(Node node, Map<Node, Node> visited) {
            if (node == null) {
                return null;
            }
            if (!visited.containsKey(node)) {
                Node clone = new Node(node.val);
                visited.put(node, clone);
                for (Node nei : node.neighbors) {
                    clone.neighbors.add(DFS(nei, visited));
                }
            }
            return visited.get(node);
        }

    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public GraphNode cloneGraph_BFS(GraphNode node) {
        if (node == null) {
            return null;
        }
        Map<Integer, GraphNode> visited = new HashMap<>();
        Queue<GraphNode> que = new LinkedList<>();
        que.add(node);
        GraphNode clone = new GraphNode(node.val);
        GraphNode root = clone;
        visited.put(clone.val, clone);
        while (!que.isEmpty()) {
            node = que.remove();
            clone = visited.get(node.val);
            for (GraphNode neighbor : node.neighbors) {
                if (!visited.containsKey(neighbor.val)) {
                    visited.put(neighbor.val, new GraphNode(neighbor.val));
                    que.add(neighbor);
                }
                clone.neighbors.add(visited.get(neighbor.val));
            }
        }
        return root;
    }

}
