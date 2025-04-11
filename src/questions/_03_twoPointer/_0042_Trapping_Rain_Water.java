package questions._03_twoPointer;

import java.util.ArrayDeque;
import java.util.Deque;

/*

Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it can trap after raining.


Example 1:
    Input:
        height = [0,1,0,2,1,0,1,3,2,1,2,1]
    Output:
        6
    Explanation:
        The above elevation map (black section) is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped.

Example 2:
    Input:
        height = [4,2,0,3,2,5]
    Output:
        9

Constraints:
    n == height.length
    1 <= n <= 2 * 10^4
    0 <= height[i] <= 10^5

*/
public class _0042_Trapping_Rain_Water {

    /// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // monotonous stack
    public int trap2(int[] height) {
        Deque<Integer> stack = new ArrayDeque<>();
        int amt = 0;
        int i = 0;
        while (i < height.length) {
            if (stack.isEmpty() || height[i] < height[stack.peekLast()]) {
                stack.offerLast(i++);
            } else {
                int h = height[stack.pollLast()];
                if (!stack.isEmpty()) {
                    amt += (Math.min(height[i], height[stack.peekLast()]) - h) * (i - stack.peekLast() - 1);
                }
            }
        }
        return amt;
    }

    /**
     * Key Idea:
     * At any point, consider the pointers left and right.
     * If height[left] < height[right],
     * we know that there's a wall height[right] (or potentially an even higher one further right, stored in right_max) that is taller than height[left].
     * This means the amount of water trapped at the left pointer position is limited by the maximum height encountered so far from the left (left_max).
     * We don't need to know the absolute max_right for the entire array yet,
     * because the water level at left is capped by the shorter of the left/right boundaries relevant to it.
     */
    class Solution {

        public int trap(int[] height) {
            int left = 0;
            int right = height.length - 1;
            int leftMax = 0;
            int rightMax = 0;
            int totalWater = 0;

            while (left < right) {
                // Process based on the shorter potential wall height
                if (height[left] < height[right]) {
                    // Left side is potentially the limiting wall
                    if (height[left] >= leftMax) {
                        // Found a new potential left wall, update max
                        leftMax = height[left];
                    } else {
                        // Current bar is shorter than leftMax, can trap water
                        // Water level is determined by leftMax because we know
                        // height[right] (or rightMax) is >= height[left], and
                        // leftMax <= height[right] implicitly.
                        totalWater += leftMax - height[left];
                    }
                    left++; // Move left pointer inwards
                } else {
                    // Right side is potentially the limiting wall (or equal)
                    if (height[right] >= rightMax) {
                        // Found a new potential right wall, update max
                        rightMax = height[right];
                    } else {
                        // Current bar is shorter than rightMax, can trap water
                        // Water level is determined by rightMax
                        totalWater += rightMax - height[right];
                    }
                    right--; // Move right pointer inwards
                }
            }
            return totalWater;
        }
    }
}
