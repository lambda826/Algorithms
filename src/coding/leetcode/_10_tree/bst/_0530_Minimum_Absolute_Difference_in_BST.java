/**
 *  @author: Yunxiang He
 *  @date  : 2018-06-27
 */

package coding.leetcode._10_tree.bst;

import common.TreeNode;

/*

Given a binary search tree with non-negative values, find the minimum absolute difference between values of any two nodes.


Example:
    Input:
       1
        \
         3
        /
       2
    Output:
    1
    Explanation:
    The minimum absolute difference is 1, which is the difference between 2 and 1 (or between 2 and 3).
 

Note: 
    There are at least two nodes in this BST.

*/

public class _0530_Minimum_Absolute_Difference_in_BST {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private int min = Integer.MAX_VALUE;
    private Integer pre = null;

    public int getMinimumDifference_InOrder(TreeNode root) {
        if (root != null) {
            getMinimumDifference_InOrder(root.left);
            /////////////////////////////////////////
            // visit
            if (pre != null) {
                min = Math.min(min, Math.abs(root.val - pre));
            }
            pre = root.val;
            /////////////////////////////////////////
            getMinimumDifference_InOrder(root.right);
        }
        return min;
    }
}
