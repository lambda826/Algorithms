/**
 *  @author Yunxiang He
 *  @date 01/29/2018
 */

package coding.temp;

import common.TreeNode;

/*

Given an array where elements are sorted in ascending order, convert it to a height balanced BST.
For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.


Example:
    Given the sorted array: [-10,-3,0,5,9],
    One possible answer is: [0,-3,9,-10,null,5], which represents the following height balanced BST:
    
          0
         / \
       -3   9
       /   /
     -10  5

*/

public class _0108_Convert_Sorted_Array_to_Binary_Search_Tree {

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public TreeNode sortedArrayToBST(int[] nums) {
        return build(nums, 0, nums.length - 1);
    }

    private TreeNode build(int[] nums, int l, int r) {
        if (l <= r) {
            int mid = l + (r - l) / 2;
            TreeNode root = new TreeNode(nums[mid]);
            root.left = build(nums, l, mid - 1);
            root.right = build(nums, mid + 1, r);
            return root;
        } else {
            return null;
        }
    }
}
