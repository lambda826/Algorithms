package coding.leetcode._02_string.parentheses;

import java.util.ArrayDeque;
import java.util.Deque;

/*

Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.


Example 1:
    Input:
        s = "(()"
    Output:
        2
    Explanation:
        The longest valid parentheses substring is "()".

Example 2:
    Input:
        s = ")()())"
    Output:
        4
    Explanation:
        The longest valid parentheses substring is "()()".

Example 3:
    Input:
        s = ""
    Output:
        0


Constraints:
    0 <= s.length <= 3 * 10^4
    s[i] is '(', or ')'.

*/

public class _0032_Longest_Valid_Parentheses {


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Find index of valid parentheses
    class Solution {

        public int longestValidParentheses(String s) {
            boolean[] visited = new boolean[s.length()];
            Deque<Integer> deque = new ArrayDeque<>();
            for (int i = 0; i < s.length(); ++i) {
                if (s.charAt(i) == '(') {
                    deque.offerLast(i);
                } else if (s.charAt(i) == ')') {
                    if (!deque.isEmpty()) {
                        visited[deque.pollLast()] = true;
                        visited[i] = true;
                    }
                }
            }
            int max = 0;
            int count = 0;
            for (int i = 0; i < visited.length; ++i) {
                if (visited[i]) {
                    ++count;
                    max = Math.max(max, count);
                } else {
                    count = 0;
                }
            }
            return max;
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // If the current is ')' then
    //   If previous is '(', dp[i] = 2 + dp[i - 2];
    //   Or if previous is ')' 
    class Solution_DP {

        public int longestValidParentheses(String s) {
            int max = 0;
            int dp[] = new int[s.length()];
            for (int i = 1; i < s.length(); ++i) {
                if (s.charAt(i) == ')') {
                    if (s.charAt(i - 1) == '(') {
                        // If previous character is '(', dp[i] equals to 2 plus the dp value for previous ')'
                        dp[i] = 2 + (i - 2 >= 0 ? dp[i - 2] : 0);
                    } else {
                        // Else find the earliest '(', plus the dp value for previous ')'
                        int open = i - dp[i - 1] - 1;
                        int preClose = open - 1;
                        if (open >= 0 && s.charAt(open) == '(') {
                            dp[i] = dp[i - 1] + 2 + (preClose >= 0 ? dp[preClose] : 0);
                        }
                    }
                    max = Math.max(max, dp[i]);
                }
            }
            return max;
        }
    }

}