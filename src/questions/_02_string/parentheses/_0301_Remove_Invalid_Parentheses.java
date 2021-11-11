package questions._02_string.parentheses;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*

Given a string s that contains parentheses and letters, remove the minimum number of invalid parentheses to make the input string valid.
Return all the possible results. You may return the answer in any order.


Example 1:
    Input:
        s = "()())()"
    Output:
        ["(())()","()()()"]

Example 2:
    Input:
        s = "(a)())()"
    Output:
        ["(a())()","(a)()()"]

Example 3:
    Input:
        s = ")("
    Output:
        [""]


Constraints:
    1 <= s.length <= 25
    s consists of lowercase English letters and parentheses '(' and ')'.
    There will be at most 20 parentheses in s.

*/

public class _0301_Remove_Invalid_Parentheses {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Record max length result set
    // For each character, there could be two options:
    //    1. Append it to the result;
    //    2. Skip (delete) it;
    // When done scanning the string, compare open and close counter to decide whether it's a valid result
    class Solution_Backtracking {
        int min = Integer.MAX_VALUE;

        public List<String> removeInvalidParentheses(String s) {
            Set<String> res = new HashSet<>();
            helper(s, 0, new StringBuilder(), 0, 0, res, 0);
            return new ArrayList<>(res);
        }

        private void helper(String s, int index, StringBuilder sb, int open, int close, Set<String> res, int count) {
            if (count <= min) {
                if (index == s.length() && open == close) {
                    if (count < min) {
                        min = count;
                        res.clear();
                        res.add(sb.toString());
                    } else if (count == min) {
                        res.add(sb.toString());
                    }
                } else if (index < s.length()) {
                    char ch = s.charAt(index);
                    if (ch == '(') {
                        // Append
                        sb.append(ch);
                        helper(s, index + 1, sb, open + 1, close, res, count);
                        sb.deleteCharAt(sb.length() - 1);
                        // Skip
                        helper(s, index + 1, sb, open, close, res, count + 1);
                    } else if (ch == ')') {
                        // Append
                        if (open > close) { // If close equals to open, then adding a ')' result an invalid result
                            sb.append(ch);
                            helper(s, index + 1, sb, open, close + 1, res, count);
                            sb.deleteCharAt(sb.length() - 1);
                        }
                        // Skip
                        helper(s, index + 1, sb, open, close, res, count + 1);
                    } else {
                        sb.append(ch);
                        helper(s, index + 1, sb, open, close, res, count);
                        sb.deleteCharAt(sb.length() - 1);
                    }
                }
            }
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // 1. Locate the first invalid close parentheses;
    // 2. Delete any invalid close parentheses within the range;
    // 3. Execute helper() with reversed string to delete invalid open parentheses;
    class Solution_DFS {

        public List<String> removeInvalidParentheses(String s) {
            List<String> res = new ArrayList<>();
            helper(s, 0, 0, new char[] { '(', ')' }, res);
            return res;
        }

        private void helper(String s, int start, int cut, char[] pair, List<String> res) {
            // 1. Locate the first invalid parentheses;
            int count = 0;
            for (int i = start; i < s.length(); ++i) {
                if (s.charAt(i) == pair[0]) {
                    ++count;
                } else if (s.charAt(i) == pair[1]) {
                    --count;
                }
                if (count < 0) {
                    // 2. Delete one invalid parentheses within the range;
                    for (int j = cut; j <= i; ++j) {
                        if (s.charAt(j) == pair[1] && (j == cut || s.charAt(j - 1) != s.charAt(j))) {
                            helper(s.substring(0, j) + s.substring(j + 1), i, j, pair, res);
                        }
                    }
                    return; // Need to return here to cut the recursive call
                }
            }
            if (pair[0] == '(') {
                // From above
                helper(new StringBuilder(s).reverse().toString(), 0, 0, new char[] { ')', '(' }, res);
            } else {
                // When the procedure reaches here, the number of open and close is equal;
                // We need to reverse the string again;
                res.add(new StringBuilder(s).reverse().toString());
            }
        }
    }
}