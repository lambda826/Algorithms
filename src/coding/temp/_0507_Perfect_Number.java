/**
 *  @author: Yunxiang He
 *  @date  : 2018-07-17 04:39
 */

package coding.temp;

/*


We define the Perfect Number is a positive integer that is equal to the sum of all its positive divisors except itself.

Now, given an integer n, write a function that returns true when it is a perfect number and false when it is not.

Example:
Input: 28
Output: True
Explanation: 28 = 1 + 2 + 4 + 7 + 14

Note: The input number n will not exceed 100,000,000. (1e8)

*/

public class _0507_Perfect_Number {

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public boolean checkPerfectNumber(int num) {
        if (num <= 2) {
            return false;
        }
        int sum = 0;
        int left = 2;
        int right = num / 2;
        while (left <= right) {
            if (num % left == 0) {
                sum += left + right;
                right = num / left;
            }
            right = num / ++left;
        }
        return sum + 1 == num;
    }

    public static void main(String[] args) {
        new _0507_Perfect_Number().checkPerfectNumber(28);
    }
}
