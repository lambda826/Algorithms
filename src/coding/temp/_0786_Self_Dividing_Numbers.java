/**
 *  @author Yunxiang He
 *  @date Jan 12, 2018 7:04:34 PM
 */

package coding.temp;

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

The boundaries of each input argument are 1 <= left <= right <= 10000.

 */

public class _0786_Self_Dividing_Numbers {
    public List<Integer> selfDividingNumbers(int left, int right) {
        List<Integer> resList = new ArrayList<Integer>();
        if (left > right) {
            return resList;
        }
        for (int i = left; i <= right; i++) {
            int k = i;
            while (k > 0) {
                if (k == 0 || k % 10 == 0 || i % (k % 10) != 0) {
                    break;
                }
                k /= 10;
            }
            if (k == 0) {
                resList.add(i);
            }
        }
        return resList;
    }

    public static void main(String[] args) {
        System.out.println(10 % 10);
        _0786_Self_Dividing_Numbers test = new _0786_Self_Dividing_Numbers();
        test.selfDividingNumbers(1, 22);
    }
}
