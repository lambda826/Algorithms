package leetcode.monotonic_queue;

import annotations.Companies;
import annotations.Difficulty;
import annotations.Frequency;

import java.util.ArrayDeque;
import java.util.Deque;

import static annotations.constants.CompaniesType.GOOGLE;
import static annotations.constants.DifficultyLevel.HARD;
import static annotations.constants.FrequencyLevel.VERY_HIGH;

/*

You are given an array of integers nums, there is a sliding window of size k which is moving from the very left of the array to the very right.
You can only see the k numbers in the window. Each time the sliding window moves right by one position.

Return the max sliding window.

Example 1:
    Input:
        nums = [1,3,-1,-3,5,3,6,7], k = 3
    Output:
        [3,3,5,5,6,7]
    Explanation:
        Window position                Max
        ---------------               -----
        [1  3  -1] -3  5  3  6  7       3
         1 [3  -1  -3] 5  3  6  7       3
         1  3 [-1  -3  5] 3  6  7       5
         1  3  -1 [-3  5  3] 6  7       5
         1  3  -1  -3 [5  3  6] 7       6
         1  3  -1  -3  5 [3  6  7]      7

Example 2:
    Input:
        nums = [1], k = 1
    Output:
        [1]

Constraints:
    1 <= nums.length <= 10^5
    -10^4 <= nums[i] <= 10^4
    1 <= k <= nums.length
*/
@Companies(GOOGLE)
@Difficulty(HARD)
@Frequency(VERY_HIGH)
public class _0239_Sliding_Window_Maximum {

    class Solution {
        public int[] maxSlidingWindow(int[] nums, int k) {
            int[] res = new int[nums.length - k + 1];
            // The deque stores indices of elements in nums, maintaining a monotonic decreasing order of values.
            // This ensures the maximum value in the current window is always at the front of the deque.
            Deque<Integer> deque = new ArrayDeque<>();
            int left = 0;
            int right = 0;

            while (right < nums.length) {
                // Add the current index to the deque, maintaining the monotonic decreasing order.
                // Remove indices from the back of the deque if they point to values smaller than nums[right].
                while (!deque.isEmpty() && nums[deque.peekLast()] < nums[right]) {
                    deque.pollLast();
                }
                deque.offerLast(right++);

                // Once the window size is met (distance between right and left equals k), record the maximum value.
                if (right - left == k) {
                    // The maximum value in the current window is at the front of the deque.
                    res[left] = nums[deque.peekFirst()];

                    // Remove the index from the front of the deque if it is out of the current window.
                    if (deque.peekFirst() == left) {
                        deque.pollFirst();
                    }
                    left++; // Slide the window forward.
                }
            }

            return res;
        }
    }
}
