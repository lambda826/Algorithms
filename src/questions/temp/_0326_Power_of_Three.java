/**
 * @author: Yunxiang He
 * @date : 2018-07-17 00:57
 */

package questions.temp;

/*

Given an integer, write a function to determine if it is a power of three.


Example 1:
    Input: 27
    Output: true

Example 2:
    Input: 0
    Output: false

Example 3:
    Input: 9
    Output: true

Example 4:
    Input: 45
    Output: false


Follow up:
    Could you do it without using any loop / recursion?

*/

public class _0326_Power_of_Three {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public boolean isPowerOfThree(int n) {
        if (n < 1) {
            return false;
        } else if (n == 1) {
            return true;
        } else if (n % 3 != 0) {
            return false;
        } else {
            return isPowerOfThree(n / 3);
        }
    }

    public boolean isPowerOfThree2(int n) {
        // 3^19 = 1162261467, 3^20 = 3486784401 > Ingeter.MAX_VALUE
        return (n > 0 && 1162261467 % n == 0);
    }
}
