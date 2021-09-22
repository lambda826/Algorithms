package coding.leetcode.temp;

import java.util.ArrayList;
import java.util.List;

/*

Given an integer numRows, return the first numRows of Pascal's triangle.

In Pascal's triangle, each number is the sum of the two numbers directly above it as shown:




Example 1:

Input: numRows = 5
Output: [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]
Example 2:

Input: numRows = 1
Output: [[1]]


Constraints:

1 <= numRows <= 30

*/

public class _0118_Pascals_Triangle {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> firstRow = new ArrayList<>();
        firstRow.add(1);
        res.add(firstRow);
        for (int i = 1; i < numRows; ++i) {
            List<Integer> row = new ArrayList<>();
            res.add(row);
            for (int j = 0; j <= i; ++j) {
                int m = (j - 1 >= 0) ? (res.get(i - 1).get(j - 1)) : 0;
                int n = (j < res.get(i - 1).size()) ? (res.get(i - 1).get(j)) : 0;
                row.add(m + n);
            }
        }
        return res;
    }
}
