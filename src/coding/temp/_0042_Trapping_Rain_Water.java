/**
 *  @author Yunxiang He
 *  @date 04/10/2019
 */

package coding.temp;

import java.util.ArrayDeque;
import java.util.Deque;

/*

Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining.


Example:
    Input: [0,1,0,2,1,0,1,3,2,1,2,1]
    Output: 6

*/

public class _0042_Trapping_Rain_Water {

    public static void main(String[] args) {
        _0042_Trapping_Rain_Water test = new _0042_Trapping_Rain_Water();
        test.trap2(new int[] { 0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1 });
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // two pointers
    public int trap(int[] height) {
        int l = 0;
        int r = height.length - 1;
        int res = 0;
        int l_max = 0;
        int r_max = 0;
        while (l < r) {
            if (height[l] < height[r]) { // check left
                if (height[l] >= l_max) {
                    l_max = height[l];
                } else {
                    res += (l_max - height[l]);
                }
                ++l;
            } else { // check right
                if (height[r] >= r_max) {
                    r_max = height[r];
                } else {
                    res += (r_max - height[r]);
                }
                --r;
            }
        }
        return res;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
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
