package questions.leetcode;

import common.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

/*

Given the root of a Binary Search Tree (BST), return the minimum difference between the values of any two different nodes in the tree.


Example 1:
    Input: root = [4,2,6,1,3]
    Output: 1

Example 2:
    Input: root = [1,0,48,null,null,12,49]
    Output: 1


Constraints:
    The number of nodes in the tree is in the range [2, 100].
    0 <= Node.val <= 10^5

*/
public class _0783_Minimum_Distance_Between_BST_Nodes {

    class Solution {
        public int minDiffInBST(TreeNode root) {
            int[] min = { Integer.MAX_VALUE };
            inorder(root, new Integer[] { null }, min);
            return min[0];
        }

        private void inorder(TreeNode node, Integer[] pre, int[] min) {
            if (node != null) {
                inorder(node.left, pre, min);
                if (pre[0] != null) {
                    min[0] = Math.min(min[0], node.val - pre[0]);
                }
                pre[0] = node.val;
                inorder(node.right, pre, min);
            }
        }
    }

    class Solution2 {
        public int minDiffInBST(TreeNode root) {
            TreeNode pre = null;
            Deque<TreeNode> stack = new ArrayDeque<>();
            int min = Integer.MAX_VALUE;
            TreeNode curr = root;
            while (!stack.isEmpty() || curr != null) {
                if (curr != null) {
                    stack.offerLast(curr);
                    curr = curr.left;
                } else {
                    curr = stack.pollLast();
                    if (pre != null) {
                        min = Math.min(min, curr.val - pre.val);
                    }
                    pre = curr;
                    curr = curr.right;
                }
            }
            return min;
        }
    }


}
