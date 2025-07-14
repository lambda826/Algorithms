package questions.leetcode.tree.dfs;

import common.TreeNode;

/*

A binary tree is uni-valued if every node in the tree has the same value.

Given the root of a binary tree, return true if the given tree is uni-valued, or false otherwise.


Example 1:
    Input: root = [1,1,1,1,1,null,1]
    Output: true

Example 2:
    Input: root = [2,2,2,5,2]
    Output: false


Constraints:
    The number of nodes in the tree is in the range [1, 100].
    0 <= Node.val < 100

*/
public class _0965_Univalued_Binary_Tree {

    class Solution {
        public boolean isUnivalTree(TreeNode root) {
            return isUnivalTree(root, root.val);
        }

        private boolean isUnivalTree(TreeNode node, int val) {
            if (node == null) {
                return true;
            } else if (node.val != val) {
                return false;
            } else {
                return isUnivalTree(node.left, val) && isUnivalTree(node.right, val);
            }
        }
    }
}
