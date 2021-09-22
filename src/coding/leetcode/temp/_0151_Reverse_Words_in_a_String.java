/**
 *  @author Yunxiang He
 *  @date 01/07/2018
 */

package coding.leetcode.temp;

/*

Given an input string, reverse the string word by word.


Example 1:
    Input: "the sky is blue"
    Output: "blue is sky the"
Example 2:
    Input: "  hello world!  "
    Output: "world! hello"
    Explanation: Your reversed string should not contain leading or trailing spaces.
Example 3:
    Input: "a good   example"
    Output: "example good a"
    Explanation: You need to reduce multiple spaces between two words to a single space in the reversed string.
 

Note:
    A word is defined as a sequence of non-space characters.
    Input string may contain leading or trailing spaces. However, your reversed string should not contain leading or trailing spaces.
    You need to reduce multiple spaces between two words to a single space in the reversed string.
 

*/

public class _0151_Reverse_Words_in_a_String {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public String solution2(String s) {
        StringBuilder sb = new StringBuilder();
        s = " " + s;
        boolean isStart = false;
        boolean isEnd = true;
        int start = 0;
        int end = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if ((s.charAt(i) == ' ' && isStart)) {
                start = i + 1;
                isStart = false;
            } else if (s.charAt(i) != ' ' && isEnd) {
                end = i + 1;
                isEnd = false;
                isStart = true;
            }
            if (!isStart && !isEnd) {
                sb.append(s.substring(start, end) + " ");
                isStart = true;
                isEnd = true;
            }
        }
        return sb.toString().trim();
    }

}
