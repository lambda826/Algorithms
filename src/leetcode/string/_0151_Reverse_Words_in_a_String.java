package leetcode.string;

/*

Given an input string s, reverse the order of the words.
A word is defined as a sequence of non-space characters. The words in s will be separated by at least one space.
Return a string of the words in reverse order concatenated by a single space.
Note that s may contain leading or trailing spaces or multiple spaces between two words.
The returned string should only have a single space separating the words. Do not include any extra spaces.


Example 1:
    Input: s = "the sky is blue"
    Output: "blue is sky the"

Example 2:
    Input: s = "  hello world  "
    Output: "world hello"
    Explanation: Your reversed string should not contain leading or trailing spaces.

Example 3:
    Input: s = "a good   example"
    Output: "example good a"
    Explanation: You need to reduce multiple spaces between two words to a single space in the reversed string.


Constraints:
    1 <= s.length <= 10^4
    s contains English letters (upper-case and lower-case), digits, and spaces ' '.
    There is at least one word in s.


Follow-up: If the string data type is mutable in your language, can you solve it in-place with O(1) extra space?

*/
public class _0151_Reverse_Words_in_a_String {

    class Solution {
        public String reverseWords(String s) {
            s = " " + s;
            int right = -1;
            StringBuilder sb = new StringBuilder();
            for (int i = s.length() - 1; i >= 0; --i) {
                if (s.charAt(i) != ' ') {
                    if (right == -1) {
                        right = i;
                    }
                } else {
                    if (right != -1) {
                        appendWord(s, i + 1, right, sb);
                        right = -1;
                    }
                }
            }
            sb.setLength(sb.length() - 1);
            return sb.toString();
        }

        private void appendWord(String s, int left, int right, StringBuilder sb) {
            while (left <= right) {
                sb.append(s.charAt(left));
                left++;
            }
            sb.append(' ');
        }
    }
}
