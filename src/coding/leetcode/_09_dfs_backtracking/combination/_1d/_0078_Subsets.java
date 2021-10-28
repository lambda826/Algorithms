package coding.leetcode._09_dfs_backtracking.combination._1d;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/*

Given an integer array nums of unique elements, return all possible subsets (the power set).
The solution set must not contain duplicate subsets. Return the solution in any order.


Example 1:
    Input:
        nums = [1,2,3]
    Output:
        [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]

Example 2:
    Input:
        nums = [0]
    Output:
        [[],[0]]


Constraints:
    1 <= nums.length <= 10
    -10 <= nums[i] <= 10
    All the numbers of nums are unique.

*/

public class _0078_Subsets {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    class Solution {
        public List<List<Integer>> subsets(int[] nums) {
            List<List<Integer>> res = new ArrayList<>();
            helper(nums, 0, new LinkedList<>(), res);
            return res;
        }

        private void helper(int[] nums, int index, LinkedList<Integer> current, List<List<Integer>> res) {
            if (index == nums.length) {
                res.add(new LinkedList<>(current));
            } else {
                current.add(nums[index]);
                helper(nums, index + 1, current, res);
                current.removeLast();
                helper(nums, index + 1, current, res);
            }
        }
    }
}
