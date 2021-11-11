/**
 *  @author: Yunxiang He
 *  @date  : 2018-07-14 01:13
 */

package questions.temp;

import java.util.ArrayList;
import java.util.List;

/*

A self-dividing number is a number that is divisible by every digit it contains.

For example, 128 is a self-dividing number because 128 % 1 == 0, 128 % 2 == 0, and 128 % 8 == 0.

Also, a self-dividing number is not allowed to contain the digit zero.

Given a lower and upper number bound, output a list of every possible self dividing number, including the bounds if possible.

Example 1:
Input: 
left = 1, right = 22
Output: [1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 12, 15, 22]
Note:

The boundaries of each input argument are 1 <= left <= right <= 10^4.

*/

public class _0728_Self_Dividing_Numbers {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public List<Integer> selfDividingNumbers(int left, int right) {
        List<Integer> res = new ArrayList<>();
        outter:
        while (left <= right) {
            int num = left;
            while (num > 0) {
                int rem = num % 10;
                if (rem != 0 && left % rem == 0) {
                    num /= 10;
                } else {
                    left++;
                    continue outter;
                }
            }
            res.add(left);
            left++;
        }
        return res;
    }
}
