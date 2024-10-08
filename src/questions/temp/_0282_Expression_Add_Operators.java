/**
 * @author: Yunxiang He
 * @date : 2018-06-27
 */

package questions.temp;

import java.util.ArrayList;
import java.util.List;

/*

Given a string that contains only digits 0-9 and a target value, return all possibilities to add binary operators (not unary) +, -, or * between the digits so they evaluate to the target value.


Example 1:
Input: num = "123", target = 6
Output: ["1+2+3", "1*2*3"] 

Example 2:
Input: num = "232", target = 8
Output: ["2*3+2", "2+3*2"]

Example 3:
Input: num = "105", target = 5
Output: ["1*0+5","10-5"]

Example 4:
Input: num = "00", target = 0
Output: ["0+0", "0-0", "0*0"]

Example 5:
Input: num = "3456237490", target = 9191
Output: []


*/

public class _0282_Expression_Add_Operators {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public List<String> addOperators2(String num, int target) {
        List<String> rst = new ArrayList<String>();
        if (num == null || num.length() == 0) {
            return rst;
        }
        helper(rst, "", num, target, 0, 0, 0);
        return rst;
    }

    public void helper(List<String> rst, String path, String num, int target, int pos, long eval, long multed) {
        if (pos == num.length()) {
            if (target == eval) {
                rst.add(path);
            }
            return;
        }
        for (int i = pos; i < num.length(); i++) {
            if (i != pos && num.charAt(pos) == '0') {
                break;
            }
            long cur = Long.parseLong(num.substring(pos, i + 1));
            if (pos == 0) {
                helper(rst, path + cur, num, target, i + 1, cur, cur);
            } else {
                helper(rst, path + "+" + cur, num, target, i + 1, eval + cur, cur);

                helper(rst, path + "-" + cur, num, target, i + 1, eval - cur, -cur);

                helper(rst, path + "*" + cur, num, target, i + 1, eval - multed + multed * cur, multed * cur);
            }
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public List<String> addOperators(String num, int target) {
        List<String> list = new ArrayList<>();
        dfs(num, target, 0, 0, new StringBuilder(), list);
        return list;
    }

    private void dfs(String num, int target, int index, int currNum, StringBuilder sb, List<String> list) {
        if (index == num.length()) {
            if (target == currNum) {
                sb.deleteCharAt(0);
                list.add(sb.toString());
            }
        } else {
            int n = Character.getNumericValue(num.charAt(index));
            sb.append("+").append(n);
            dfs(num, target, index + 1, currNum + n, sb, list);
            sb.deleteCharAt(sb.length() - 1).deleteCharAt(sb.length() - 1);

            sb.append("*").append(n);
            dfs(num, target, index + 1, currNum * n, sb, list);
            sb.deleteCharAt(sb.length() - 1).deleteCharAt(sb.length() - 1);

            sb.append("-").append(n);
            dfs(num, target, index + 1, currNum - n, sb, list);
            sb.deleteCharAt(sb.length() - 1).deleteCharAt(sb.length() - 1);

        }
    }

    public static void main(String[] args) {
        new _0282_Expression_Add_Operators().addOperators("123", 6);
    }
}
