/**
 *  @author: Yunxiang He
 *  @date  : 2018-06-27
 */

package questions.temp;

import java.util.ArrayList;
import java.util.List;

/*


Given an array of integers, 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.

Find all the elements that appear twice in this array.

Could you do it without extra space and in O(n) runtime?


Example:
    Input:
    [4,3,2,7,8,2,3,1]
    Output:
    [2,3]

*/

public class _0442_Find_All_Duplicates_in_an_Array {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            int index = Math.abs(nums[i]) - 1;
            // If nums[index] is -, then it is the second time to encounter
            if (nums[index] < 0) {
                list.add(index + 1);
            }
            // Flip nums[index] from + to -, from - to +
            nums[index] = -nums[index];
        }
        return list;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public List<Integer> findDuplicates2(int[] nums) {
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {

            while (nums[i] != i + 1) {
                int temp = nums[nums[i] - 1];
                nums[nums[i] - 1] = nums[i];
            }
        }
        return list;
    }

    public static void main(String[] args) {
        System.out.println(new _0442_Find_All_Duplicates_in_an_Array().findDuplicates(new int[] { 10, 2, 5, 10, 9, 1, 1, 4, 3, 7 }));
    }
}
