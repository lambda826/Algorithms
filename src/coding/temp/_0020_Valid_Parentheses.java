/**
 *  @author Yunxiang He
 *  @date 05/10/2017
 */
package coding.temp;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/*

Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

An input string is valid if:
    Open brackets must be closed by the same type of brackets.
    Open brackets must be closed in the correct order.
Note that an empty string is also considered valid.


Example 1:
    Input: "()"
    Output: true

Example 2:
    Input: "()[]{}"
    Output: true

Example 3:
    Input: "(]"
    Output: false

Example 4:
    Input: "([)]"
    Output: false

Example 5:
    Input: "{[]}"
    Output: true

*/

public class _0020_Valid_Parentheses {

    public static void main(String[] args) {
        new _0020_Valid_Parentheses().isValid("()[]{}");
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public boolean isValid(String s) {
        if (s.length() % 2 != 0) {
            return false;
        }
        Map<Character, Character> map = new HashMap<>();
        map.put('}', '{');
        map.put(']', '[');
        map.put(')', '(');
        Deque<Character> deque = new ArrayDeque<>();
        int index = 0;
        char temp;
        while (index < s.length()) {
            temp = s.charAt(index);
            if (!map.containsKey(temp)) {
                deque.addLast(temp);
            } else if (deque.pollLast() != map.get(temp)) {
                return false;
            }
            index++;
        }
        return deque.size() == 0;
    }

}
