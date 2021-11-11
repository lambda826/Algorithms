/**
 *  @author: Yunxiang He
 *  @date  : 2018-06-27
 */

package questions.temp;

import java.util.HashMap;
import java.util.Map;

/*

A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).
Write a function to count the total strobogrammatic numbers that exist in the range of low <= num <= high.


Example:
    Input: 
        low = "50", high = "100"
    Output: 
        3 
    Explanation: 
        69, 88, and 96 are three strobogrammatic numbers.


Note:
    Because the range might be a large number, the low and high numbers are represented as string.

*/

public class _0248_Strobogrammatic_Number_III {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    Map<Character, Character> map = new HashMap<>();
    int count = 0;
    char[] chs;
    String low;
    String high;

    public int strobogrammaticInRange(String low, String high) {
        map.put('0', '0');
        map.put('1', '1');
        map.put('6', '9');
        map.put('8', '8');
        map.put('9', '6');
        this.low = low;
        this.high = high;
        for (int n = low.length(); n <= high.length(); n++) {
            chs = new char[n];
            if ((n & 1) == 0) {
                DFS(n / 2 - 1, n / 2);
            } else {
                for (char c : map.keySet()) {
                    if (c != '6' && c != '9') {
                        chs[n / 2] = c;
                        DFS(n / 2 - 1, n / 2 + 1);
                    }
                }
            }
        }
        return count;
    }

    private void DFS(int left, int right) {
        if (left < 0) {
            String n = new String(chs);
            if (n.length() == low.length() && n.length() == high.length()) {
                if (compare(low, n) && compare(n, high)) {
                    count++;
                }
                return;
            } else if (n.length() == low.length() && compare(low, n)) {
                count++;
            } else if (n.length() == high.length() && compare(n, high)) {
                count++;
            } else if (n.length() > low.length() && n.length() < high.length()) {
                count++;
            }
        } else {
            for (char c : map.keySet()) {
                if (0 != left || '0' != c) {
                    chs[left] = c;
                    chs[right] = map.get(c);
                    DFS(left - 1, right + 1);
                }
            }
        }
    }

    private boolean compare(String cmp1, String cmp2) {
        for (int i = 0; i < cmp1.length(); i++) {
            if (cmp1.charAt(i) < cmp2.charAt(i)) {
                break;
            } else if (cmp1.charAt(i) == cmp2.charAt(i)) {
                continue;
            } else if (cmp1.charAt(i) > cmp2.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        _0248_Strobogrammatic_Number_III test = new _0248_Strobogrammatic_Number_III();
        System.out.println(test.strobogrammaticInRange("0", "1680"));

    }

}
