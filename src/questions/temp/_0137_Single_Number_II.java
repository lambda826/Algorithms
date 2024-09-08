/**
 * @author Yunxiang He
 * @date Jan 3, 2018 4:38:00 PM
 */

package questions.temp;

/*

Given an array of integers, every element appears three times except for one, which appears exactly once. 
Find that single one.

Note:
Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?

 */

public class _0137_Single_Number_II {

    /**
     * Optimal solution:
     * Bit manipulation (^ & ~)
     * --------------------------------------------------------------
     * Steps:
     * Two variables (ones, twos) are needed
     * The corresponding bit can represent 4 status (00, 01, 10, 11)
     * In this problem, we only need three status (ones:tows -> 0:0, 1:0, 0:1)
     * --------------------------------------------------------------
     * Example:
     *    num:0001     1st        2st        3st
     *   ones:0000     0001       0000       0000
     *   twos:0000     0000       0001       0000
     * --------------------------------------------------------------
     *  Time complexity : O(n)
     * Space complexity : O(1)
     * --------------------------------------------------------------
     * Other solutions:
     * HashMap, int []
     */
    public int singleNumber(int[] nums) {
        int ones = 0;
        int twos = 0;
        for (int num : nums) {
            ones ^= num & ~twos;
            twos ^= num & ~ones;
        }
        return ones;
    }

    public static void main(String[] args) {
        _0137_Single_Number_II test = new _0137_Single_Number_II();
        int[] nums = new int[] { 2, 2, 2, 3, 3, 3, 4 };
        test.singleNumber(nums);
    }
}
