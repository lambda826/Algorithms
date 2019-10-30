/**
 *  @author Yunxiang He
 *  @date Jan 4, 2018 11:22:05 PM
 */

package coding.temp;

/*

The Hamming distance between two integers is the number of positions at which the corresponding bits are different.

Given two integers x and y, calculate the Hamming distance.

Note:
0 ≤ x, y < 231.

Example:

Input: x = 1, y = 4

Output: 2

Explanation:
1   (0 0 0 1)
4   (0 1 0 0)
       ↑   ↑

The above arrows point to positions where the corresponding bits are different.

*/

public class _0461_Hamming_Distance {

    /**
     * Solution:
     * Bit manipulation
     * --------------------------------------------------------------
     * Steps:
     * XOR computes the different bits
     * AND 1 to check whether the current bit is 1
     * Right shit
     * --------------------------------------------------------------
     *  Time complexity : O(n)
     * Space complexity : O(1)
     */
    public int hammingDistance(int x, int y) {
        int XOR = x ^ y;
        int count = 0;
        for (int i = 0; i < 32; i++) {
            count += (XOR) & 1;
            XOR >>= 1;
        }
        return count;
    }
}
