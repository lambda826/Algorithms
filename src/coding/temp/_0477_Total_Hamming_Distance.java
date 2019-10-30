/**
 *  @author Yunxiang He
 *  @date Jan 4, 2018 11:39:42 PM
 */

package coding.temp;

/*

The Hamming distance between two integers is the number of positions at which the corresponding bits are different.
Now your job is to find the total Hamming distance between all pairs of the given numbers.

Example:
Input: 4, 14, 2

Output: 6

Explanation: 
In binary representation, the 4 is 0100, 14 is 1110, and 2 is 0010 (just showing the four bits relevant in this case). 
So the answer will be: HammingDistance(4, 14) + HammingDistance(4, 2) + HammingDistance(14, 2) = 2 + 2 + 2 = 6.

Note:
Elements of the given array are in the range of 0 to 10^9
Length of the array will not exceed 10^4.

 */

public class _0477_Total_Hamming_Distance {

    /**
     * Solution:
     * Bit manipulation
     * --------------------------------------------------------------
     * Tips:
     * The total of hamming distance at a certain bit is the number of ones times the numbers of zeros
     * The total of the nums is all bits (within 32bits)
     * --------------------------------------------------------------
     *  Time complexity : O(n)
     * Space complexity : O(1)
     */
    public int totalHammingDistance(int[] nums) {
        int sum = 0;
        int n = nums.length;
        for (int i = 0; i < 32; i++) {
            int ones = 0;
            for (int j = 0; j < n; j++) {
                ones += nums[j] & 1;
                nums[j] >>= 1;
            }
            sum += ones * (n - ones);
        }
        return sum;
    }
}
