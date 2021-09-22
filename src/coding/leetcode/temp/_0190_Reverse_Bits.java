/**
 *  @author Yunxiang He
 *  @date Jan 5, 2018 12:11:50 AM
 */

package coding.leetcode.temp;

/*

Reverse bits of a given 32 bits unsigned integer.

For example, given input 43261596 (represented in binary as 00000010100101000001111010011100), return 964176192 (represented in binary as 00111001011110000010100101000000).

Follow up:
If this function is called many times, how would you optimize it?

*/

public class _0190_Reverse_Bits {

    /**
     * Optimal solution:
     * Bit manipulation
     * --------------------------------------------------------------
     *  Time complexity : O(n)
     * Space complexity : O(1)
     */
    public int reverseBits(int n) {
        int res = 0;
        for (int i = 31; i >= 0; i--) {
            res += (n & 1) << i;
            n >>= 1;
        }
        return res;
    }

    public static void main(String[] args) {

        System.out.println(new _0190_Reverse_Bits().reverseBits(0));
        // Output: 964176192
    }
}
