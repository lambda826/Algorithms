/**
 *  @author: Yunxiang He
 *  @date  : 2018-07-12 08:57
 */

package coding.leetcode.temp;

/*

Given a non-empty string check if it can be constructed by taking a substring of it and appending multiple copies of the substring together. 
You may assume the given string consists of lowercase English letters only and its length will not exceed 10000.

Example 1:
Input: "abab"
Output: True
Explanation: It's the substring "ab" twice.

Example 2:
Input: "aba"
Output: False

Example 3:
Input: "abcabcabcabc"
Output: True
Explanation: It's the substring "abc" four times. (And the substring "abcabc" twice.)

*/

public class _0459_Repeated_Substring_Pattern {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public boolean repeatedSubstringPattern(String s) {
        outter:
        for (int i = 2; s.length() / i > 0; i++) {
            int len = s.length() / i;
            if (s.length() % i == 0) {
                String sub = s.substring(0, len);
                for (int k = len; k <= s.length() - len; k += len) {
                    if (!s.startsWith(sub, k)) {
                        continue outter;
                    }
                }
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(new _0459_Repeated_Substring_Pattern().repeatedSubstringPattern("ababababab"));
    }
}
