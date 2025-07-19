package leetcode.tree.dfs;

import common.TreeNode;

/*

Given a non-empty special binary tree consisting of nodes with the non-negative value, where each node in this tree has exactly two or zero sub-node.
If the node has two sub-nodes, then this node's value is the smaller value among its two sub-nodes. More formally, the property root.val = min(root.left.val, root.right.val) always holds.

Given such a binary tree, you need to output the second minimum value in the set made of all the nodes' value in the whole tree.
If no such second minimum value exists, output -1 instead.


Example 1:
    Input: root = [2,2,5,null,null,5,7]
    Output: 5
    Explanation: The smallest value is 2, the second smallest value is 5.

Example 2:
    Input: root = [2,2,2]
    Output: -1
    Explanation: The smallest value is 2, but there isn't any second smallest value.


Constraints:
    The number of nodes in the tree is in the range [1, 25].
    1 <= Node.val <= 2^31 - 1
    root.val == min(root.left.val, root.right.val) for each internal node of the tree.

*/
public class _0671_Second_Minimum_Node_In_a_Binary_Tree {

    class Solution {
        public int findSecondMinimumValue(TreeNode root) {
            return findSecondMinimumValue(root, root.val);
        }

        private int findSecondMinimumValue(TreeNode node, int parentVal) {
            if (node == null) {
                return -1;
            } else if (node.val != parentVal) {
                return node.val;
            } else {
                int left = findSecondMinimumValue(node.left, node.val);
                int right = findSecondMinimumValue(node.right, node.val);
                if (left == -1 && right != -1) {
                    return right;
                }
                if (left != -1 && right == -1) {
                    return left;
                }
                if (left == -1 && right == -1) {
                    return -1;
                }
                return Math.min(left, right);
            }
        }
    }
}
