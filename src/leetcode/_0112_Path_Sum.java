package leetcode;

import common.TreeNode;

import java.util.ArrayDeque;
import java.util.Queue;

/*

Given the root of a binary tree and an integer targetSum, return true if the tree has a root-to-leaf path such that adding up all the values along the path equals targetSum.
A leaf is a node with no children.


Example 1:
    Input: root = [5,4,8,11,null,13,4,7,2,null,null,null,1], targetSum = 22
    Output: true
    Explanation: The root-to-leaf path with the target sum is shown.

Example 2:
    Input: root = [1,2,3], targetSum = 5
    Output: false
    Explanation: There two root-to-leaf paths in the tree:
                    (1 --> 2): The sum is 3.
                    (1 --> 3): The sum is 4.
                    There is no root-to-leaf path with sum = 5.

Example 3:
    Input: root = [], targetSum = 0
    Output: false
    Explanation: Since the tree is empty, there are no root-to-leaf paths.


Constraints:
    The number of nodes in the tree is in the range [0, 5000].
    -1000 <= Node.val <= 1000
    -1000 <= targetSum <= 1000

*/
public class _0112_Path_Sum {

    class Solution {
        /**
         * To determine if a binary tree has a root-to-leaf path such that the sum of the values along the path equals a given targetSum, you can use a recursive approach.
         * The idea is to traverse the tree, subtracting the current node's value from the targetSum at each step, and check if you reach a leaf node with the remaining sum
         * equal to zero.
         *
         * Base Case: If the current node (root) is null, there is no path, so return false.
         * Leaf Node Check: If the current node is a leaf (both left and right children are null), check if its value equals the targetSum. If so, return true.
         * Recursive Case: Subtract the current node's value from the targetSum to get the remainingSum. Recursively check the left and right subtrees with the remainingSum.
         * Use the logical OR (||) to return true if either subtree has a path that satisfies the condition.
         */
        public boolean hasPathSum(TreeNode root, int targetSum) {
            if (root == null) {
                return false;
            } else if (root.left == null && root.right == null) {
                return root.val == targetSum;
            } else {
                return hasPathSum(root.left, targetSum - root.val) || hasPathSum(root.right, targetSum - root.val);
            }
        }
    }

    class Solution2 {
        public boolean hasPathSum(TreeNode root, int targetSum) {
            if (root != null) {
                Queue<TreeNode> deque = new ArrayDeque<>();
                deque.offer(root);
                while (!deque.isEmpty()) {
                    TreeNode node = deque.poll();
                    if (node.left == null && node.right == null && node.val == targetSum) {
                        return true;
                    }
                    if (node.left != null) {
                        node.left.val += node.val;
                        deque.offer(node.left);
                    }
                    if (node.right != null) {
                        node.right.val += node.val;
                        deque.offer(node.right);
                    }
                }
            }
            return false;
        }
    }
}
