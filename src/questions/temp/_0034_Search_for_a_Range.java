/**
 * @author Yunxiang He
 * @date Jan 21, 2018 3:31:32 PM
 */

package questions.temp;

/*

Given an array of integers sorted in ascending order, find the starting and ending position of a given target value.

Your algorithm's runtime complexity must be in the order of O(log n).

If the target is not found in the array, return [-1, -1].

For example,
Given [5, 7, 7, 8, 8, 10] and target value 8,
return [3, 4].

 */

public class _0034_Search_for_a_Range {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Find the start and end of this range
    public int[] searchRange_BinarySearch(int[] nums, int target) {
        // Find the end
        int lo = 0;
        int hi = nums.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] <= target) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        // Find the start
        if (hi >= 0 && nums[hi] == target) {
            int _lo = 0;
            int _hi = hi;
            while (_lo < _hi) {
                int mid = _lo + (_hi - _lo) / 2;
                if (nums[mid] < target) {
                    _lo = mid + 1;
                } else {
                    _hi = mid;
                }
            }
            return new int[] { _lo, hi };
        } else {
            return new int[] { -1, -1 };
        }
    }

    public static void main(String[] args) {
        _0034_Search_for_a_Range test = new _0034_Search_for_a_Range();
        test.searchRange_BinarySearch(new int[] { 2, 2 }, 2);
    }
}
