/**
 *  @author Yunxiang He
 *  @date 01/28/2018
 */

package coding.temp;

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
    Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
    Output: 3
    Explanation: The LCA of of nodes 5 and 1 is 3.

Example 2:
    Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
    Output: 5
    Explanation: The LCA of nodes 5 and 4 is 5, since a node can be a descendant of itself according to the LCA definition.


Note:
    All of the nodes' values will be unique.
    p and q are different and both values will exist in the binary tree.

*/

public class _0236_Lowest_Common_Ancestor_of_a_Binary_Tree {

    public static void main(String[] args) {
        _0236_Lowest_Common_Ancestor_of_a_Binary_Tree test = new _0236_Lowest_Common_Ancestor_of_a_Binary_Tree();
        TreeNode node3 = new TreeNode(3);
        TreeNode node5 = new TreeNode(5);
        TreeNode node1 = new TreeNode(1);
        TreeNode node6 = new TreeNode(6);
        TreeNode node2 = new TreeNode(2);
        TreeNode node0 = new TreeNode(0);
        TreeNode node8 = new TreeNode(8);
        TreeNode node7 = new TreeNode(7);
        TreeNode node4 = new TreeNode(4);

        node3.left = node5;
        node3.right = node1;
        node5.left = node6;
        node5.right = node2;
        node1.left = node0;
        node1.right = node8;
        node2.left = node7;
        node2.right = node4;

        TreeNode p = node5;
        TreeNode q = node4;
        TreeNode res = test.lowestCommonAncestor(node3, p, q);
        System.out.println(res.val);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // D&C
    // case 1: p,q reside on the left and right of the root
    //         root is the LCA
    // case 2: p,q reside on the left or right of the root
    //         LCA reside on the left or right of the root
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root.val == p.val || root.val == q.val) {
            return root;
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
