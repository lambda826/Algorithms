/**
 * @author Yunxiang He
 * @date Jan 5, 2018 12:31:16 AM
 */

package questions.temp;

/*

Given an integer, write a function to determine if it is a power of two.


Example 1:
    Input: 1
    Output: true 
    Explanation: 20 = 1
    
Example 2:
    Input: 16
    Output: true
    Explanation: 24 = 16
    
Example 3:
    Input: 218
    Output: false

*/

public class _0231_Power_of_Two {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public boolean isPowerOfTwo(int n) {
        if (n < 1) {
            return false;
        } else if (n == 1) {
            return true;
        } else if ((n & 1) == 1) {
            return false;
        } else {
            return isPowerOfTwo(n >> 1);
        }
    }
}
