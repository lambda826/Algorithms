/**
 *  @author: Yunxiang He
 *  @date  : 2018-07-13 10:00
 */

package questions.temp;

/*

Implement strStr().

Return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.

Example 1:
Input: haystack = "hello", needle = "ll"
Output: 2

Example 2:
Input: haystack = "aaaaa", needle = "bba"
Output: -1

Clarification:
What should we return when needle is an empty string? This is a great question to ask during an interview.
For the purpose of this problem, we will return 0 when needle is an empty string. This is consistent to C's strstr() and Java's indexOf().

*/

public class __0028_Implement_strStr {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int strStr(String haystack, String needle) {
        if (needle == "" || haystack.equals(needle)) {
            return 0;
        }
        if (needle.length() <= haystack.length()) {
            for (int i = 0; i < haystack.length() - needle.length() + 1; i++) {
                if (haystack.substring(i, i + needle.length()).equals(needle)) {
                    return i;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(new __0028_Implement_strStr().strStr("mississippi", "issipi"));
    }
}
