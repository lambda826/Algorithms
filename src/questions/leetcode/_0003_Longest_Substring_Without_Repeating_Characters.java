package questions.leetcode;

/*

Given a string s, find the length of the longest substring without repeating characters.


Example 1:
    Input: s = "abcabcbb"
    Output: 3
    Explanation: The answer is "abc", with the length of 3.

Example 2:
    Input: s = "bbbbb"
    Output: 1
    Explanation: The answer is "b", with the length of 1.

Example 3:
    Input: s = "pwwkew"
    Output: 3
    Explanation: The answer is "wke", with the length of 3.
    Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.


Constraints:
    0 <= s.length <= 5 * 10^4
    s consists of English letters, digits, symbols and spaces.

*/
public class _0003_Longest_Substring_Without_Repeating_Characters {

    class Solution {
        public int lengthOfLongestSubstring(String s) {
            int left = 0;
            int right = 0;
            int max = 0;
            int[] chs = new int[256];
            while (right < s.length()) {
                if (chs[s.charAt(right)] == 0) {
                    ++chs[s.charAt(right)];
                    ++right;
                    max = Math.max(max, right - left);
                } else {
                    --chs[s.charAt(left)];
                    ++left;
                }
            }
            return max;
        }
    }
}
