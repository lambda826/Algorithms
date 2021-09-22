package coding.leetcode._10_tree.bst;

import common.TreeNode;

/*

Given a binary search tree and a node in it, find the in-order successor of that node in the BST.
The successor of a node p is the node with the smallest key greater than p.val.


Example 1:
    Input:
        root = [2,1,3], p = 1
    Output:
        2
    Explanation:
        1's in-order successor node is 2. Note that both p and the return value is of TreeNode type.

Example 2:
    Input:
        root = [5,3,6,2,4,null,null,1], p = 6
    Output:
        null
    Explanation:
        There is no in-order successor of the current node, so the answer is null.


Constraints:
    The number of nodes in the tree is in the range [1, 10^4].
    -10^5 <= Node.val <= 10^5
    All Nodes will have unique values.

*/

public class _0285_Inorder_Successor_in_BST {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        TreeNode[] successor = new TreeNode[2];
        inorderSuccessor(root, p, successor);
        return successor[1];
    }

    private void inorderSuccessor(TreeNode node, TreeNode p, TreeNode[] successor) {
        if (node != null) {
            inorderSuccessor(node.left, p, successor);
            if (successor[0] == p) {
                successor[1] = node;
            }
            successor[0] = node;
            inorderSuccessor(node.right, p, successor);
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Search from root,
    // Compare current node value with node.val
    // Find the smallest val greater than node.val
    public TreeNode successor(TreeNode root, TreeNode p) {
        TreeNode successor = null;
        while (root != null) {
            if (root.val <= p.val) {
                root = root.right;
            } else {
                successor = root;
                root = root.left;
            }
        }
        return successor;
    }
}
