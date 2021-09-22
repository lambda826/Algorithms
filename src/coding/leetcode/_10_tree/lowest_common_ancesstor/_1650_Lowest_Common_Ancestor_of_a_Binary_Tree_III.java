package coding.leetcode._10_tree.lowest_common_ancesstor;


/*

Given two nodes of a binary tree p and q, return their lowest common ancestor (LCA).
Each node will have a reference to its parent node. The definition for Node is below:

class Node {
    public int val;
    public Node left;
    public Node right;
    public Node parent;
}


Example 1:
    Input:
        root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
    Output:
        3
    Explanation:
        The LCA of nodes 5 and 1 is 3.

Example 2:
    Input:
        root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
    Output:
        5
    Explanation:
        The LCA of nodes 5 and 4 is 5 since a node can be a descendant of itself according to the LCA definition.

Example 3:
    Input:
        root = [1,2], p = 1, q = 2
    Output:
        1

Constraints:
    The number of nodes in the tree is in the range [2, 100000].
    -1000000000 <= Node.val <= 1000000000
    All Node.val are unique.
    p != q
    p and q exist in the tree.

*/

import common.Node;

import java.util.ArrayDeque;
import java.util.Deque;

public class _1650_Lowest_Common_Ancestor_of_a_Binary_Tree_III {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    class Solution_CountDepth {
        public Node lowestCommonAncestor2(Node p, Node q) {
            int lc = getLc(p);
            int rc = getLc(q);
            while (lc > rc) {
                --lc;
                p = p.parent;
            }
            while (lc < rc) {
                --rc;
                q = q.parent;
            }
            while (p != q) {
                p = p.parent;
                q = q.parent;
            }
            return p;
        }

        private int getLc(Node node) {
            int depth = 0;
            while (node != null) {
                node = node.parent;
                ++depth;
            }
            return depth;
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // 1. Use a stack to store parents;
    // 2. Find the last equal parent.
    class Solution_CompareParent {
        public Node lowestCommonAncestor(Node p, Node q) {
            Deque<Node> que1 = new ArrayDeque<>();
            Deque<Node> que2 = new ArrayDeque<>();
            enqueue(p, que1);
            enqueue(q, que2);
            Node lca = null;
            while (que1.peekFirst() != null && que1.peekFirst() == que2.peekFirst()) {
                lca = que1.pollFirst();
                que2.pollFirst();
            }
            return lca;
        }

        private void enqueue(Node node, Deque<Node> que) {
            while (node != null) {
                que.offerFirst(node);
                node = node.parent;
            }
        }
    }
}