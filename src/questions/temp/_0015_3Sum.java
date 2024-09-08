/**
 * @author Yunxiang He
 * @date 10/11/2017
 */

package questions.temp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*

Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0? 
Find all unique triplets in the array which gives the sum of zero.


Example:
    Given array nums = [-1, 0, 1, 2, -1, -4],
    A solution set is:
    [
      [-1, 0, 1],
      [-1, -1, 2]
    ]


Note:
    The solution set must not contain duplicate triplets.

*/

public class _0015_3Sum {

    public static void main(String[] args) {
        _0015_3Sum test = new _0015_3Sum();
        test.threeSum(new int[] { -4, -2, 1, -5, -4, -4, 4, -2, 0, 4, 0, -2, 3, 1, -5, 0 });
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.parallelSort(nums);
        for (int i = 0; i < nums.length - 2; ++i) {
            if (i == 0 || nums[i] != nums[i - 1]) {
                int l = i + 1;
                int r = nums.length - 1;
                while (l < r) {
                    if (nums[l] + nums[r] == -nums[i]) {
                        res.add(Arrays.asList(nums[i], nums[l++], nums[r--]));
                        while (l < r && nums[l] == nums[l - 1]) {
                            ++l;
                        }
                        while (l < r && nums[r] == nums[r + 1]) {
                            --r;
                        }
                    } else if (nums[l] + nums[r] < -nums[i]) {
                        ++l;
                    } else {
                        --r;
                    }
                }
            }
        }
        return res;
    }
}
