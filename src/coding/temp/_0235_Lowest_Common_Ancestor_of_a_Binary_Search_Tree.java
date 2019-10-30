/**
 *  @author Yunxiang He
 *  @date 01/28/2018
 */

package coding.temp;

import common.TreeNode;

/*

Given a binary search tree (BST), find the lowest common ancestor (LCA) of two given nodes in the BST.


Given binary search tree:  root = [6,2,8,0,4,7,9,null,null,3,5]
        _______6______
       /              \
    ___2__          ___8__
   /      \        /      \
   0      _4       7       9
         /  \
         3   5

Example 1:
    Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
    Output: 6
    Explanation: The LCA of nodes 2 and 8 is 6.

Example 2:
    Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
    Output: 2
    Explanation: The LCA of nodes 2 and 4 is 2, since a node can be a descendant of itself 
                 according to the LCA definition.


Note:
    All of the nodes' values will be unique.
    p and q are different and both values will exist in the BST.

*/

public class _0235_Lowest_Common_Ancestor_of_a_Binary_Search_Tree {

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
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
