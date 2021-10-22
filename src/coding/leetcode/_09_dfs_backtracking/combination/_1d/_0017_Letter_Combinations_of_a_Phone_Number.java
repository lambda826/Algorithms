/**
 * @author: Yunxiang He
 * @date : 2018-06-27
 */

package coding.leetcode._09_dfs_backtracking.combination._1d;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*

Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent. Return the answer in any order.
A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.


Example 1:
    Input: digits = "23"
    Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]

Example 2:
    Input: digits = ""
    Output: []

Example 3:
    Input: digits = "2"
    Output: ["a","b","c"]


Constraints:
    0 <= digits.length <= 4
    digits[i] is a digit in the range ['2', '9'].

*/

public class _0017_Letter_Combinations_of_a_Phone_Number {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    class Solution {

        public List<String> letterCombinations(String digits) {
            List<String> res = new ArrayList<>();
            if (digits != null && digits.length() != 0) {
                Map<Character, String> map = new HashMap<>();
                map.put('2', "abc");
                map.put('3', "def");
                map.put('4', "ghi");
                map.put('5', "jkl");
                map.put('6', "mno");
                map.put('7', "pqrs");
                map.put('8', "tuv");
                map.put('9', "wxyz");
                helper(digits, 0, new StringBuilder(), res, map);
            }
            return res;
        }

        private void helper(String digits, int index, StringBuilder sb, List<String> res, Map<Character, String> map) {
            if (index == digits.length()) {
                res.add(sb.toString());
            } else {
                for (char ch : map.get(digits.charAt(index)).toCharArray()) {
                    sb.append(ch);
                    helper(digits, index + 1, sb, res, map);
                    sb.deleteCharAt(sb.length() - 1);
                }
            }
        }
    }
}