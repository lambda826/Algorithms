/**
 *  @author Yunxiang He
 *  @date 06/23/2018
 */

package coding.temp;

import java.util.ArrayList;
import java.util.List;

/*

Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.


For example
    given n = 3, a solution set is:
    [
      "((()))",
      "(()())",
      "(())()",
      "()(())",
      "()()()"
    ]

*/

public class _0022_Generate_Parentheses {

    public static void main(String[] args) {
        new _0022_Generate_Parentheses().generateParenthesis_DFS(3);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public List<String> generateParenthesis_DFS(int n) {
        List<String> list = new ArrayList<>();
        DFS(n, n, list, new StringBuilder());
        return list;
    }

    private void DFS(int left, int right, List<String> list, StringBuilder sb) {
        if (left == 0 && right == 0) {
            list.add(sb.toString());
        } else {
            if (left > 0) {
                sb.append('(');
                DFS(left - 1, right, list, sb);
                sb.deleteCharAt(sb.length() - 1);
            }
            if (right > 0 && left < right) {
                sb.append(')');
                DFS(left, right - 1, list, sb);
                sb.deleteCharAt(sb.length() - 1);
            }
        }
    }
}
