/**
 * @author Yunxiang He
 * @date 12/11/2017
 */

package questions.temp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*

Given an array nums of n integers and an integer target, are there elements a, b, c, and d in nums such that a + b + c + d = target? 
Find all unique quadruplets in the array which gives the sum of target.


Example:
    Given array nums = [1, 0, -1, 0, -2, 2], and target = 0.
    
    A solution set is:
    [
      [-1,  0, 0, 1],
      [-2, -1, 1, 2],
      [-2,  0, 0, 2]
]


Note:
    The solution set must not contain duplicate quadruplets.
    
*/

public class _0018_4Sum {

    public static void main(String[] args) {
        int[] nums = new int[] { -1, -5, -5, -3, 2, 5, 0, 4 };
        int target = -7;
        _0018_4Sum test = new _0018_4Sum();
        List<List<Integer>> resList = test.fourSum(nums, target);
        System.out.println(resList);

    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.parallelSort(nums);
        for (int i = 0; i < nums.length - 3; ++i) {
            if (i == 0 || nums[i] != nums[i - 1]) {
                for (int j = i + 1; j < nums.length - 2; ++j) {
                    if (j == i + 1 || nums[j] != nums[j - 1]) {
                        int k = target - nums[j] - nums[i];
                        int l = j + 1;
                        int r = nums.length - 1;
                        while (l < r) {
                            if (nums[l] + nums[r] == k) {
                                res.add(Arrays.asList(nums[i], nums[j], nums[l++], nums[r--]));
                                while (l < r && nums[l] == nums[l - 1]) {
                                    ++l;
                                }
                                while (l < r && nums[r] == nums[r + 1]) {
                                    --r;
                                }
                            } else if (nums[l] + nums[r] < k) {
                                ++l;
                            } else {
                                --r;
                            }
                        }
                    }
                }
            }
        }
        return res;
    }

}
