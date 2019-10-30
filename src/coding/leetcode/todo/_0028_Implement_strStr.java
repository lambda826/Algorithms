/**
 *  @author Yunxiang He
 *  @date 11/10/2018
 */

package coding.leetcode.todo;

/*

Implement strStr().
Return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.


Example 1:
    Input: 
        haystack = "hello", needle = "ll"
    Output: 
        2

Example 2:
    Input: 
        haystack = "aaaaa", needle = "bba"
    Output: 
        -1


Clarification:
    What should we return when needle is an empty string? This is a great question to ask during an interview.
    For the purpose of this problem, we will return 0 when needle is an empty string. This is consistent to C's strstr() and Java's indexOf().

*/

public class _0028_Implement_strStr {

    public static void main(String[] args) {
        _0028_Implement_strStr test = new _0028_Implement_strStr();
        test.strStr("a", "a");
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int strStr(String haystack, String needle) {
        if (needle.length() == 0) {
            return 0;
        }
        int start = 0;
        int i;
        int j;
        while (start <= haystack.length() - needle.length()) {
            i = start;
            j = 0;
            while (j < needle.length() && haystack.charAt(i) == needle.charAt(j)) {
                ++i;
                ++j;
            }
            if (j == needle.length()) {
                return start;
            }
            start++;
        }
        return -1;
    }

}
