package leetcode;

/*

You are climbing a staircase. It takes n steps to reach the top.

Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?


Example 1:
    Input: n = 2
    Output: 2
    Explanation: There are two ways to climb to the top.
    1. 1 step + 1 step
    2. 2 steps

Example 2:
    Input: n = 3
    Output: 3
    Explanation: There are three ways to climb to the top.
    1. 1 step + 1 step + 1 step
    2. 1 step + 2 steps
    3. 2 steps + 1 step


Constraints:
    1 <= n <= 45


*/
public class _0070_Climbing_Stairs {

    class Solution {
        public int climbStairs(int n) {
            int pre1 = 1;
            int pre2 = 0;
            int curr = 0;
            for (int i = 1; i <= n; ++i) {
                curr = pre1 + pre2;
                pre2 = pre1;
                pre1 = curr;
            }
            return curr;
        }
    }

}
