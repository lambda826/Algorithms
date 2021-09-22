/**
 *  @author Yunxiang He
 *  @date Oct 12, 2017 5:02:53 PM
 */
package coding.leetcode.temp;

/*

Given a positive integer, output its complement number. 
The complement strategy is to flip the bits of its binary representation.

Note:
The given integer is guaranteed to fit within the range of a 32-bit signed integer.
You could assume no leading zero bit in the integer’s binary representation.

Example 1:
Input: 5
Output: 2
Explanation: The binary representation of 5 is 101 (no leading zero bits), and its complement is 010. So you need to output 2.

Example 2:
Input: 1
Output: 0
Explanation: The binary representation of 1 is 1 (no leading zero bits), and its complement is 0. So you need to output 0.

*/

public class _0476_Number_Complement {

    /**
     * Solution:
     * Bit manipulation
     * --------------------------------------------------------------
     *  Time complexity : O(n)
     * Space complexity : O(1)
     */
    public int findComplement(int num) {
        return (Integer.highestOneBit(num) << 1) - 1 - num;
    }

    public static void main(String[] args) {
        _0476_Number_Complement test = new _0476_Number_Complement();
        System.out.println(test.findComplement(5));

        System.out.println(Integer.highestOneBit(5));
    }
}
