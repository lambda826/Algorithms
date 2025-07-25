package leetcode.tree.dfs.lowest_common_ancesstor;

import common.TreeNode;

/*

Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.

According to the definition of LCA on Wikipedia:
“The lowest common ancestor is defined between two nodes p and q as the lowest node in T that has both p and q as descendants
(where we allow a node to be a descendant of itself).”


Example 1:
    Input:
        root = [3,5,1,6,2,0,8,null,null,7,4],
        p = 5,
        q = 1
    Output:
        3
    Explanation:
        The LCA of nodes 5 and 1 is 3.

Example 2:
    Input:
        root = [3,5,1,6,2,0,8,null,null,7,4],
        p = 5,
        q = 4
    Output:
        5
    Explanation:
        The LCA of nodes 5 and 4 is 5, since a node can be a descendant of itself according to the LCA definition.

Example 3:
    Input:
        root = [1,2],
        p = 1,
        q = 2
    Output:
        1


Constraints:
    The number of nodes in the tree is in the range [2, 10^5].
    -10^5 <= Node.val <= 10^5
    All Node.val are unique.
    p != q
    p and q will exist in the tree.

*/
public class _0236_Lowest_Common_Ancestor_of_a_Binary_Tree {

    class Solution {
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            if (root == null) {
                return null;
            } else if (root == p) {
                return p;
            } else if (root == q) {
                return q;
            } else {
                TreeNode left = lowestCommonAncestor(root.left, p, q);
                TreeNode right = lowestCommonAncestor(root.right, p, q);
                if (left == null) {
                    return right;
                } else if (right == null) {
                    return left;
                } else {
                    return root;
                }
            }
        }
    }
}