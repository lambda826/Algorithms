/**
 *  @author Yunxiang He
 *  @date 02/15/2019
 */

package questions.temp;

import java.util.ArrayList;
import java.util.List;

/*

Given a string S of digits, such as S = "123456579", we can split it into a Fibonacci-like sequence [123, 456, 579].

Formally, a Fibonacci-like sequence is a list F of non-negative integers such that:
    0 <= F[i] <= 2^31 - 1, (that is, each integer fits a 32-bit signed integer type);
    F.length >= 3;
    and F[i] + F[i+1] = F[i+2] for all 0 <= i < F.length - 2.

Also, note that when splitting the string into pieces, each piece must not have extra leading zeroes, except if the piece is the number 0 itself.
Return any Fibonacci-like sequence split from S, or return [] if it cannot be done.

Example 1:
    Input: "123456579"
    Output: [123,456,579]

Example 2:
    Input: "11235813"
    Output: [1,1,2,3,5,8,13]

Example 3:
    Input: "112358130"
    Output: []
    Explanation: The task is impossible.

Example 4:
    Input: "0123"
    Output: []
    Explanation: Leading zeroes are not allowed, so "01", "2", "3" is not valid.

Example 5:
    Input: "1101111"
    Output: [110, 1, 111]
    Explanation: The output [11, 0, 11, 11] would also be accepted.


Note:
    1 <= S.length <= 200
    S contains only digits.

*/

public class _0842_Split_Array_into_Fibonacci_Sequence {

    public static void main(String[] args) {
        new _0842_Split_Array_into_Fibonacci_Sequence().splitIntoFibonacci("123456579");
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public List<Integer> splitIntoFibonacci(String S) {
        List<Integer> list = new ArrayList<>();
        dfs(list, S, 0);
        return list;
    }

    private boolean dfs(List<Integer> list, String s, int index) {
        if (index == s.length() && list.size() > 2) {
            return true;
        }
        long num = 0;
        for (int i = index; i < s.length(); ++i) {
            if (num > Integer.MAX_VALUE) {
                break;
            }
            // Leading zero
            if (s.charAt(index) == '0' && i > index) {
                break;
            }
            // Here is the split
            num = num * 10 + Character.getNumericValue(s.charAt(i));
            // Current num is greater than the sum of previous two nums
            if (list.size() > 2 && num > list.get(list.size() - 2) + list.get(list.size() - 1)) {
                break;
            }
            if (list.size() < 2 || num == list.get(list.size() - 2) + list.get(list.size() - 1)) {
                list.add((int) num);
                if (dfs(list, s, i + 1)) {
                    return true;
                }
                list.remove(list.size() - 1);
            }
        }
        return false;
    }
}
