/**
 *  @author: Yunxiang He
 *  @date  : 2018-06-27
 */

package coding.temp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*

Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent.

A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.


Example:
    Input: "23"
    Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].

Note:
    Although the above answer is in lexicographical order, your answer could be in any order you want.

*/

public class _0017_Letter_Combinations_of_a_Phone_Number {

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private Map<Character, String> map = new HashMap<Character, String>();
    private List<String> res = new ArrayList<>();

    public List<String> letterCombinations(String digits) {
        if (!digits.isEmpty()) {
            map.put('2', "abc");
            map.put('3', "def");
            map.put('4', "ghi");
            map.put('5', "jkl");
            map.put('6', "mno");
            map.put('7', "pqrs");
            map.put('8', "tuv");
            map.put('9', "wxyz");
            DFS(0, digits, new StringBuilder());
        }
        return res;
    }

    private void DFS(int index, String digits, StringBuilder sb) {
        if (index == digits.length()) {
            res.add(sb.toString());
        } else {
            for (char ch : map.get(digits.charAt(index)).toCharArray()) {
                sb.append(ch);
                DFS(index + 1, digits, sb);
                sb.deleteCharAt(sb.length() - 1);
            }
        }
    }
}
