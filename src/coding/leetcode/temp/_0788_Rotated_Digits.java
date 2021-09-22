package coding.leetcode.temp;

/*

X is a good number if after rotating each digit individually by 180 degrees, we get a valid number that is different from X.  
Each digit must be rotated - we cannot choose to leave it alone.

A number is valid if each digit remains a digit after rotation. 
0, 1, and 8 rotate to themselves; 2 and 5 rotate to each other; 6 and 9 rotate to each other, and the rest of the numbers do not rotate to any other number and become invalid.

Now given a positive number N, how many numbers X from 1 to N are good?


Example:
    Input: 10
    Output: 4
    Explanation: 
        There are four good numbers in the range [1, 10] : 2, 5, 6, 9.
        Note that 1 and 10 are not good numbers, since they remain unchanged after rotating.


Note:
    N  will be in range [1, 10000].

*/

public class _0788_Rotated_Digits {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int rotatedDigits(int N) {
        int[] A = { 1, 2, 3, 3, 3, 4, 5, 5, 6, 7, };
        int[] B = { 1, 2, 2, 2, 2, 2, 2, 2, 3, 3, };
        int a9 = 1;
        int b9 = 1;
        int total1 = 1;
        int total2 = 0;
        while (N > 0) {
            int rem = N % 10;
            if (rem == 9 || rem == 6 || rem == 5 || rem == 2) {
                total2 = total1;
            } else if (rem == 7 || rem == 4 || rem == 3) {
                total1 = 0;
                total2 = 0;
            }
            if (rem != 0) {
                total1 += A[rem - 1] * a9;
                total2 += A[rem - 1] * a9 - B[rem - 1] * b9;
            }
            a9 *= 7;
            b9 *= 3;
            N /= 10;
        }
        return total2;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int rotatedDigits2(int N) {
        int res = 0;
        for (int i = 1; i <= N; i++) {
            if (check(i)) {
                res++;
            }
        }
        return res;
    }

    private boolean check(int x) {
        boolean isRoate = false;
        while (x > 0) {
            int lsb = x % 10;
            if (lsb == 3 || lsb == 7 || lsb == 4) {
                return false;
            } else if (lsb == 2 || lsb == 5 || lsb == 6 || lsb == 9) {
                isRoate = true;
            }
            x /= 10;
        }
        return isRoate;
    }

    public static void main(String[] args) {
        System.out.println(new _0788_Rotated_Digits().rotatedDigits(1));
    }
}
