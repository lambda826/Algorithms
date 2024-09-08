/**
 * @author: Yunxiang He
 * @date : 2018-06-26 02:40
 */

package questions.temp;

import java.util.Arrays;

/*

Given an array, rotate the array to the right by k steps, where k is non-negative.

Example 1:
Input: [1,2,3,4,5,6,7] and k = 3
Output: [5,6,7,1,2,3,4]
Explanation:
rotate 1 steps to the right: [7,1,2,3,4,5,6]
rotate 2 steps to the right: [6,7,1,2,3,4,5]
rotate 3 steps to the right: [5,6,7,1,2,3,4]

Example 2:
Input: [-1,-100,3,99] and k = 2
Output: [3,99,-1,-100]
Explanation: 
rotate 1 steps to the right: [99,-1,-100,3]
rotate 2 steps to the right: [3,99,-1,-100]

Note:
Try to come up as many solutions as you can, there are at least 3 different ways to solve this problem.
Could you do it in-place with O(1) extra space?

*/

public class __0189_Rotate_Array {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // 0th        : newIndex = oldIndex + k - lenghth
    // 1st reverse: oldIndex + 1_index = length
    // 2nd reverse: newIndex + length - oldIndex = k, which equals to 0th
    public void rotate_Reverse(int[] nums, int k) {
        k %= nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }

    public void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int[] rotate(int[] nums, int k) {
        k = k % nums.length;
        if (k != 0 && nums.length != 1) {
            int start = 0;
            int pre = nums[start];
            for (int i = 1; i < nums.length + 1; i++) {
                int currIndex = (i * k + start) % nums.length;
                int temp = nums[currIndex];
                nums[currIndex] = pre;
                if (currIndex == start) {
                    start++;
                    pre = nums[start];
                } else {
                    pre = temp;
                }
            }
        }
        return nums;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new __0189_Rotate_Array().rotate(new int[] { 0, 1, 2, 3, 4, 5 }, 2)));
    }
}
