package leetcode;

/*

You are given the root of a binary tree containing digits from 0 to 9 only.

Each root-to-leaf path in the tree represents a number.

For example, the root-to-leaf path 1 -> 2 -> 3 represents the number 123.
Return the total sum of all root-to-leaf numbers. Test cases are generated so that the answer will fit in a 32-bit integer.

A leaf node is a node with no children.


Example 1:
    Input:
        root = [1,2,3]
    Output:
        25
    Explanation:
        The root-to-leaf path 1->2 represents the number 12.
        The root-to-leaf path 1->3 represents the number 13.
        Therefore, sum = 12 + 13 = 25.

Example 2:
    Input:
        root = [4,9,0,5,1]
    Output:
        1026
    Explanation:
        The root-to-leaf path 4->9->5 represents the number 495.
        The root-to-leaf path 4->9->1 represents the number 491.
        The root-to-leaf path 4->0 represents the number 40.
        Therefore, sum = 495 + 491 + 40 = 1026.


Constraints:
    The number of nodes in the tree is in the range [1, 1000].
    0 <= Node.val <= 9
    The depth of the tree will not exceed 10.

*/

import common.TreeNode;

public class _0129_Sum_Root_to_Leaf_Numbers {

    class Solution {

        public int sumNumbers(TreeNode root) {
            return sumNumbers(root, 0);
        }

        private int sumNumbers(TreeNode node, int preSum) {
            if (node.left == null && node.right == null) {
                return node.val + preSum * 10;
            }
            int sum = 0;
            if (node.left != null) {
                sum += sumNumbers(node.left, preSum * 10 + node.val);
            }
            if (node.right != null) {
                sum += sumNumbers(node.right, preSum * 10 + node.val);
            }
            return sum;
        }
    }
}