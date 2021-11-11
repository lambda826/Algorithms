/**
 *  @author Yunxiang He
 *  @date Jan 3, 2018 8:17:01 PM
 */

package questions.temp;

/*

Given an array of numbers nums, in which exactly two elements appear only once and all the other elements appear exactly twice. 
Find the two elements that appear only once.

For example:

Given nums = [1, 2, 1, 3, 2, 5], return [3, 5].

Note:
The order of the result is not important. So in the above example, [5, 3] is also correct.
Your algorithm should run in linear runtime complexity. Could you implement it using only constant space complexity?

Credits:
Special thanks to @jianchao.li.fighter for adding this problem and creating all test cases.

 */

public class _0260_Single_Number_III {

    /**
     * Optimal solution:
     * Bit manipulation
     * --------------------------------------------------------------
     *  Time complexity : O(n)
     * Space complexity : O(1)
     */
    public int[] singleNumber(int[] nums) {
        int diff = 0;
        // Get the XOR fo the two wanted numbers
        for (int num : nums) {
            diff ^= num;
        }
        // Find the difference between of the two numbers
        // Example: 3(0011), 5(0101)
        // a:  (3 ^ 5) = 0110
        // b: -(3 ^ 5) = 1010
        // a & b = 10 (this is one of the different bits between 3 and 5)
        diff &= -diff;
        int[] res = new int[2];
        for (int num : nums) {
            if ((num & diff) == 0) {
                res[0] ^= num;
            } else {
                res[1] ^= num;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(Integer.toBinaryString(3));
        System.out.println(Integer.toBinaryString(5));
        System.out.println(Integer.toBinaryString(3 ^ 5));
        System.out.println(Integer.toBinaryString(-(3 ^ 5)));
    }
}
