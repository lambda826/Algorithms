package coding.leetcode._10_tree.lowest_common_ancesstor;

import common.TreeNode;

/*

Given a binary search tree (BST), find the lowest common ancestor (LCA) of two given nodes in the BST.

According to the definition of LCA on Wikipedia:
    “The lowest common ancestor is defined between two nodes p and q as the lowest node in T that has both p and q as descendants
    (where we allow a node to be a descendant of itself).”


Example 1:
    Input:
        root = [6,2,8,0,4,7,9,null,null,3,5],
        p = 2,
        q = 8
    Output:
        6
    Explanation:
        The LCA of nodes 2 and 8 is 6.

Example 2:
    Input:
        root = [6,2,8,0,4,7,9,null,null,3,5],
        p = 2,
        q = 4
    Output:
        2
    Explanation:
        The LCA of nodes 2 and 4 is 2, since a node can be a descendant of itself according to the LCA definition.

Example 3:
    Input:
        root = [2,1],
        p = 2,
        q = 1
    Output:
        2


Constraints:
    The number of nodes in the tree is in the range [2, 100000].
    -1000000000 <= Node.val <= 1000000000
    All Node.val are unique.
    p != q
    p and q will exist in the BST.

*/

public class _0235_Lowest_Common_Ancestor_of_a_Binary_Search_Tree {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Utilize the property of BST
    // 1. If current node is greater than both p and q, the LCA is on the left subtree;
    // 2. If current node is smaller than both p and q, the LCA is on the right subtree;
    // 3. Otherwise return current node.
    class Solution {

        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            if (root.val > p.val && root.val > q.val) {
                return lowestCommonAncestor(root.left, p, q);
            } else if (root.val < p.val && root.val < q.val) {
                return lowestCommonAncestor(root.right, p, q);
            } else {
                return root;
            }
        }
    }

}