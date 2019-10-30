/**
 *  @author Yunxiang He
 *  @date 01/22/2018
 */

package coding.temp;

/*

Given a string, your task is to count how many palindromic substrings in this string.
The substrings with different start indexes or end indexes are counted as different substrings even they consist of same characters.


Example 1:
    Input: "abc"
    Output: 3
    Explanation: Three palindromic strings: "a", "b", "c".

Example 2:
    Input: "aaa"
    Output: 6
    Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".


Note:
    The input string length won't exceed 1000.

*/

public class _0647_Palindromic_Substrings {

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Count from the center
    public int countSubstrings(String s) {
        int count = 0;
        char[] chs = s.toCharArray();
        for (int center = 0; center < chs.length; center++) {
            count += countFromCenter(chs, center, center);
            count += countFromCenter(chs, center, center + 1);
        }
        return count;
    }

    private int countFromCenter(char[] chs, int left, int right) {
        int count = 0;
        while (left >= 0 && right < chs.length && chs[left--] == chs[right++]) {
            count++;
        }
        return count;
    }
}
