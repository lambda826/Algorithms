package coding.leetcode._03_twoPointer;

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

    public static void main(String[] args) {
        _0042_Trapping_Rain_Water test = new _0042_Trapping_Rain_Water();
        test.trap2(new int[] { 0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1 });
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Two Pointers:
    // Use two pointers to point left and right point;
    // In each iteration, move the smaller one to which the pointer points,
    //      because the smaller one determines how much water it can trap at most;
    // When the next point has a smaller value then the leftMax or rightMax, it means the water can be trapped.
    class Solution_TwoPointer {
        public int trap(int[] height) {
            int left = 0;
            int right = height.length - 1;
            int leftMax = height[left];
            int rightMax = height[right];
            int sum = 0;
            while (left < right) {
                if (height[left] < height[right]) {
                    if (height[left] < leftMax) {
                        sum += leftMax - height[left];
                    } else {
                        leftMax = height[left];
                    }
                    ++left;
                } else {
                    if (height[right] < rightMax) {
                        sum += rightMax - height[right];
                    } else {
                        rightMax = height[right];
                    }
                    --right;
                }
            }
            return sum;
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
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
}
