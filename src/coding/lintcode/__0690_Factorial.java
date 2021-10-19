/**
 *  @author Yunxiang He
 *  @date 02/20/2019
 */

package coding.lintcode;

import java.util.ArrayList;
import java.util.List;

/*

Given a number n, return the factorial of the number as a string.


Example
    Given a number n = 20
    return 2432902008176640000

*/

public class __0690_Factorial {

    // Related
    coding.leetcode._02_string._0043_Multiply_Strings _0043_Multiply_Strings = null;

    public static void main(String[] args) {
        __0690_Factorial test = new __0690_Factorial();
        System.out.println();
        int n = 100;
        System.out.println(test.factorial(n));
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public String factorial(int n) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        for (int i = 2; i <= n; ++i) {
            multiply(i, list);
        }
        StringBuilder sb = new StringBuilder();
        for (int digit : list) {
            sb.insert(0, digit);
        }
        return sb.toString();
    }

    private void multiply(int o1, List<Integer> list) {
        int i1 = 0;
        int _o1 = o1;
        while (_o1 > 0) {
            _o1 /= 10;
        }
        int i2;
        int carry;
        int prod;
        i2 = 0;
        carry = 0;
        while (i2 < list.size()) {
            prod = o1 * list.get(i2) + carry;
            carry = prod / 10;
            prod = prod % 10;
            list.set(i1 + i2, prod);
            ++i2;
        }
        while (carry > 0) {
            list.add(carry % 10);
            carry /= 10;
        }
        i1++;
    }
}
