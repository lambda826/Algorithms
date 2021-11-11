/**
 *  @author Yunxiang He
 *  @date Jan 14, 2018 6:27:55 PM
 */

package questions.temp;

/*

Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

You are given a target value to search. 
If found in the array return its index, otherwise return -1.

You may assume no duplicate exists in the array.

 */

public class _0033_Search_in_Rotated_Sorted_Array {

    public static void main(String[] args) {
        _0033_Search_in_Rotated_Sorted_Array test = new _0033_Search_in_Rotated_Sorted_Array();
        int[] nums = { 4, 5, 6, 7, 0, 1, 2 };
        System.out.println(test.search(nums, 0));
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int search(int[] nums, int target) {
        int lo = 0;
        int hi = nums.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[lo] < nums[mid]) { // 如果lo > mid, 则 lo 到 mid 是单调递增到
                if (target >= nums[lo] && target < nums[mid]) {
                    hi = mid - 1;
                } else {
                    lo = mid + 1;
                }
            } else { // 如果lo < mid， 则 mid 到 hi 是单调递增到
                if (target > nums[mid] && target <= nums[hi]) {
                    lo = mid + 1;
                } else {
                    hi = mid - 1;
                }
            }
        }
        return -1;
    }

}
