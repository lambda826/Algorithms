/**
 * @author Yunxiang He
 * @date 11/24/2018
 */

package questions.lintcode;

/*

Description
    Given a non-negative integer, you could swap two digits at most once to get the maximum valued number. Return the maximum valued number you could get.
    The given number is in the range [0, 10^8]
    Have you met this question in a real interview?  


Example
Example 1:
    Input: 2736
    Output: 7236
    Explanation: Swap the number 2 and the number 7.

Example 2:
    Input: 9973
    Output: 9973
    Explanation: No swap.

*/

public class __1095_Maximum_Swap {

    //    public static void main(String[] args) {
    //        __1095_Maximum_Swap __1095_Maximum_Swap = new __1095_Maximum_Swap();
    //        System.out.println(__1095_Maximum_Swap.maximumSwap(931604));
    //    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //    public int maximumSwap(int num) {
    //        int temp = num;
    //        int digits = 0;
    //        while (temp > 0) {
    //            digits++;
    //            temp /= 10;
    //        }
    //        temp = num;
    //        int[] max = new int[digits + 1];
    //        int[] nums = new int[digits];
    //        for (int i = nums.length - 1; i >= 0; i--) {
    //            nums[i] = temp % 10;
    //            max[i] = Math.max(max[i + 1], nums[i]);
    //            temp /= 10;
    //        }
    //
    //    }

}
