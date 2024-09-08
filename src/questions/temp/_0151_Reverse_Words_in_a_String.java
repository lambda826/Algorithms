package questions.temp;

import java.util.ArrayList;
import java.util.List;

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

    class Solution {
        public String reverseWords(String s) {
            int i = 0;
            int j;
            List<String> words = new ArrayList<>();
            while (i < s.length()) {
                // Find the first char which is non whitespace
                while (i < s.length() && s.charAt(i) == ' ') {
                    i++;
                }
                // Find the first whitespace
                j = i + 1;
                while (j < s.length() && s.charAt(j) != ' ') {
                    j++;
                }
                if (i < s.length()) {
                    words.add(0, s.substring(i, j));
                }
                i = j;
            }
            return String.join(" ", words);
        }
    }
}
