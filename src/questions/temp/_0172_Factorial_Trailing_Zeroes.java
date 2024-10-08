/**
 * @author: Yunxiang He
 * @date : 2018-07-17 04:22
 */

package questions.temp;

/*

Given an integer n, return the number of trailing zeroes in n!.

Example 1:
Input: 3
Output: 0
Explanation: 3! = 6, no trailing zero.

Example 2:
Input: 5
Output: 1
Explanation: 5! = 120, one trailing zero.

Note: Your solution should be in logarithmic time complexity.

*/

public class _0172_Factorial_Trailing_Zeroes {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int trailingZeroes(int n) {
        int count = 0;
        while (n > 4) {
            count += n / 5;
            n /= 5;
        }
        return count;
    }
}
