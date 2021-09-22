/**
 *  @author Yunxiang He
 *  @date 02/23/2019
 */

package coding.leetcode.temp;

import common.TreeNode;

/*

Given a binary tree, count the number of uni-value subtrees.
A Uni-value subtree means all nodes of the subtree have the same value.


Example :
    Input:  root = [5,1,5,5,5,null,5]
    
                  5
                 / \
                1   5
               / \   \
              5   5   5
    Output: 4

*/

public class _0250_Count_Univalue_Subtrees {
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private int count;

    public int countUnivalSubtrees(TreeNode root) {
        if (root != null) {
            isUnival(root);
        }
        return count;
    }

    private boolean isUnival(TreeNode node) {
        if (node.left == null && node.right == null) {
            count++;
            return true;
        }
        boolean left = node.left == null ? true : isUnival(node.left) && node.val == node.left.val;
        boolean right = node.right == null ? true : isUnival(node.right) && node.val == node.right.val;
        if (left && right) {
            count++;
            return true;
        } else {
            return false;
        }
    }
}
