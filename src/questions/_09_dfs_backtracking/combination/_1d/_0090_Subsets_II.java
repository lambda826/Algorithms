package questions._09_DFS_backtracking.combination._1d;

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
    // Refer - https://blog.csdn.net/liushu1231/article/details/38516701?utm_medium=distribute.pc_aggpage_search_result.none-task-blog-2~aggregatepage~first_rank_ecpm_v1~rank_aggregation-10-38516701.pc_agg_rank_aggregation&utm_term=%E6%8E%92%E5%88%97%E7%BB%84%E5%90%88%E6%80%8E%E4%B9%88%E7%90%86%E8%A7%A3&spm=1000.2123.3001.4430
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