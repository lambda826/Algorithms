/**
 *  @author Yunxiang He
 *  @date 2018-07-20 17:13
 */

package coding.leetcode.temp;

import java.util.HashMap;
import java.util.Map;

/*

Given a string which consists of lowercase or uppercase letters, find the length of the longest palindromes that can be built with those letters.

This is case sensitive, for example "Aa" is not considered a palindrome here.

Example:
    Input:
    "abccccdd"
    Output:
    7

Explanation:
One longest palindrome that can be built is "dccaccd", whose length is 7.

Note:
    Assume the length of given string will not exceed 1,010.

*/

public class _0409_Longest_Palindrome {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int longestPalindrome(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int lenth = 0;
        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        for (int c : map.values()) {
            if ((c & 1) == 1) {
                lenth += c - 1;
            } else {
                lenth += c;
            }
        }
        lenth++;
        return lenth > s.length() ? lenth - 1 : lenth;
    }

    public static void main(String[] args) {
        _0409_Longest_Palindrome test = new _0409_Longest_Palindrome();
        test.longestPalindrome("abccccdd");
    }
}
