package coding.leetcode._09_dynamicProgramming.oneDimension.optimized;

/*

You are climbing a stair case. 
It takes n steps to reach to the top.
Each time you can either climb 1 or 2 steps. 
In how many distinct ways can you climb to the top?


Example 1:
    Input: 2
    Output:  2
    Explanation:  There are two ways to climb to the top.
    1. 1 step + 1 step
    2. 2 steps

Example 2:
    Input: 3
    Output:  3
    Explanation:  There are three ways to climb to the top.
    1. 1 step + 1 step + 1 step
    2. 1 step + 2 steps
    3. 2 steps + 1 step


Note:
    Given n will be a positive integer.


History:
    4/7/2020

*/

public class _0070_Climbing_Stairs {


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int climbStairs(int n) {
        int[] steps = new int[n + 2];
        steps[1] = 1;
        steps[2] = 2;
        for (int i = 3; i <= n; ++i) {
            steps[i] = steps[i - 1] + steps[i - 2];
        }
        return steps[n];
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int climbStairs_optimized(int n) {
        if (n < 3) {
            return n;
        } else {
            int step1 = 1;
            int step2 = 2;
            int step = step1 + step2;
            for (int i = 3; i <= n; ++i) {
                step = step1 + step2;
                step1 = step2;
                step2 = step;
            }
            return step;
        }
    }
}
