/**
 *  @author: Yunxiang He
 *  @date  : 2018-06-27
 */

package questions.temp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*

A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).

Find all strobogrammatic numbers that are of length = n.


Example:
    Input:  n = 2
    Output: ["11","69","88","96"]

*/

public class _0247_Strobogrammatic_Number_II {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    Map<Character, Character> map = new HashMap<>();
    List<String> res = new ArrayList<>();
    char[] chs;

    public List<String> findStrobogrammatic(int n) {
        chs = new char[n];
        map.put('0', '0');
        map.put('1', '1');
        map.put('6', '9');
        map.put('8', '8');
        map.put('9', '6');
        if ((n & 1) == 0) {
            dfs(n / 2 - 1, n / 2);
        } else {
            for (char c : map.keySet()) {
                if (c != '6' && c != '9') {
                    chs[n / 2] = c;
                    dfs(n / 2 - 1, n / 2 + 1);
                }
            }
        }
        return res;
    }

    private void dfs(int left, int right) {
        if (left < 0) {
            res.add(new String(chs));
        } else {
            for (char c : map.keySet()) {
                if (0 != left || '0' != c) {
                    chs[left] = c;
                    chs[right] = map.get(c);
                    dfs(left - 1, right + 1);
                }
            }
        }
    }

    public static void main(String[] args) {
        _0247_Strobogrammatic_Number_II test = new _0247_Strobogrammatic_Number_II();
        test.findStrobogrammatic(1);
    }
}
