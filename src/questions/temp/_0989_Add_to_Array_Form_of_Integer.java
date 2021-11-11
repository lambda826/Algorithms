/**
 *  @author Yunxiang He
 *  @date 02/09/2019
 */

package questions.temp;

import java.util.ArrayList;
import java.util.List;

/*

For a non-negative integer X, the array-form of X is an array of its digits in left to right order.  For example, if X = 1231, then the array form is [1,2,3,1].

Given the array-form A of a non-negative integer X, return the array-form of the integer X+K.

 

Example 1:

Input: A = [1,2,0,0], K = 34
Output: [1,2,3,4]
Explanation: 1200 + 34 = 1234
Example 2:

Input: A = [2,7,4], K = 181
Output: [4,5,5]
Explanation: 274 + 181 = 455
Example 3:

Input: A = [2,1,5], K = 806
Output: [1,0,2,1]
Explanation: 215 + 806 = 1021
Example 4:

Input: A = [9,9,9,9,9,9,9,9,9,9], K = 1
Output: [1,0,0,0,0,0,0,0,0,0,0]
Explanation: 9999999999 + 1 = 10^50
 

Note：

1 <= A.length <= 10^4
0 <= A[i] <= 9
0 <= K <= 10^4
If A.length > 1, then A[0] != 0

*/

public class _0989_Add_to_Array_Form_of_Integer {

    public static void main(String[] args) {
        System.out.println(new _0989_Add_to_Array_Form_of_Integer().addToArrayForm(new int[] { 2, 1, 6 }, 806));
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public List<Integer> addToArrayForm(int[] A, int K) {
        List<Integer> B = new ArrayList<>();
        while (K != 0) {
            B.add(0, K % 10);
            K /= 10;
        }
        int i = A.length - 1;
        int j = B.size() - 1;
        List<Integer> res = new ArrayList<>();
        int carry = 0;
        int sum;
        while (i >= 0 || j >= 0) {
            sum = (i < 0 ? 0 : A[i]) + (j < 0 ? 0 : B.get(j)) + carry;
            carry = sum / 10;
            K /= 10;
            res.add(0, sum % 10);
            --i;
            --j;
        }
        if (carry == 1) {
            res.add(0, 1);
        }
        return res;
    }
}
