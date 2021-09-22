package coding.leetcode.temp;

/*

Given an integer n, return an array ans of length n + 1 such that for each i (0 <= i <= n), ans[i] is the number of 1's in the binary representation of i.



Example 1:

Input: n = 2
Output: [0,1,1]
Explanation:
0 --> 0
1 --> 1
2 --> 10
Example 2:

Input: n = 5
Output: [0,1,1,2,1,2]
Explanation:
0 --> 0
1 --> 1
2 --> 10
3 --> 11
4 --> 100
5 --> 101


Constraints:

0 <= n <= 105

*/

public class _0338_Counting_Bits {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Right shift to reuse dp[];
    // If the last digit is 1, then plus 1
    //                  0
    //                  1
    //              1   0
    //              1   1
    //          1   0   0
    //          1   0   1
    //          1   1   0
    //          1   1   1
    //      1   0   0   0
    //      1   0   0   1
    //      1   0   1   0
    //      1   0   1   1
    //      1   1   0   0
    //      1   1   0   1
    //      1   1   1   0
    //      1   1   1   1
    //  1   0   0   0   0
    public int[] countBits(int n) {
        int[] count = new int[n + 1];
        for (int i = 1; i <= n; ++i) {
            count[i] = (i & 1) + count[(i >>> 1)];
        }
        return count;
    }
}
