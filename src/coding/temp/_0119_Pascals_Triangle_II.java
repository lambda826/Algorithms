/**
 *  @author: Yunxiang He
 *  @date  : 2018-06-25 21:06
 */

package coding.temp;

import java.util.ArrayList;
import java.util.List;

/*

Given a non-negative index k where k ≤ 33, return the kth index row of the Pascal's triangle.

Note that the row index starts from 0.

In Pascal's triangle, each number is the sum of the two numbers directly above it.

Example:
Input: 3
Output: [1,3,3,1]

Follow up:
Could you optimize your algorithm to use only O(k) extra space?

*/

public class _0119_Pascals_Triangle_II {

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // [C(k,0), C(k,1), ..., C(k, k-1), C(k, k)]
    // C(a, b) = C(a, b - 1) * (a - b + 1) / b
    public List<Integer> getRow_Math(int rowIndex) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        for (int i = 1; i < rowIndex + 1; i++) {
            list.add((int) ((long) list.get(i - 1) * (rowIndex - (i - 1)) / (i)));
        }
        return list;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public List<Integer> getRow(int rowIndex) {
        List<Integer> res = new ArrayList<>();
        res.add(1);
        for (int row = 1; row < rowIndex + 1; row++) {
            for (int j = row - 1; j > 0; j--) {
                res.set(j, res.get(j - 1) + res.get(j));
            }
            res.add(1);
        }
        return res;
    }

    public static void main(String[] args) {
        //        System.out.println(new _0119_Pascals_Triangle_II().getRow(3));
        System.out.println(new _0119_Pascals_Triangle_II().getRow_Math(3));
    }
}
