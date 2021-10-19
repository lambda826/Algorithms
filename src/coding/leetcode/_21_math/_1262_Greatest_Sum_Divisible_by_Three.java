package coding.leetcode._21_math;

/*

Given an array nums of integers, we need to find the maximum possible sum of elements of the array such that it is divisible by three.



Example 1:

Input: nums = [3,6,5,1,8]
Output: 18
Explanation: Pick numbers 3, 6, 1 and 8 their sum is 18 (maximum sum divisible by 3).
Example 2:

Input: nums = [4]
Output: 0
Explanation: Since 4 is not divisible by 3, do not pick any number.
Example 3:

Input: nums = [1,2,3,4,4]
Output: 12
Explanation: Pick numbers 1, 3, 4 and 4 their sum is 12 (maximum sum divisible by 3).


Constraints:

1 <= nums.length <= 4 * 10^4
1 <= nums[i] <= 10^4

 */


public class _1262_Greatest_Sum_Divisible_by_Three {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int maxSumDivThree(int[] nums) {
        int sum = 0;
        int min1a = 20000;
        int min1b = 20000;
        int min2a = 20000;
        int min2b = 20000;
        for (int num : nums) {
            sum += num;
            if (num % 3 == 1) {
                if (min1a > num) {
                    min1b = min1a;
                    min1a = num;
                } else if (min1b > num) {
                    min1b = num;
                }
            } else if (num % 3 == 2) {
                if (min2a > num) {
                    min2b = min2a;
                    min2a = num;
                } else if (min2b > num) {
                    min2b = num;
                }
            }
        }

        if (sum % 3 == 2) {
            if (min1a + min1b > min2a || min1b == 20000) {
                sum -= min2a;
            } else {
                sum = sum - min1a - min1b;
            }
        } else if (sum % 3 == 1) {
            if (min2a + min2b > min1a || min2b == 20000) {
                sum -= min1a;
            } else {
                sum = sum - min2a - min2b;
            }
        }
        return sum;
    }
}
