package coding.leetcode.temp;

/*

Given a string, find the length of the longest substring without repeating characters.

Example 1:
    Input: "abcaccbb"
    Output: 3 
    Explanation: The answer is "abc", with the length of 3. 

Example 2:
    Input: "bbbbb"
    Output: 1
    Explanation: The answer is "b", with the length of 1.

Example 3:
    Input: "pwwkew"
    Output: 3
    Explanation: The answer is "wke", with the length of 3. 
                 Note that the answer must be a substring, "pwke" is a subsequence and not a substring.


*/

public class _0003_Longest_Substring_Without_Repeating_Characters {

    public static void main(String[] args) {
        _0003_Longest_Substring_Without_Repeating_Characters test = new _0003_Longest_Substring_Without_Repeating_Characters();
        test.lengthOfLongestSubstring("abcabcbb");
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int lengthOfLongestSubstring(String s) {
        boolean[] visited = new boolean[256];
        char[] chs = s.toCharArray();
        int l = 0;
        int r = 0;
        int max = 0;
        while (r < chs.length) {
            while (r < chs.length && !visited[chs[r]]) {
                visited[chs[r++]] = true;
            }
            max = Math.max(max, r - l);
            visited[chs[l++]] = false;
        }
        return max;
    }
}
