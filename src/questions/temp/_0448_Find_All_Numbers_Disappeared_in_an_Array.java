/**
 * @author Yunxiang He
 * @date Jan 29, 2018 9:55:28 PM
 */

package questions.temp;

import java.util.ArrayList;
import java.util.List;

/*

Given an array of integers where 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.

Find all the elements of [1, n] inclusive that do not appear in this array.

Could you do it without extra space and in O(n) runtime? 
You may assume the returned list does not count as extra space.

Example:

Input:
[4,3,2,7,8,2,3,1]

Output:
[5,6]

 */

public class _0448_Find_All_Numbers_Disappeared_in_an_Array {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> res = new ArrayList<Integer>();

        for (int i = 0; i < nums.length; i++) {
            int p = Math.abs(nums[i]) - 1;
            nums[p] = -Math.abs(nums[p]);
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                res.add(i + 1);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = new int[] { 4, 3, 2, 7, 8, 2, 3, 1 };
        new _0448_Find_All_Numbers_Disappeared_in_an_Array().findDisappearedNumbers(nums);
    }
}
