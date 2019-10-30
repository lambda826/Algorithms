/**
 *  @author Yunxiang He
 *  @date 02/21/2019
 */

package coding.temp;

/*

Given two strings s and t, determine if they are both one edit distance apart.


Example 1:
    Input: s = "ab", t = "acb"
    Output: true
    Explanation: We can insert 'c' into s to get t.

Example 2:
    Input: s = "cab", t = "ad"
    Output: false
    Explanation: We cannot get t from s by only one step.

Example 3:
    Input: s = "1203", t = "1213"
    Output: true
    Explanation: We can replace '0' with '1' to get t.


Note: 
    There are 3 possiblities to satisify one edit distance apart:
    Insert a character into s to get t
    Delete a character from s to get t
    Replace a character of s to get t

*/

public class _0161_One_Edit_Distance {

    public static void main(String[] args) {
        _0161_One_Edit_Distance test = new _0161_One_Edit_Distance();
        test.isOneEditDistance("", "a");
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public boolean isOneEditDistance(String s, String t) {
        if (s.length() - s.length() > 1) {
            return false;
        }
        if (s.length() > t.length()) {
            return isOneEditDistance(t, s);
        }
        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) != t.charAt(i)) {
                if (s.length() == t.length()) {
                    return s.substring(i + 1).equals(t.substring(i + 1));
                } else {
                    return s.substring(i).equals(t.substring(i + 1));
                }
            }
        }
        return s.length() + 1 == t.length();
    }
}
