package questions._10_tree.traversal.universal_traversal;

import common.Node;

import java.util.ArrayDeque;
import java.util.Deque;

/*
Given a root of an N-ary tree, return a deep copy (clone) of the tree.

Each node in the n-ary tree contains a val (int) and a list (List[Node]) of its children.

class Node {
 public int val;
 public List<Node> children;
}

Nary-Tree input serialization is represented in their level order traversal, each group of children is separated by the null value (See examples).


Example 1:
    Input: root = [1,null,3,2,4,null,5,6]
    Output: [1,null,3,2,4,null,5,6]

Example 2:
    Input: root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
    Output: [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]


Constraints:
    The depth of the n-ary tree is less than or equal to 1000.
    The total number of nodes is between [0, 10^4].

Follow up: Can your solution work for the graph problem?

*/
public class _1490_Clone_N_ary_Tree {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public Node cloneTree(Node root) {
        Node result = null;
        if (root != null) {
            // Original tree
            Deque<Node> queue = new ArrayDeque<>();
            queue.offer(root);
            // Cloned tree
            Deque<Node> queueClone = new ArrayDeque<>();
            result = clone(root);
            queueClone.offer(result);
            // BFS
            while (!queue.isEmpty()) {
                Node curr = queue.poll();
                Node currClone = queueClone.poll();
                for (Node child : curr.children) {
                    queue.offer(child);
                    Node cloneChild = clone(child);
                    currClone.children.add(cloneChild);
                    queueClone.offer(cloneChild);
                }
            }
        }
        return result;
    }

    private Node clone(Node node) {
        Node cloned = new Node(node.val);
        if (node.children == null) {
            cloned.children = null;
        }
        return cloned;
    }
}
