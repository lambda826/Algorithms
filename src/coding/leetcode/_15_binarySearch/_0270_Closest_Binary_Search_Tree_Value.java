/**
 * @author Yunxiang He
 * @date 01/17/2018
 */

package coding.leetcode._15_binarySearch;

import common.TreeNode;

/*

Given a non-empty binary search tree and a target value, find the value in the BST that is closest to the target.


Example:
    Input: root = [4,2,5,1,3], target = 3.714286
    
        4
       / \
      2   5
     / \
    1   3
    Output: 4


Note:
    Given target value is a floating point.
    You are guaranteed to have only one unique value in the BST that is closest to the target.

*/

public class _0270_Closest_Binary_Search_Tree_Value {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int closestValue(TreeNode root, double target) {
        double min = Double.MAX_VALUE;
        int res = root.val;
        while (root != null) {
            double temp = Math.abs(root.val - target);
            if (min > temp) {
                min = temp;
                res = root.val;
            }
            if (root.val > target) {
                root = root.left;
            } else {
                root = root.right;
            }
        }
        return res;
    }
}
