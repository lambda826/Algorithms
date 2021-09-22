/**
 *  @author: Yunxiang He
 *  @date  : 2018-06-26 23:54
 */

package coding.leetcode.temp;

/*

Given a non-empty array of integers, return the third maximum number in this array. 
If it does not exist, return the maximum number. 
The time complexity must be in O(n).

Example 1:
Input: [3, 2, 1]
Output: 1
Explanation: The third maximum is 1.

Example 2:
Input: [1, 2]
Output: 2
Explanation: The third maximum does not exist, so the maximum (2) is returned instead.

Example 3:
Input: [2, 2, 3, 1]
Output: 1
Explanation: Note that the third maximum here means the third maximum distinct number.
Both numbers with value 2 are both considered as second maximum.

*/

public class _0414_Third_Maximum_Number {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int thirdMax(int[] nums) {
        long max1 = Long.MIN_VALUE;
        long max2 = Long.MIN_VALUE;
        long max3 = Long.MIN_VALUE;
        for (Integer n : nums) {
            if (n > max1) {
                max3 = max2;
                max2 = max1;
                max1 = n;
            } else if (n < max1 && n > max2) {
                max3 = max2;
                max2 = n;
            } else if (n < max2 && n > max3) {
                max3 = n;
            }
        }
        return (int) (max3 == Long.MIN_VALUE ? max1 : max3);
    }

    public static void main(String[] args) {
        System.out.println(new _0414_Third_Maximum_Number().thirdMax(new int[] { 3, 2, 1 }));
    }

}
