/**
 *  @author: Yunxiang He
 *  @date  : 2018-06-27
 */

package coding.leetcode.temp;

import java.util.ArrayList;
import java.util.List;

/*

Given a set of distinct integers, nums, return all possible subsets (the power set).

Note: The solution set must not contain duplicate subsets.

Example:

Input: nums = [1,2,3]
Output:
[
  [3],
  [1],
  [2],
  [1,2,3],
  [1,3],
  [2,3],
  [1,2],
  []
]

*/

public class _0078_Subsets {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> subsets(int[] nums) {
        DFS(0, nums, new ArrayList<>());
        return res;
    }

    private void DFS(int start, int[] nums, List<Integer> temp) {
        if (start == nums.length) {
            res.add(new ArrayList<>(temp));
        } else if (start < nums.length) {
            temp.add(nums[start]);
            DFS(start + 1, nums, temp);
            temp.remove(temp.size() - 1);
            DFS(start + 1, nums, temp);
        }
    }
}
