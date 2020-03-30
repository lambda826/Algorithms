package coding.leetcode;

import common.TreeNode;

/*

Given a binary tree, determine if it is height-balanced.
For this problem, a height-balanced binary tree is defined as:
    a binary tree in which the depth of the two subtrees of every node never differ by more than 1.


Example 1:
    Given the following tree [3,9,20,null,null,15,7]:
    
        3
       / \
      9  20
        /  \
       15   7
    Return true.

Example 2:
    Given the following tree [1,2,2,3,3,null,null,4,4]:
    
           1
          / \
         2   2
        / \
       3   3
      / \
     4   4
    Return false.


History:
    3/29/2020

 */

public class _0110_Balanced_Binary_Tree {

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private boolean isBalanced = true;

    public boolean isBalanced(TreeNode root) {
        depth(root);
        return isBalanced;
    }

    private int depth(TreeNode node) {
        if (isBalanced) {
            if (node == null) {
                return 0;
            } else {
                int leftH = depth(node.left);
                int rightH = depth(node.right);
                isBalanced = isBalanced && Math.abs(leftH - rightH) < 2;
                return 1 + Math.max(leftH, rightH);
            }
        }
        return -1;
    }
}
