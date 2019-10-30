/**
 *  @author: Yunxiang He
 *  @date  : 2018-10-04
 */

package coding.lintcode;

import java.util.HashSet;
import java.util.Set;

/*

The characters of a substring can not be repeated and output the number of substrings that satisfy such conditions (the same substring is counted only 1 times).


Example
    Given str = "abc", k = 2, output 2.
    Explanation:
    Characters are not repeated, and substrings of length k have "ab", "bc".
    Given str = "abc", k = 2, output 2.
    Explanation:
    Characters are not repeated, and substrings of length k have "a", "b"."c".


Notice
    |str| <= 100000.
    k <= 100000.
    All characters are lowercase.

*/

public class __1377_Find_Substring {

    public static void main(String[] args) {
        new __1377_Find_Substring().findSubstring("abc", 2);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int findSubstring(String stringIn, int k) {
        Set<String> set = new HashSet<>();
        int l = 0;
        int r = 0;
        boolean[] dup = new boolean[256];
        while (r < stringIn.length()) {
            while (r < stringIn.length() && r - l < k && !dup[stringIn.charAt(r)]) {
                dup[stringIn.charAt(r)] = true;
                ++r;
            }
            if (r - l == k) {
                set.add(stringIn.substring(l, r));
            }
            dup[stringIn.charAt(l)] = false;
            ++l;
        }
        return set.size();
    }

}
