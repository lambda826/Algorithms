package leetcode.array;

/*
Description:
    A permutation of an array of integers is an arrangement of its members into a sequence or linear order.

    For example, for arr = [1,2,3], the following are all the permutations of arr: [1,2,3], [1,3,2], [2, 1, 3], [2, 3, 1], [3,1,2], [3,2,1].
    The next permutation of an array of integers is the next lexicographically greater permutation of its integer. More formally, if all the permutations of the array are sorted in one container according to their lexicographical order, then the next permutation of that array is the permutation that follows it in the sorted container. If such arrangement is not possible, the array must be rearranged as the lowest possible order (i.e., sorted in ascending order).

    For example, the next permutation of arr = [1,2,3] is [1,3,2].
    Similarly, the next permutation of arr = [2,3,1] is [3,1,2].
    While the next permutation of arr = [3,2,1] is [1,2,3] because [3,2,1] does not have a lexicographical larger rearrangement.
    Given an array of integers nums, find the next permutation of nums.

    The replacement must be in place and use only constant extra memory.


Examples:
    Example 1:
        Input: nums = [1,2,3]
        Output: [1,3,2]
        Explanation: The next permutation of [1,2,3] is [1,3,2].

    Example 2:
        Input: nums = [3,2,1]
        Output: [1,2,3]
        Explanation: Since [3,2,1] is the largest permutation, we return the smallest by reversing.

    Example 3:
        Input: nums = [1,1,5]
        Output: [1,5,1]
        Explanation: The next permutation of [1,1,5] is [1,5,1].


Constraints:
    1 <= nums.length <= 100
    0 <= nums[i] <= 100
*/
public class _0031_Next_Permutation {

    /**
     * <h2>31. Next Permutation — Greedy + Two-Pointer</h2>
     *
     * <h3>Goal/Problem summary</h3>
     * Rearrange {@code nums} into the lexicographically next greater permutation; if none exists,
     * rearrange to the smallest (ascending) permutation. Do it in-place with O(1) extra space.
     *
     * <h3>Constraints/Assumptions</h3>
     * <ul>
     *   <li>Duplicates allowed; array length {@code n >= 1}.</li>
     *   <li>Must modify in-place; O(1) extra memory.</li>
     * </ul>
     *
     * <h3>Approach</h3>
     * <ul>
     *   <li>Scan from right to find the rightmost pivot {@code i-1} with {@code nums[i-1] < nums[i]}.</li>
     *   <li>If pivot exists, from the right find the first {@code j} where {@code nums[j] > nums[i-1]}, then swap.</li>
     *   <li>Reverse the suffix {@code [i .. n-1]} to make it minimal (ascending).</li>
     * </ul>
     *
     * <h3>Algorithm</h3>
     * <ol>
     *   <li>{@code i = n - 1}; while {@code i > 0 && nums[i-1] >= nums[i]}: {@code i--}.</li>
     *   <li>If {@code i > 0}:
     *     <ol type="a">
     *       <li>{@code j = n - 1}; while {@code j >= i && nums[j] <= nums[i-1]}: {@code j--}.</li>
     *       <li>Swap {@code nums[i-1]} and {@code nums[j]}.</li>
     *     </ol>
     *   </li>
     *   <li>Reverse {@code [i, n-1]}.</li>
     * </ol>
     *
     * <h3>Complexity</h3>
     * <ul>
     *   <li>Time: O(n)</li>
     *   <li>Space: O(1)</li>
     * </ul>
     */
    static class Solution {
        public void nextPermutation(int[] nums) {
            int n = nums.length;

            int i = n - 1;
            while (i > 0 && nums[i - 1] >= nums[i]) {
                i--;
            }

            if (i > 0) {
                int j = n - 1;
                while (j >= i && nums[j] <= nums[i - 1]) {
                    j--;
                }
                swap(nums, i - 1, j);
            }
            reverse(nums, i, n - 1);
        }

        private void reverse(int[] nums, int left, int right) {
            int l = left;
            int r = right;
            while (l < r) {
                swap(nums, l, r);
                l++;
                r--;
            }
        }

        private void swap(int[] nums, int a, int b) {
            int t = nums[a];
            nums[a] = nums[b];
            nums[b] = t;
        }
    }
}
