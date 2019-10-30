/**
 *  @author Yunxiang He
 *  @date Dec 21, 2017 9:22:20 PM
 */

package coding.leetcode.todo;

/*

Given a string and an integer k, you need to reverse the first k characters for every 2k characters counting from the start of the string. 
If there are less than k characters left, reverse all of them. 
If there are less than 2k but greater than or equal to k characters, then reverse the first k characters and left the other as original.

Example:
Input: s = "abcdefg", k = 2
Output: "bacdfeg"

Restrictions:
The string consists of lower English letters only.
Length of the given string and k will in the range [1, 10000]

*/

public class _0541_Reverse_String_II {

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public String reverseStr(String s, int k) {
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i += 2 * k) {
            reverse(chars, i, i + k - 1);
        }
        return new String(chars);
    }

    private void reverse(char[] chars, int from, int to) {
        if (to > chars.length - 1) {
            to = chars.length - 1;
        }
        while (from < to) {
            char temp = chars[from];
            chars[from] = chars[to];
            chars[to] = temp;
            from++;
            to--;
        }
    }

    public static void main(String[] args) {
        System.out.println(new _0541_Reverse_String_II().reverseStr("abcde", 2));
    }
}
