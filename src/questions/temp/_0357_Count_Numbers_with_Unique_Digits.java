/**
 * @author Yunxiang He
 * @date Jan 29, 2018 9:55:28 PM
 */

package questions.temp;

/*

Given a non-negative integer n, count all numbers with unique digits, x, where 0 ≤ x < 10 ^ n.


Example:
Input: 2
Output: 91 
Explanation: The answer should be the total numbers in the range of 0 ≤ x < 100, excluding 11,22,33,44,55,66,77,88,99
             
*/

public class _0357_Count_Numbers_with_Unique_Digits {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int countNumbersWithUniqueDigits_Math(int n) {
        if (n > 10) {
            return countNumbersWithUniqueDigits_Math(10);
        } else if (n == 1) {
            return 10;
        } else if (n == 0) {
            return 1;
        } else {
            int amount = 1;
            for (int i = 1, j = 9; i < n; i++, j--) {
                amount *= j;
            }
            return countNumbersWithUniqueDigits_Math(n - 1) + amount * 9;
        }
    }
}
