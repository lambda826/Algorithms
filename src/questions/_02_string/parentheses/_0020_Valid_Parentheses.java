package questions._02_string.parentheses;

import java.util.ArrayDeque;
import java.util.Deque;

/*

Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

An input string is valid if:
    Open brackets must be closed by the same type of brackets.
    Open brackets must be closed in the correct order.


Example 1:
    Input:
        s = "()"
    Output:
        true

Example 2:
    Input:
        s = "()[]{}"
    Output:
        true

Example 3:
    Input:
        s = "(]"
    Output:
        false

Example 4:
    Input:
        s = "([)]"
    Output:
        false

Example 5:
    Input:
        s = "{[]}"
    Output:
        true


Constraints:
    1 <= s.length <= 10^4
    s consists of parentheses only '()[]{}'.

*/

public class _0020_Valid_Parentheses {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static class Solution {

        public boolean isValid(String s) {
            if ((s.length() & 1) == 1) {
                return false;
            }
            Deque<Character> deque = new ArrayDeque<>();
            for (char ch : s.toCharArray()) {
                if (ch == '(' || ch == '{' || ch == '[') {
                    deque.offerLast(ch);
                } else if (ch == ')') {
                    if (deque.size() == 0 || deque.pollLast() != '(') {
                        return false;
                    }
                } else if (ch == '}') {
                    if (deque.size() == 0 || deque.pollLast() != '{') {
                        return false;
                    }
                } else {
                    if (deque.size() == 0 || deque.pollLast() != '[') {
                        return false;
                    }
                }
            }
            return deque.size() == 0;
        }
    }

}