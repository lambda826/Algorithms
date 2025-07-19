package leetcode;

import common.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

/*

Given the root of a binary tree, determine if it is a valid binary search tree (BST).

A valid BST is defined as follows:
- The left subtree of a node contains only nodes with keys less than the node's key.
- The right subtree of a node contains only nodes with keys greater than the node's key.
- Both the left and right subtrees must also be binary search trees.


Example 1:
    Input: root = [2,1,3]
    Output: true
Example 2:
    Input: root = [5,1,4,null,null,3,6]
    Output: false
    Explanation: The root node's value is 5 but its right child's value is 4.


Constraints:
    The number of nodes in the tree is in the range [1, 10^4].
    -2^31 <= Node.val <= 2^(31 - 1)

*/

public class _0098_Validate_Binary_Search_Tree {

    class Solution {
        public boolean isValidBST(TreeNode root) {
            return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
        }

        private boolean isValidBST(TreeNode node, long lower, long upper) {
            if (node == null) {
                return true;
            } else {
                return node.val > lower
                        && node.val < upper
                        && isValidBST(node.left, lower, node.val)
                        && isValidBST(node.right, node.val, upper);
            }
        }
    }

    class Solution2 {

        public boolean isValidBST(TreeNode root) {
            return inorder(root, new TreeNode[1]);
        }

        private boolean inorder(TreeNode node, TreeNode[] pre) {
            if (node == null) {
                return true;
            }
            return inorder(node.left, pre) && isValidBST(node, pre) && inorder(node.right, pre);
        }

        private boolean isValidBST(TreeNode curr, TreeNode[] pre) {
            boolean isValid = true;
            if (pre[0] != null) {
                isValid = curr.val > pre[0].val;
            }
            pre[0] = curr;
            return isValid;
        }
    }

    class Solution3 {
        public boolean isValidBST(TreeNode root) {
            TreeNode pre = null;
            TreeNode curr = root;
            Deque<TreeNode> stack = new ArrayDeque<>();
            while (!stack.isEmpty() || curr != null) {
                if (curr != null) {
                    stack.offerLast(curr);
                    curr = curr.left;
                } else {
                    curr = stack.pollLast();
                    if (pre != null && pre.val >= curr.val) {
                        return false;
                    }
                    pre = curr;
                    curr = curr.right;
                }
            }
            return true;
        }
    }
}
