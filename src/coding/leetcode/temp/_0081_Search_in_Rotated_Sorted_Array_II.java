/**
 *  @author Yunxiang He
 *  @date Jan 22, 2018 3:42:32 PM
 */

package coding.leetcode.temp;

/*

Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

(i.e., [0,0,1,2,2,5,6] might become [2,5,6,0,0,1,2]).

You are given a target value to search. If found in the array return true, otherwise return false.

Example 1:
Input: nums = [2,5,6,0,0,1,2], target = 0
Output: true

Example 2:
Input: nums = [2,5,6,0,0,1,2], target = 3
Output: false

 */

public class _0081_Search_in_Rotated_Sorted_Array_II {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public boolean search_BinarySearch(int[] nums, int target) {
        int lo = 0;
        int hi = nums.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] == target) {
                return true;
            } else if (nums[mid] < nums[hi]) {
                if (nums[mid] < target && nums[hi] >= target) {
                    lo = mid + 1;
                } else {
                    hi = mid - 1;
                }
            } else if (nums[mid] > nums[hi]) {
                if (nums[lo] <= target && nums[mid] > target) {
                    hi = mid - 1;
                } else {
                    lo = mid + 1;
                }
            } else {
                hi--;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] nums = { 3, 1, 1 };
        int target = 3;
        _0081_Search_in_Rotated_Sorted_Array_II test = new _0081_Search_in_Rotated_Sorted_Array_II();
        boolean result = test.search_BinarySearch(nums, target);
        System.out.println(result);
    }
}
