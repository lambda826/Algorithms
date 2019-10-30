/**
 *  @author Yunxiang He
 *  @date Jan 29, 2018 9:55:28 PM
 */

package coding.temp;

import java.util.ArrayList;
import java.util.List;

/*

Given a non-negative integer numRows, generate the first numRows of Pascal's triangle.

In Pascal's triangle, each number is the sum of the two numbers directly above it.

Example:

Input: 5
Output:
[
     [1],
    [1,1],
   [1,2,1],
  [1,3,3,1],
 [1,4,6,4,1]
]

 */

public class _0118_Pascals_Triangle {

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        if (numRows == 0) {
            return res;
        }
        List<Integer> up = new ArrayList<>();
        up.add(1);
        res.add(up);
        for (int row = 2; row < numRows + 1; row++) {
            List<Integer> curr = new ArrayList<>();
            curr.add(1);
            int index = 1;
            while (index < row - 1) {
                curr.add(up.get(index - 1) + up.get(index));
                index++;
            }
            curr.add(1);
            res.add(curr);
            up = curr;
        }
        return res;
    }

    public static void main(String[] args) {
        new _0118_Pascals_Triangle().generate(5);

    }
}
