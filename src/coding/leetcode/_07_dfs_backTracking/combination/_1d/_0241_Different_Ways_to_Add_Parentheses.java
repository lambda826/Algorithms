package coding.leetcode._07_dfs_backTracking.combination._1d;

/*

Given a string expression of numbers and operators, return all possible results from computing all the different possible ways to group numbers and operators.
You may return the answer in any order.


Example 1:
    `Input:
        expression = "2-1-1"
    Output:
        [0,2]
    Explanation:
        ((2-1)-1) = 0
        (2-(1-1)) = 2`

Example 2:
    Input:
        expression = "2*3-4*5"
    Output:
        [-34,-14,-10,-10,10]
    Explanation:
        (2*(3-(4*5))) = -34
        ((2*3)-(4*5)) = -14
        ((2*(3-4))*5) = -10
        (2*((3-4)*5)) = -10
        (((2*3)-4)*5) = 10


Constraints:
    1 <= expression.length <= 20
    expression consists of digits and the operator '+', '-', and '*'.

*/

import java.util.ArrayList;
import java.util.List;

public class _0241_Different_Ways_to_Add_Parentheses {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    class Solution_BackTracking_Memo {
        public List<Integer> diffWaysToCompute(String expression) {
            List<String> bags = new ArrayList<>();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < expression.length(); ++i) {
                if (Character.isDigit(expression.charAt(i))) {
                    sb.append(expression.charAt(i));
                } else {
                    bags.add(sb.toString());
                    sb.setLength(0);
                    bags.add(String.valueOf(expression.charAt(i)));
                }
            }
            bags.add(sb.toString());
            return helper(bags, 0, bags.size() - 1, new List[bags.size()][bags.size()]);
        }

        private List<Integer> helper(List<String> bag, int p1, int p2, List[][] memo) {
            if (memo[p1][p2] != null) {
                return memo[p1][p2];
            }
            List<Integer> res = new ArrayList<>();
            if (p1 == p2) {
                res.add(Integer.valueOf(bag.get(p1)));
            } else {
                for (int i = p1; i <= p2; ++i) {
                    if (bag.get(i).equals("+") || bag.get(i).equals("-") || bag.get(i).equals("*")) {
                        List<Integer> l1 = helper(bag, p1, i - 1, memo);
                        List<Integer> l2 = helper(bag, i + 1, p2, memo);
                        for (int num1 : l1) {
                            for (int num2 : l2) {
                                if (bag.get(i).equals("+")) {
                                    res.add(num1 + num2);
                                } else if (bag.get(i).equals("-")) {
                                    res.add(num1 - num2);
                                } else {
                                    res.add(num1 * num2);
                                }
                            }
                        }
                    }
                }
            }
            memo[p1][p2] = res;
            return memo[p1][p2];
        }
    }
}