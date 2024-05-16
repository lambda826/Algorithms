package questions._10_tree.bst;

import common.TreeNode;

/*

Given an integer array nums where the elements are sorted in ascending order, convert it to a height-balanced binary search tree.


Example 1:
    Input: nums = [-10,-3,0,5,9]
    Output: [0,-3,9,-10,null,5]
    Explanation: [0,-10,5,null,-3,null,9] is also accepted:

Example 2:
    Input: nums = [1,3]
    Output: [3,1]
    Explanation: [1,null,3] and [3,1] are both height-balanced BSTs.


Constraints:
    1 <= nums.length <= 10^4
    -10^4 <= nums[i] <= 10^4
    nums is sorted in a strictly increasing order.

*/
class _0108_Convert_Sorted_Array_to_Binary_Search_Tree {

    class Solution {
        public TreeNode sortedArrayToBST(int[] nums) {
            return buildTree(nums, 0, nums.length - 1);
        }

        private TreeNode buildTree(int[] nums, int from, int to) {
            if (from > to) {
                return null;
            } else {
                int mid = (from + to) / 2;
                TreeNode t = new TreeNode(nums[mid]);
                t.left = buildTree(nums, from, mid - 1);
                t.right = buildTree(nums, mid + 1, to);
                return t;
            }
        }
    }
}