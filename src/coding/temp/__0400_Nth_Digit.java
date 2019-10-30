/**
 *  @author: Yunxiang He
 *  @date  : 2018-07-17 11:47
 */

package coding.temp;

/*

Find the nth digit of the infinite integer sequence 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ...

Note:
n is positive and will fit within the range of a 32-bit signed integer (n < 2^31).

Example 1:
Input:
3
Output:
3

Example 2:
Input:
11
Output:
0
Explanation:
The 11th digit of the sequence 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ... is a 0, which is part of the number 10.

*/

public class __0400_Nth_Digit {

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int findNthDigit(int n) {
        long i = 9;
        int digits = 1;
        while (n > i) {
            n -= i;
            i = 9 * (long) Math.pow(10, digits) * (digits + 1);
            digits++;
        }
        int rem = n % digits;
        int div = n / digits;
        int num = (int) Math.pow(10, digits - 1) + div - 1;
        if (rem > 0) {
            num++;
            return String.valueOf(num).charAt(rem - 1) - 48;
        } else {
            return String.valueOf(num).charAt(digits - 1) - 48;
        }
    }

    public static void main(String[] args) {
        System.out.println(new __0400_Nth_Digit().findNthDigit(1000000000));
    }
}
