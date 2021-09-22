/**
 *  @author: Yunxiang He
 *  @date  : 2018-07-13 07:12
 */

package coding.leetcode.temp;

/*

Given a string s consists of upper/lower-case alphabets and empty space characters ' ', return the length of last word in the string.

If the last word does not exist, return 0.

Note: A word is defined as a character sequence consists of non-space characters only.

Example:

Input: "Hello World"
Output: 5

*/

public class _0058_Length_of_Last_Word {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int lengthOfLastWord(String s) {
        String[] ss = s.trim().split(" ");
        for (int i = ss.length - 1; i >= 0; i--) {
            if (ss[i].length() > 0) {
                return ss[i].length();
            }
        }
        return 0;
    }
}
