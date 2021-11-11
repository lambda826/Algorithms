/**
 *  @author: Yunxiang He
 *  @date  : 2018-07-29 17:42
 */

package questions.temp;

import java.util.HashMap;
import java.util.Map;

/*

Given a pattern and a string str, find if str follows the same pattern.

Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty word in str.

Example 1:
Input: pattern = "abba", str = "dog cat cat dog"
Output: true

Example 2:
Input:pattern = "abba", str = "dog cat cat fish"
Output: false

Example 3:
Input: pattern = "aaaa", str = "dog cat cat dog"
Output: false

Example 4:
Input: pattern = "abba", str = "dog dog dog dog"
Output: false

Notes:
You may assume pattern contains only lowercase letters, and str contains lowercase letters separated by a single space.

*/

public class _0290_Word_Pattern {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public boolean wordPattern(String pattern, String str) {
        String[] strs = str.split(" ");
        if (pattern.length() != strs.length) {
            return false;
        }
        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < strs.length; i++) {
            String s = " " + pattern.charAt(i);
            String s1 = map.get(strs[i]);
            String s2 = map.get(s);
            if (s1 == null && s2 == null) {
                map.put(strs[i], s);
                map.put(s, strs[i]);
            } else if (!strs[i].equals(s2) || !s.equals(s1)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new _0290_Word_Pattern().wordPattern("abba", "dog cat cat dog"));
    }
}
