package questions.leetcode;

import common.TreeNode;

/*

The thief has found himself a new place for his thievery again. There is only one entrance to this area, called root.

Besides the root, each house has one and only one parent house. After a tour, the smart thief realized that all houses in this place form a binary tree.
It will automatically contact the police if two directly-linked houses were broken into on the same night.

Given the root of the binary tree, return the maximum amount of money the thief can rob without alerting the police.


Example 1:
    Input: root = [3,2,3,null,3,null,1]
    Output: 7
    Explanation: Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.

Example 2:
    Input: root = [3,4,5,1,3,null,1]
    Output: 9
    Explanation: Maximum amount of money the thief can rob = 4 + 5 = 9.


Constraints:
    The number of nodes in the tree is in the range [1, 10^4].
    0 <= Node.val <= 10^4

*/
public class _0337_House_Robber_III {

    class Solution {
        public int rob(TreeNode root) {
            int[] res = robHelper(root);
            return Math.max(res[0], res[1]);
        }

        private int[] robHelper(TreeNode node) {
            int[] res = { 0, 0 };
            if (node != null) {
                int[] left = robHelper(node.left);
                int[] right = robHelper(node.right);
                res[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
                res[1] = left[0] + right[0] + node.val;
            }
            return res;
        }
    }
}