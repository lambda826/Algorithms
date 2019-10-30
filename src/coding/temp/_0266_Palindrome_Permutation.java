/**
 *  @author: Yunxiang He
 *  @date  : 2018-07-20 12:46
 */

package coding.temp;

import java.util.HashMap;
import java.util.Map;

/*

Given a string, determine if a permutation of the string could form a palindrome.


Example 1:
    Input: "code"
    Output: false

Example 2:
    Input: "aab"
    Output: true

Example 3:
    Input: "carerac"
    Output: true

*/

public class _0266_Palindrome_Permutation {

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public boolean canPermutePalindrome(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        int odd = 0;
        for (int val : map.values()) {
            if ((val & 1) == 1) {
                odd++;
            }
        }
        return odd < 2;
    }
}
