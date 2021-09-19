/**
 * @author Yunxiang He
 * @date 01/28/2018
 */

package coding.leetcode._10_tree.lowest_common_ancesstor;

import common.TreeNode;

/*

Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.


Given the following binary tree:  root = [3,5,1,6,2,0,8,null,null,7,4]
        _______3______
       /              \
    ___5__          ___1__
   /      \        /      \
   6      _2       0       8
         /  \
         7   4


Example 1:
    Input:
        root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
    Output:
        3
    Explanation:
        The LCA of of nodes 5 and 1 is 3.

Example 2:
    Input:
        root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
    Output:
        5
    Explanation:
        The LCA of nodes 5 and 4 is 5, since a node can be a descendant of itself according to the LCA definition.


Note:
    All of the nodes' values will be unique.
    p and q are different and both values will exist in the binary tree.

*/

public class _0236_Lowest_Common_Ancestor_of_a_Binary_Tree {


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // case 1: p,q reside on the left and right of the root
    //         root is the LCA
    // case 2: p,q reside on the left or right of the root
    //         LCA reside on the left or right of the root
    public class Solution_PostOrder {

        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            if (root == null || root.val == p.val || root.val == q.val) {
                return root;
            } else {
                TreeNode left = lowestCommonAncestor(root.left, p, q);
                TreeNode right = lowestCommonAncestor(root.right, p, q);
                if (left != null && right != null) {
                    return root;
                } else if (left != null) {
                    return left;
                } else {
                    return right;
                }
            }
        }
    }
}
