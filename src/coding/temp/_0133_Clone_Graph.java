package coding.temp;

import common.GraphNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
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

    public static _0133_Clone_Graph instance = null;

    _0133_Clone_Graph() {
        instance = new _0133_Clone_Graph();
    }

    public static void main(String[] args) {
        _0133_Clone_Graph test = new _0133_Clone_Graph();
        GraphNode node0 = new GraphNode(0);
        GraphNode node1 = new GraphNode(1);
        GraphNode node2 = new GraphNode(2);
        GraphNode node3 = new GraphNode(3);
        GraphNode node4 = new GraphNode(4);
        GraphNode node5 = new GraphNode(5);
        node0.neighbors.add(node1);
        node0.neighbors.add(node5);
        node1.neighbors.add(node2);
        node1.neighbors.add(node5);
        node2.neighbors.add(node3);
        node3.neighbors.add(node4);
        node3.neighbors.add(node4);
        node4.neighbors.add(node5);
        node4.neighbors.add(node5);

        test.cloneGraph_BFS(node0);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // DFS + memo
    Map<GraphNode, GraphNode> map = new HashMap<>();

    public GraphNode cloneGraph(GraphNode node) {
        return DFS(node);
    }

    private GraphNode DFS(GraphNode node) {
        if (!map.containsKey(node)) {
            GraphNode nNode = new GraphNode(node.val, new ArrayList<>());
            map.put(node, nNode);
            for (GraphNode nei : node.neighbors) {
                nNode.neighbors.add(DFS(nei));
            }
        }
        return map.get(node);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
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
