/**
 *  @author: Yunxiang He
 *  @date  : 2018-10-08
 */

package coding.lintcode;

import java.util.ArrayDeque;
import java.util.Deque;

/*

Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.


Example
    The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and "([)]" are not.

*/

public class __0423_Valid_Parentheses {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public boolean isValidParentheses(String s) {
        Deque<Character> deque = new ArrayDeque<>();
        for (char ch : s.toCharArray()) {
            switch (ch) {
                case '{':
                case '[':
                case '(':
                    deque.addLast(ch);
                    break;
                case '}':
                    if (deque.size() == 0 || deque.removeLast() != '{') {
                        return false;
                    }
                    break;
                case ']':
                    if (deque.size() == 0 || deque.removeLast() != '[') {
                        return false;
                    }
                    break;
                case ')':
                    if (deque.size() == 0 || deque.removeLast() != '(') {
                        return false;
                    }
            }
        }
        return deque.size() == 0;
    }
}
