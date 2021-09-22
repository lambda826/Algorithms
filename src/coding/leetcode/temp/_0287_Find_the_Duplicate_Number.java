/**
 *  @author Yunxiang He
 *  @date Jan 4, 2018 12:37:26 AM
 */

package coding.leetcode.temp;

/*

Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive), prove that at least one duplicate number must exist. 
Assume that there is only one duplicate number, find the duplicate one.


Note:
    You must not modify the array (assume the array is read only).
    You must use only constant, O(1) extra space.
    Your runtime complexity should be less than O(n ^ 2).
    There is only one duplicate number in the array, but it could be repeated more than once.

*/

public class _0287_Find_the_Duplicate_Number {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // The problem can be reduced to finding the entrance of the circle (the entrance is the duplicate elements)
    // Two pointers one for slow and one for fast
    // If the two pointers start in the circle, then they will alwasy meet at the start points because the speed of the fast is twice of slow's
    // If the tow pointers start outside circle, then
    // Dis_slow = outside_circle + inside_circle_slow
    // Dis_fast = outside_circle + inside_circle_slow * 2 + inside_circle_slow_rest
    // Because Dis_fast = 2 * Dis_slow
    // outside_circle + inside_circle_slow * 2 + inside_circle_slow_rest = (outside_circle + inside_circle_slow) * 2
    // inside_circle_slow_rest = outside_circle
    // Then we set a new pointer to start, and it will meet the slow at the entrance of circle
    public int findDuplicate_TwoPointers(int[] nums) {
        int res = 0;
        int slow = 0;
        int fast = 0;
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);

        while (res != slow) {
            res = nums[res];
            slow = nums[slow];
        }
        return res;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Find the element that is greater then the target
    // Where the element is the # of nums that are less than or equal to mid
    // The target is mid
    public int findDuplicate_BinarySearch(int[] nums) {
        int lo = 1;
        int hi = nums.length - 1;
        while (lo < hi) {
            int count = 0;
            int mid = lo + (hi - lo) / 2;
            for (int num : nums) {
                if (num <= mid) {
                    count++;
                }
            }
            // Find the smallest element that is greater than the target
            if (count <= mid) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        return lo;
    }

    public static void main(String[] args) {
        int[] nums = new int[] { 1, 1, 2, 3, 5, 6, 7, 8, 9, 9 };
        _0287_Find_the_Duplicate_Number test = new _0287_Find_the_Duplicate_Number();
        System.out.println(test.findDuplicate_BinarySearch(nums));
    }
}
