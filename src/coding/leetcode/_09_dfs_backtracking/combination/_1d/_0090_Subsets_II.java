package coding.leetcode._09_dfs_backtracking.combination._1d;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


/*

Given an integer array nums that may contain duplicates, return all possible subsets (the power set).
The solution set must not contain duplicate subsets. Return the solution in any order.


Example 1:
    Input:
        nums = [1,2,2]
    Output:
        [[],[1],[1,2],[1,2,2],[2],[2,2]]

Example 2:
    Input:
        nums = [0]
    Output:
        [[],[0]]


Constraints:
    1 <= nums.length <= 10
    -10 <= nums[i] <= 10

*/

public class _0090_Subsets_II {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    class Solution {
        public List<List<Integer>> subsetsWithDup(int[] nums) {
            Arrays.sort(nums);
            List<List<Integer>> res = new ArrayList<>();
            helper(nums, 0, new LinkedList<>(), res);
            return res;
        }

        private void helper(int[] nums, int index, LinkedList<Integer> current, List<List<Integer>> res) {
            res.add(new LinkedList<>(current));
            for (int i = index; i < nums.length; ++i) {
                if (i == index || nums[i] != nums[i - 1]) {
                    current.add(nums[i]);
                    helper(nums, i + 1, current, res);
                    current.removeLast();
                }
            }
        }
    }
}