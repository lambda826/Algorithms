/**
 *  @author: Yunxiang He
 *  @date  : 2018-07-17 05:26
 */

package coding.leetcode.temp;

/*

Given a non-negative integer c, your task is to decide whether there're two integers a and b such that a^2 + b^2 = c.

Example 1:
Input: 5
Output: True
Explanation: 1 * 1 + 2 * 2 = 5

Example 2:
Input: 3
Output: False

*/

public class _0633_Sum_of_Square_Numbers {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public boolean judgeSquareSum(int c) {
        int left = 0;
        int right = (int) Math.sqrt(c);
        while (left <= right) {
            if (left * left + right * right == c) {
                return true;
            }
            left++;
            right = (int) Math.sqrt(c - left * left);
        }
        return false;
    }
}
