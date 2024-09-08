/**
 * @author Yunxiang He
 * @date Jan 6, 2018 12:42:48 AM
 */

package questions.temp;

/*

Given a positive integer, check whether it has alternating bits: namely, if two adjacent bits will always have different values.

Example 1:
Input: 5
Output: True
Explanation:
The binary representation of 5 is: 101

Example 2:
Input: 7
Output: False
Explanation:
The binary representation of 7 is: 111.

Example 3:
Input: 11
Output: False
Explanation:
The binary representation of 11 is: 1011.

Example 4:
Input: 10
Output: True
Explanation:
The binary representation of 10 is: 1010.

*/

public class _0693_Binary_Number_with_Alternating_Bits {

    /**
     * Solution:
     * Set flag to check
     * --------------------------------------------------------------
     * Tips:
     * Corner case: 0, 1 should return true
     * --------------------------------------------------------------
     *  Time complexity : O(n)
     * Space complexity : O(1)
     */
    public boolean hasAlternatingBits(int n) {
        if (n <= 1) {
            return true;
        }
        boolean flag = (n & 1) == 1;
        while (n != 0) {
            if (flag) {
                if ((n & 1) == 1) {
                    flag = false;
                } else {
                    return false;
                }
            } else {
                if ((n & 1) == 0) {
                    flag = true;
                } else {
                    return false;
                }
            }
            n >>= 1;
        }
        return true;
    }

    public static void main(String[] args) {
        _0693_Binary_Number_with_Alternating_Bits test = new _0693_Binary_Number_with_Alternating_Bits();
        System.out.println(test.hasAlternatingBits(5));
    }
}
