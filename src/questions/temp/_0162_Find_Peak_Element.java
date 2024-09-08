/**
 * @author Yunxiang He
 * @date Jan 17, 2018 6:51:50 PM
 */

package questions.temp;

/*

A peak element is an element that is greater than its neighbors.
Given an input array nums, where nums[i] ≠ nums[i+1], find a peak element and return its index.
The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.
You may imagine that nums[-1] = nums[n] = -∞.

Example 1:
    Input: 
        nums = [1,2,3,1]
    Output: 
        2
    Explanation: 
        3 is a peak element and your function should return the index number 2.

Example 2:
    Input: 
        nums = [1,2,1,3,5,6,4]
    Output: 
        1 or 5 
    Explanation: 
        Your function can return either index number 1 where the peak element is 2, 
        or index number 5 where the peak element is 6.


Note:
    Your solution should be in logarithmic complexity.

*/

public class _0162_Find_Peak_Element {
    public static void main(String[] args) {
        _0162_Find_Peak_Element test = new _0162_Find_Peak_Element();
        int[] nums = { 2, 1, 2 };
        System.out.println(test.findPeakElement_BinarySerach1(nums));
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int findPeakElement_BinarySerach1(int[] nums) {
        int lo = 0;
        int hi = nums.length - 1;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] < nums[mid + 1]) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        return lo;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int findPeakElement_BinarySerach2(int[] nums) {
        int lo = 0;
        int hi = nums.length - 1;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if ((mid >= nums.length - 1 || nums[mid] > nums[mid + 1]) && (mid <= 0 || nums[mid] > nums[mid - 1])) {
                return mid;
            } else if (mid <= 0 || nums[mid] > nums[mid - 1]) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return lo;
    }
}
