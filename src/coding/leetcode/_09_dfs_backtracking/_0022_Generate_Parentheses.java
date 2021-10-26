package coding.leetcode._07_DFS_BackTracking;

import java.util.ArrayList;
import java.util.List;

/*

Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.


Example 1:
    Input:
        n = 3
    Output:
        ["((()))","(()())","(())()","()(())","()()()"]

Example 2:
    Input:
        n = 1
    Output:
        ["()"]


Constraints:
    1 <= n <= 8

*/

public class _0022_Generate_Parentheses {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Top-down:
    //      1. There are two options for each recursion, either append "(" or ")";
    //      1.1 To append "(", the left monitor has to less than the number of pairs;
    //      1.2 To append ")", the right monitor has to less than left monitor;
    public List<String> generateParenthesis(int n) {
        List<String> list = new ArrayList<>();
        helper(n, 0, 0, new StringBuilder(), list);
        return list;
    }

    private void helper(int n, int left, int right, StringBuilder tempCurr, List<String> list) {
        if (left == n && right == n) {
            list.add(tempCurr.toString());
        } else {
            if (left < n) {
                tempCurr.append("(");
                helper(n, left + 1, right, tempCurr, list);
                tempCurr.deleteCharAt(tempCurr.length() - 1);
            }
            if (right < left) {
                tempCurr.append(")");
                helper(n, left, right + 1, tempCurr, list);
                tempCurr.deleteCharAt(tempCurr.length() - 1);
            }
        }
    }
}