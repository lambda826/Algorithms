/**
 *  @author Yunxiang He
 *  @date 01/29/2018
 */

package coding.temp;

import java.util.HashMap;
import java.util.Map;

/*

Given a string, find the first non-repeating character in it and return it's index. 
If it doesn't exist, return -1.

Examples:
    s = "leetcode"
    return 0.

    s = "loveleetcode",
    return 2.


Note: 
    You may assume the string contain only lowercase letters.

*/

public class _0387_First_Unique_Character_in_a_String {

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int firstUniqChar(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (char ch : s.toCharArray()) {
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }
        int i = 0;
        for (char ch : s.toCharArray()) {
            if (map.get(ch) == 1) {
                return i;
            }
            ++i;
        }
        return -1;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int firstUniqChar2(String s) {
        int resIndex = s.length();
        for (char c = 'a'; c <= 'z'; c++) {
            int index = s.indexOf(c);
            if (index != -1 && index == s.lastIndexOf(c)) {
                resIndex = Math.min(resIndex, index);
            }
        }
        return resIndex == s.length() ? -1 : resIndex;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int firstUniqChar3(String s) {
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (s.indexOf(c) == s.lastIndexOf(c)) {
                return i;
            }
        }
        return -1;
    }

}
