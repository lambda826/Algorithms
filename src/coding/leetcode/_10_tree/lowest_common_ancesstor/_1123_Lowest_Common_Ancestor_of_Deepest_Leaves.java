package coding.leetcode._10_tree.lowest_common_ancesstor;

import common.TreeNode;

public class _1123_Lowest_Common_Ancestor_of_Deepest_Leaves {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // This problem can be transformed into:
    //      Finding the max depth of left subtree and right subtree if equals.
    //
    // Post order:
    // 1. Use two global variables store Target node and max depth value;
    // 2. Update max depth value for every recursion;
    // 2.1. Compare left and right depth with max.
    // 2.2. Return max(left, right)
    public class Solution_PostOrder {

        private TreeNode target;
        private int max = Integer.MIN_VALUE;

        public TreeNode lcaDeepestLeaves(TreeNode root) {
            postOrder(root, 0);
            return target;
        }

        private int postOrder(TreeNode node, int depth) {
            max = Math.max(max, depth);
            if (node == null) {
                return depth;
            } else {
                int left = postOrder(node.left, depth + 1);
                int right = postOrder(node.right, depth + 1);
                if (left == max && right == max) {
                    target = node;
                }
                return Math.max(left, right);
            }
        }
    }

}