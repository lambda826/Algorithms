package leetcode;

import common.TreeNode;

/*

A path in a binary tree is a sequence of nodes where each pair of adjacent nodes in the sequence has an edge connecting them.
A node can only appear in the sequence at most once. Note that the path does not need to pass through the root.
The path sum of a path is the sum of the node's values in the path.
Given the root of a binary tree, return the maximum path sum of any path.


Example 1:
    Input:
        root = [1,2,3]
    Output:
        6
    Explanation:
        The optimal path is 2 -> 1 -> 3 with a path sum of 2 + 1 + 3 = 6.

Example 2:
    Input:
        root = [-10,9,20,null,null,15,7]
    Output:
        42
    Explanation:
        The optimal path is 15 -> 20 -> 7 with a path sum of 15 + 20 + 7 = 42.


Constraints:
    The number of nodes in the tree is in the range [1, 3 * 10^4].
    -1000 <= Node.val <= 1000

*/
public class _0124_Binary_Tree_Maximum_Path_Sum {

    class Solution {

        /**
         * To solve the problem of finding the maximum path sum in a binary tree, you can use a recursive approach to explore all possible paths and calculate their sums.
         * The key here is to keep track of the maximum path sum at each node while considering two possible scenarios:
         *
         * - The path passes through the current node and extends to one or both of its children.
         * - The path does not pass through the current node, but instead, the maximum path sum from either of its children is considered.
         */
        public int maxPathSum(TreeNode root) {
            int[] max = { Integer.MIN_VALUE };
            postOrder(root, max);
            return max[0];
        }

        private int postOrder(TreeNode node, int[] max) {
            if (node == null) {
                return 0;
            } else {
                int left = Math.max(0, postOrder(node.left, max));
                int right = Math.max(0, postOrder(node.right, max));
                int maxPathSum = node.val + Math.max(left, right);
                max[0] = Math.max(max[0], node.val + left + right);
                return maxPathSum;
            }
        }
    }
}