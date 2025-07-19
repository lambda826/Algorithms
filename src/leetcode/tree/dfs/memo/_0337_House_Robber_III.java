package leetcode.tree.dfs.memo;

import common.TreeNode;

import java.util.HashMap;
import java.util.Map;

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
        // Main function to calculate the maximum amount of money the robber can rob tonight without alerting the police.
        public int rob(TreeNode root) {
            int[] res = robHelper(root);
            // We can choose to rob or not rob the root, so we take the maximum of the two scenarios.
            return Math.max(res[0], res[1]);
        }

        // Helper function to return the maximum amount of money that can be robbed if starting from the given node.
        // res[0] stores the maximum amount when the current node is not robbed.
        // res[1] stores the maximum amount when the current node is robbed.
        private int[] robHelper(TreeNode node) {
            int[] res = {0, 0}; // Initialize the result for the base case where the node is null.
            if (node != null) {
                // Recursively solve for the left and right subtrees.
                int[] left = robHelper(node.left);
                int[] right = robHelper(node.right);
                // If the current node is not robbed, the maximum money from the children is the sum of max values from robbing or not robbing each child.
                res[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
                // If the current node is robbed, we cannot rob its direct children, but we can take the value of this node plus the max values from not robbing its children.
                res[1] = left[0] + right[0] + node.val;
            }
            return res;
        }
    }

    class Solution2 {
        public int rob(TreeNode root) {
            Map<TreeNode, Map<Boolean, Integer>> dp = new HashMap<>();
            return Math.max(rob(root, true, dp), rob(root, false, dp));
        }

        private int rob(TreeNode node, boolean isRob, Map<TreeNode, Map<Boolean, Integer>> dp) {
            if (node == null) {
                return 0;
            }
            if (dp.containsKey(node)) {
                if (dp.get(node).containsKey(isRob)) {
                    return dp.get(node).get(isRob);
                }
            } else {
                dp.put(node, new HashMap<>());
            }
            int sum = 0;
            if (isRob) {
                int left = rob(node.left, false, dp);
                int right = rob(node.right, false, dp);
                sum = node.val + left + right;
            } else {
                sum = Math.max(rob(node.left, false, dp), rob(node.left, true, dp))
                        + Math.max(rob(node.right, false, dp), rob(node.right, true, dp));
            }
            dp.get(node).put(isRob, sum);
            return sum;
        }
    }
}