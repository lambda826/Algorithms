package questions.leetcode.tree.dfs;

import common.TreeNode;

/*

Given the root of a binary tree, return the sum of all left leaves.

A leaf is a node with no children. A left leaf is a leaf that is the left child of another node.


Example 1:
    Input: root = [3,9,20,null,null,15,7]
    Output: 24
    Explanation: There are two left leaves in the binary tree, with values 9 and 15 respectively.

Example 2:
    Input: root = [1]
    Output: 0

Constraints:
    The number of nodes in the tree is in the range [1, 1000].
    -1000 <= Node.val <= 1000

*/
public class _0404_Sum_of_Left_Leaves {

    class Solution {
        public int sumOfLeftLeaves(TreeNode root) {
            return sumOfLeftLeaves(root, false);
        }

        private int sumOfLeftLeaves(TreeNode node, boolean isLeft) {
            if (node == null) {
                return 0;
            } else if (isLeft && node.left == null && node.right == null) {
                return node.val;
            } else {
                return sumOfLeftLeaves(node.left, true) + sumOfLeftLeaves(node.right, false);
            }
        }
    }
}
