package questions.leetcode;

import common.TreeNode;

/*

Given a binary tree, determine if it is height-balanced.


Example 1:
    Input: root = [3,9,20,null,null,15,7]
    Output: true

Example 2:
    Input: root = [1,2,2,3,3,null,null,4,4]
    Output: false

Example 3:
    Input: root = []
    Output: true


Constraints:
    The number of nodes in the tree is in the range [0, 5000].
    -10^4 <= Node.val <= 10^4

*/
public class _0110_Balanced_Binary_Tree {

    class Solution {
        public boolean isBalanced(TreeNode root) {
            boolean[] isBalanced = { true };
            dfs(root, 0, isBalanced);
            return isBalanced[0];
        }

        private int dfs(TreeNode node, int height, boolean[] isBalanced) {
            if (isBalanced[0]) {
                if (node != null) {
                    int leftHeight = dfs(node.left, height + 1, isBalanced);
                    int rightHeight = dfs(node.right, height + 1, isBalanced);
                    isBalanced[0] = isBalanced[0] && Math.abs(leftHeight - rightHeight) < 2;
                    height = Math.max(leftHeight, rightHeight);
                }
            }
            return height;
        }
    }
}
