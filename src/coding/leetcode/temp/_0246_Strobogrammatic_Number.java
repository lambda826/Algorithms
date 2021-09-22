/**
 *  @author: Yunxiang He
 *  @date  : 2018-07-17 01:06
 */

package coding.leetcode.temp;

import java.util.HashMap;
import java.util.Map;

/*

A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).
Write a function to determine if a number is strobogrammatic. 
The number is represented as a string.
、

Example 1:
    Input:  "69"
    Output: true

Example 2:
    Input:  "88"
    Output: true

Example 3:
    Input:  "962"
    Output: false

*/

public class _0246_Strobogrammatic_Number {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public boolean isStrobogrammatic(String num) {
        int left = 0;
        int right = num.length() - 1;
        while (left <= right) {
            if (num.charAt(left) == '0' && num.charAt(right) != '0' || num.charAt(left) == '1' && num.charAt(right) != '1' || num.charAt(left) == '2' || num.charAt(left) == '3' || num.charAt(left) == '4' || num.charAt(left) == '5'
                    || num.charAt(left) == '6' && num.charAt(right) != '9' || num.charAt(left) == '7' || num.charAt(left) == '8' && num.charAt(right) != '8' || num.charAt(left) == '9' && num.charAt(right) != '6') {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public boolean isStrobogrammatic2(String num) {
        Map<Character, Character> map = new HashMap<>();
        map.put('0', '0');
        map.put('1', '1');
        map.put('6', '9');
        map.put('8', '8');
        map.put('9', '6');
        int left = 0;
        int right = num.length() - 1;
        while (left <= right) {
            if (!map.containsKey(num.charAt(left)) || !map.containsKey(num.charAt(right)) || map.get(num.charAt(left)) != num.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
