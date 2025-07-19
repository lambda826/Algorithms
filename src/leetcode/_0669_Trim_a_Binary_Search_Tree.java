package leetcode;

import common.TreeNode;

/*

Given the root of a binary search tree and the lowest and highest boundaries as low and high, trim the tree so that all its elements lies in [low, high].
Trimming the tree should not change the relative structure of the elements that will remain in the tree (i.e., any node's descendant should remain a descendant).
It can be proven that there is a unique answer.

Return the root of the trimmed binary search tree. Note that the root may change depending on the given bounds.


Example 1:
    Input: root = [1,0,2], low = 1, high = 2
    Output: [1,null,2]

Example 2:
    Input: root = [3,0,4,null,2,null,null,1], low = 1, high = 3
    Output: [3,2,null,1]


Constraints:
    The number of nodes in the tree is in the range [1, 10^4].
    0 <= Node.val <= 10^4
    The value of each node in the tree is unique.
    root is guaranteed to be a valid binary search tree.
    0 <= low <= high <= 10^4

*/
public class _0669_Trim_a_Binary_Search_Tree {

    class Solution {
        /**
         * Problem Breakdown
         * Binary Search Tree Properties:
         * The left child of a node contains only nodes with values less than the node’s value.
         * The right child of a node contains only nodes with values greater than the node’s value.
         *
         * Trimming the Tree:
         * If the current node’s value is less than the lower boundary (low),
         * - then we discard the current node and all nodes in its left subtree because all values in the left subtree will also be less than low.
         * - Thus, we only need to consider the right subtree.
         * If the current node’s value is greater than the upper boundary (high),
         * - then we discard the current node and all nodes in its right subtree because all values in the right subtree will also be greater than high.
         * - Thus, we only need to consider the left subtree.
         * If the current node’s value is within the range [low,high], we recursively trim both the left and right subtrees and retain the current node.
         *
         * Uniqueness of the Answer:
         * The problem guarantees a unique answer because the BST properties ensure there’s only one way to maintain the relative structure while trimming nodes outside the
         * specified range.
         **/

        public TreeNode trimBST(TreeNode root, int low, int high) {
            if (root == null) {
                return null;
            } else if (root.val < low) {
                return trimBST(root.right, low, high);
            } else if (root.val > high) {
                return trimBST(root.left, low, high);
            } else {
                root.left = trimBST(root.left, low, high);
                root.right = trimBST(root.right, low, high);
                return root;
            }
        }
    }
}
