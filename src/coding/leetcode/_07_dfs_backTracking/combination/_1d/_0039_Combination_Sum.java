package coding.leetcode._07_dfs_backTracking.combination._1d;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/*

Given an array of distinct integers candidates and a target integer target, return a list of all unique combinations of candidates where the chosen numbers sum to target.
You may return the combinations in any order.

The same number may be chosen from candidates an unlimited number of times.
Two combinations are unique if the frequency of at least one of the chosen numbers is different.

It is guaranteed that the number of unique combinations that sum up to target is less than 150 combinations for the given input.


Example 1:
    Input:
        candidates = [2,3,6,7],
        target = 7
    Output:
        [[2,2,3],[7]]
    Explanation:
        2 and 3 are candidates, and 2 + 2 + 3 = 7. Note that 2 can be used multiple times.
        7 is a candidate, and 7 = 7.
        These are the only two combinations.

Example 2:
    Input:
        candidates = [2,3,5],
        target = 8
    Output:
        [[2,2,2,2],[2,3,3],[3,5]]

Example 3:
    Input:
        candidates = [2],
        target = 1
    Output:
        []

Example 4:
    Input:
        candidates = [1],
        target = 1
    Output:
        [[1]]

Example 5:
    Input:
        candidates = [1],
        target = 2
    Output:
        [[1,1]]


Constraints:
    1 <= candidates.length <= 30
    1 <= candidates[i] <= 200
    All elements of candidates are distinct.
    1 <= target <= 500

*/

public class _0039_Combination_Sum {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        helper(candidates, target, 0, res, new LinkedList<>());
        return res;
    }

    private void helper(int[] candidates, int target, int start, List<List<Integer>> res, LinkedList<Integer> curr) {
        if (target == 0) {
            res.add(new ArrayList<>(curr));
        } else if (target > 0) {
            for (int i = start; i < candidates.length; ++i) {
                curr.addLast(candidates[i]);
                helper(candidates, target - candidates[i], i, res, curr); // Don't increase start index because the it allows duplicates
                curr.removeLast();
            }
        }
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.parallelSort(candidates);
        List<List<Integer>> list = new ArrayList<>();
        helper2(0, candidates, new ArrayList<>(), list, target);
        return list;
    }

    private void helper2(int i, int[] candidates, List<Integer> temp, List<List<Integer>> list, int target) {
        if (target == 0) {
            list.add(new ArrayList<>(temp));
        } else if (i != candidates.length) {
            if (target > 0) {
                // Choose candidates[i]
                temp.add(candidates[i]);
                helper2(i, candidates, temp, list, target - candidates[i]);
                temp.remove(temp.size() - 1);
                // Skip candidates[i]
                helper2(i + 1, candidates, temp, list, target);
            }
        }
    }
}