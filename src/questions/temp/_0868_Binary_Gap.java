/**
 * @author: Yunxiang He
 * @date : 2018-07-16 08:52
 */

package questions.temp;

/*

Given a positive integer N, find and return the longest distance between two consecutive 1's in the binary representation of N.

If there aren't two consecutive 1's, return 0.

Example 1:
Input: 22
Output: 2
Explanation: 
22 in binary is 0b10110.
In the binary representation of 22, there are three ones, and two consecutive pairs of 1's.
The first consecutive pair of 1's have distance 2.
The second consecutive pair of 1's have distance 1.
The answer is the largest of these two distances, which is 2.

Example 2:
Input: 5
Output: 2
Explanation: 
5 in binary is 0b101.

Example 3:
Input: 6
Output: 1
Explanation: 
6 in binary is 0b110.

Example 4:
Input: 8
Output: 0
Explanation: 
8 in binary is 0b1000.
There aren't any consecutive pairs of 1's in the binary representation of 8, so we return 0.

Note:
1 <= N <= 10^9

*/

public class _0868_Binary_Gap {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int binaryGap(int N) {
        int first = -1;
        int max = 0;
        int i = 0;
        while (N > 0) {
            if ((N & 1) == 1) {
                if (first == -1) {
                    first = i;
                } else {
                    max = Math.max(max, i - first);
                    first = i;
                }
            }
            N >>= 1;
            i++;
        }
        return max;
    }
}
