/**
 *  @author: Yunxiang He
 *  @date  : 2018-07-16 10:33
 */

package coding.temp;

/*

Given a column title as appear in an Excel sheet, return its corresponding column number.

For example:
    A -> 1
    B -> 2
    C -> 3
    ...
    Z -> 26
    AA -> 27
    AB -> 28 
    ...

Example 1:
Input: "A"
Output: 1

Example 2:
Input: "AB"
Output: 28

Example 3:
Input: "ZY"
Output: 701

*/

public class _0171_Excel_Sheet_Column_Number {

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int titleToNumber(String s) {
        int sum = 0;
        for (int c = 1, i = s.length() - 1; i > -1; i--, c *= 26) {
            sum += (s.charAt(i) - 64) * c;
        }
        return sum;
    }
}
