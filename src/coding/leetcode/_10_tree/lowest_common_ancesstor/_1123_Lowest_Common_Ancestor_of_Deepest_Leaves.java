package coding.leetcode._10_tree.lowest_common_ancesstor;

import common.TreeNode;

/*

Given the root of a binary tree, return the lowest common ancestor of its deepest leaves.

Recall that:
    The node of a binary tree is a leaf if and only if it has no children
    The depth of the root of the tree is 0. if the depth of a node is d, the depth of each of its children is d + 1.
    The lowest common ancestor of a set S of nodes, is the node A with the largest depth such that every node in S is in the subtree with root A.
    Note: This question is the same as 865: https://leetcode.com/problems/smallest-subtree-with-all-the-deepest-nodes/


Example 1:
    Input:
        root = [3,5,1,6,2,0,8,null,null,7,4]
    Output:
        [2,7,4]
    Explanation:
        We return the node with value 2, colored in yellow in the diagram.
        The nodes coloured in blue are the deepest leaf-nodes of the tree.
        Note that nodes 6, 0, and 8 are also leaf nodes, but the depth of them is 2, but the depth of nodes 7 and 4 is 3.

Example 2:
    Input:
        root = [1]
    Output:
        [1]
    Explanation:
        The root is the deepest node in the tree, and it's the lca of itself.

Example 3:
    Input:
        root = [0,1,3,null,2]
    Output:
        [2]
    Explanation:
        The deepest leaf node in the tree is 2, the lca of one node is itself.


Constraints:
    The number of nodes in the tree will be in the range [1, 1000].
    0 <= Node.val <= 1000
    The values of the nodes in the tree are unique.

*/

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
    class Solution_PostOrder {

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