/**
 * @author Yunxiang He
 * @date 02/16/2019
 */

package questions.temp;

/*

Additive number is a string whose digits can form additive sequence.
A valid additive sequence should contain at least three numbers. 
    Except for the first two numbers, each subsequent number in the sequence must be the sum of the preceding two.
Given a string containing only digits '0'-'9', write a function to determine if it's an additive number.
Note: Numbers in the additive sequence cannot have leading zeros, so sequence 1, 2, 03 or 1, 02, 3 is invalid.

Example 1:
    Input: "112358"
    Output: true 
    Explanation: The digits can form an additive sequence: 1, 1, 2, 3, 5, 8. 
                 1 + 1 = 2, 1 + 2 = 3, 2 + 3 = 5, 3 + 5 = 8

Example 2:
    Input: "199100199"
    Output: true 
    Explanation: The additive sequence is: 1, 99, 100, 199. 
                 1 + 99 = 100, 99 + 100 = 199


Follow up:
    How would you handle overflow for very large input integers?

*/

public class _0306_Additive_Number {

    public static void main(String[] args) {
        System.out.println(new _0306_Additive_Number().isAdditiveNumber("10"));
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public boolean isAdditiveNumber(String num) {
        return dfs(-1, -1, num, 0, 0);
    }

    private boolean dfs(long first, long second, String s, int index, int level) {
        if (index == s.length() && first != -1 && second != -1 && level > 2) {
            return true;
        }
        long num = 0;
        for (int i = index; i < s.length(); ++i) {
            // Leading zero
            if (s.charAt(index) == '0' && i > index) {
                break;
            }
            // Here is the split
            num = num * 10 + Character.getNumericValue(s.charAt(i));
            // Current num is greater than the sum of previous two nums
            if (second != -1 && num > first + second) {
                break;
            }
            long _first = first;
            long _second = second;
            if (_second == -1 || num == _first + _second) {
                if (_first == -1) {
                    _first = num;
                } else if (_second == -1) {
                    _second = num;
                } else {
                    _first = _second;
                    _second = num;
                }
                if (dfs(_first, _second, s, i + 1, level + 1)) {
                    return true;
                }
            }
        }
        return false;
    }
}
