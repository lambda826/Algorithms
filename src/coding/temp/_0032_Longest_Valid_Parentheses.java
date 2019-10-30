/**
 *  @author Yunxiang He
 *  @date 06/25/2018
 */

package coding.temp;

import java.util.ArrayDeque;
import java.util.Deque;

/*

Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.

Example 1:
Input: "(()"
Output: 2
Explanation: The longest valid parentheses substring is "()"

Example 2:
Input: ")()())"
Output: 4
Explanation: The longest valid parentheses substring is "()()"

*/

public class _0032_Longest_Valid_Parentheses {

    public static void main(String[] args) {
        System.out.println(new _0032_Longest_Valid_Parentheses().longestValidParentheses_dp(")()())"));
        System.out.println(new _0032_Longest_Valid_Parentheses().longestValidParentheses(")()())"));
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // If the current is ')' then
    //   If previous is '(', dp[i] = 2 + dp[i - 2];
    //   Or if previous is ')' 
    public int longestValidParentheses_dp(String s) {
        int max = 0;
        int dp[] = new int[s.length() + 1];
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == ')') {
                if (s.charAt(i - 1) == '(') {
                    dp[i + 1] = dp[i - 1] + 2;
                } else if (i - dp[i] - 1 > 0 && s.charAt(i - dp[i] - 1) == '(') {
                    dp[i + 1] = 2 * dp[i] + 2;
                }
                max = Math.max(max, dp[i + 1]);
            }
        }
        return max;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int longestValidParentheses(String s) {
        int maxans = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.offerLast(i);
            } else {
                if (stack.isEmpty()) {
                    stack.offerLast(i);
                }
                maxans = Math.max(maxans, i - stack.pollLast() + 1);
            }
        }
        return maxans;
    }

    //    public int longestValidParentheses(String s) {
    //        Deque<String> stack = new ArrayDeque<>();
    //        int max = 0;
    //        for (char ch : s.toCharArray()) {
    //            if (ch == '(') {
    //                stack.offerLast("(");
    //            } else {
    //                if (stack.peekLast() != null) {
    //                    int num = 0;
    //                    if (stack.peekLast() != "(") {
    //                        num = Integer.parseInt(stack.pollLast()); // Pop the num
    //                    }
    //                    if (stack.peekLast() == "(") {
    //                        num += 2;
    //                        stack.pollLast(); // Pop the "("
    //                        if (stack.peekLast() != "(" && stack.peekLast() != null) { // if the front of the popped "(" is a num
    //                            num += Integer.parseInt(stack.pollLast());
    //                        }
    //                        stack.offerLast(String.valueOf(num));
    //                    }
    //                    max = Math.max(max, num);
    //                }
    //            }
    //        }
    //        return max;
    //    }

}
