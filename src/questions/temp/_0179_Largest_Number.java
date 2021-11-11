/**
 *  @author Yunxiang He
 *  @date 06/22/2018
 */

package questions.temp;

import java.util.Arrays;

/*

Given a list of non negative integers, arrange them such that they form the largest number.


Example 1:
    Input: [10,2]
    Output: "210"

Example 2:
    Input: [3,30,34,5,9]
    Output: "9534330"


Note: 
    The result may be very large, so you need to return a string instead of an integer.

*/

public class _0179_Largest_Number {

    public static void main(String[] args) {
        _0179_Largest_Number test = new _0179_Largest_Number();
        test.largestNumber(new int[] { 3, 30, 34, 5, });
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // a + b > b + a, b + c > c + b => a + c > c + a
    public String largestNumber(int[] nums) {
        String[] strs = new String[nums.length];
        for (int i = 0; i < nums.length; ++i) {
            strs[i] = String.valueOf(nums[i]);
        }
        Arrays.sort(strs, (a, b) -> (b + a).compareTo(a + b));
        if (strs[0].charAt(0) == '0') {
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        for (String str : strs) {
            sb.append(str);
        }
        return sb.toString();
    }
}
