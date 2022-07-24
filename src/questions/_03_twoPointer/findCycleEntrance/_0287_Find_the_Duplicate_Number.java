package questions._03_twoPointer.findCycleEntrance;

/*

Given an array of integers nums containing n + 1 integers where each integer is in the range [1, n] inclusive.
There is only one repeated number in nums, return this repeated number.
You must solve the problem without modifying the array nums and uses only constant extra space.


Example 1:
    Input: nums = [1,3,4,2,2]
    Output: 2
Example 2:
    Input: nums = [3,1,3,4,2]
    Output: 3


Constraints:
    1 <= n <= 10^5
    nums.length == n + 1
    1 <= nums[i] <= n
    All the integers in nums appear only once except for precisely one integer which appears two or more times.


Follow up:
    How can we prove that at least one duplicate number must exist in nums?
    Can you solve the problem in linear runtime complexity?

*/
public class _0287_Find_the_Duplicate_Number {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Find entrance of the cycle
    class Solution {
        public int findDuplicate(int[] nums) {
            int res = 0;
            int slow = nums[0];
            int fast = nums[nums[0]];
            while (slow != fast) {
                slow = nums[slow];
                fast = nums[nums[fast]];
            }

            while (slow != res) {
                slow = nums[slow];
                res = nums[res];
            }

            return res;
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Find the element that is greater than the target
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

}
