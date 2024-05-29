package questions.leetcode;

import common.TreeNode;

/*

Given the root of a Binary Search Tree (BST), return the minimum absolute difference between the values of any two different nodes in the tree.


Example 1:
    Input: root = [4,2,6,1,3]
    Output: 1
Example 2:
    Input: root = [1,0,48,null,null,12,49]
    Output: 1


Constraints:
    The number of nodes in the tree is in the range [2, 10^4].
    0 <= Node.val <= 10^5

*/
public class _0530_Minimum_Absolute_Difference_in_BST {

    class Solution {
        public int getMinimumDifference(TreeNode root) {
            int[] min = { Integer.MAX_VALUE };
            inorder(root, new Integer[] { null }, min);
            return min[0];
        }

        private void inorder(TreeNode curr, Integer[] pre, int[] min) {
            if (curr != null) {
                inorder(curr.left, pre, min);
                if (pre[0] != null) {
                    min[0] = Math.min(min[0], Math.abs(pre[0] - curr.val));
                }
                pre[0] = curr.val;
                inorder(curr.right, pre, min);
            }
        }
    }
}
