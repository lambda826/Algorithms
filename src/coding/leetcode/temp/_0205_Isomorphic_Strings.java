/**
 *  @author: Yunxiang He
 *  @date  : 2018-07-29 15:09
 */

package coding.leetcode.temp;

/*

Given two strings s and t, determine if they are isomorphic.

Two strings are isomorphic if the characters in s can be replaced to get t.

All occurrences of ss character must be replaced with another character while preserving the order of characters. 
No two characters may map to the same character but ss character may map to itself.

Example 1:
    Input: s = "egg", t = "add"
    Output: true

Example 2:
    Input: s = "foo", t = "bar"
    Output: false

Example 3:
    Input: s = "paper", t = "__00000_title"
    Output: true


Note:
    You may assume both s and t have the same length.

*/

public class _0205_Isomorphic_Strings {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public boolean isIsomorphic(String s, String t) {
        int[] ss = new int[256];
        int[] tt = new int[256];

        int len = s.length();
        for (int i = 0; i < len; i++) {
            if (ss[s.charAt(i)] != tt[t.charAt(i)]) {
                return false;
            }
            ss[s.charAt(i)] = tt[t.charAt(i)] = i + 1;
        }
        return true;
    }
}
