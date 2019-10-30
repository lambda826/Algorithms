/**
 *  @author Yunxiang He
 *  @date 03/21/2019
 */

package coding.temp;

import java.util.ArrayList;
import java.util.List;

/*

Remove the minimum number of invalid parentheses in order to make the input string valid. Return all possible results.


Example 1:
    Input: "()())()"
    Output: ["()()()", "(())()"]

Example 2:
    Input: "(a)())()"
    Output: ["(a)()()", "(a())()"]

Example 3:
    Input: ")("
    Output: [""]


Note: 
    The input string may contain letters other than the parentheses ( and ).
    
*/

public class _0301_Remove_Invalid_Parentheses {

    public static void main(String[] args) {
        _0301_Remove_Invalid_Parentheses test = new _0301_Remove_Invalid_Parentheses();
        //        test.removeInvalidParentheses("()))(()()))");
        test.removeInvalidParentheses("())))");
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public List<String> removeInvalidParentheses(String s) {
        List<String> res = new ArrayList<>();
        DFS(res, s, 0, 0, new char[] { '(', ')' });
        return res;
    }

    private void DFS(List<String> res, String s, int start, int cutStart, char[] pair) {
        int count = 0;
        // find the fisrt invalid index ')'
        for (int i = start; i < s.length(); ++i) {
            if (s.charAt(i) == pair[0]) {
                ++count;
            } else if (s.charAt(i) == pair[1]) {
                --count;
            }
            if (count < 0) {
                // find a ')' to delete, then keep searching
                for (int j = cutStart; j <= i; ++j) { // j is the index to be cut
                    if (s.charAt(j) == pair[1] && (j == cutStart || s.charAt(j - 1) != pair[1])) { // previous char is letter || '('
                        DFS(res, s.substring(0, j) + s.substring(j + 1), i, j, pair);
                    }
                }
                return; // after serach, return directly
            }
        }
        String rvsd = new StringBuilder(s).reverse().toString();
        if (pair[0] == '(') {
            DFS(res, rvsd, 0, 0, new char[] { ')', '(' });
        } else {
            res.add(rvsd);
        }
    }
}
