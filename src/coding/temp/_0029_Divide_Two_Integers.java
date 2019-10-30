/**
 *  @author Yunxiang He
 *  @date Jan 16, 2018 7:03:28 PM
 */

package coding.temp;

/*

Divide two integers without using multiplication, division and mod operator.

If it is overflow, return MAX_INT.

 */

public class _0029_Divide_Two_Integers {

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Need to use negative to avoid overflow
    public int divide_Math(int dividend, int divisor) {
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        } else if (divisor == 1) {
            return dividend;
        } else if (dividend > 0) {
            return -divide_Math(-dividend, divisor);
        } else if (divisor > 0) {
            return -divide_Math(dividend, -divisor);
        } else {
            return divideAux(dividend, divisor);
        }
    }

    private int divideAux(int dividend, int divisor) {
        if (dividend > divisor) {
            return 0;
        }
        int count = 1;
        int _divisor = divisor;
        while (_divisor << 1 < 0 && (_divisor << 1) > dividend) {
            _divisor <<= 1;
            count <<= 1;
        }
        return count + divideAux(dividend - _divisor, divisor);
    }

    public static void main(String[] args) {
        _0029_Divide_Two_Integers test = new _0029_Divide_Two_Integers();
        int dividend = -2147483648;

        int divisor = 2;
        System.out.println(test.divide_Math(dividend, divisor));
        System.out.println(dividend / divisor);

        System.out.println(-7 << 33);
    }
}
