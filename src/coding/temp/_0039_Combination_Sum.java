/**
 *  @author: Yunxiang He
 *  @date  : 2018-06-27
 */

package coding.temp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*

Given a set of candidate numbers (candidates) (without duplicates) and a target number (target), find all unique combinations in candidates where the candidate numbers sums to target.

The same repeated number may be chosen from candidates unlimited number of times.


Example 1:
    Input: candidates = [2,3,6,7], target = 7,
    A solution set is:
    [
      [7],
      [2,2,3]
    ]

Example 2:
    Input: candidates = [2,3,5], target = 8,
    A solution set is:
    [
      [2,2,2,2],
      [2,3,3],
      [3,5]
    ]


Note:
    All numbers (including target) will be positive integers.
    The solution set must not contain duplicate combinations.

*/

public class _0039_Combination_Sum {

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.parallelSort(candidates);
        List<List<Integer>> list = new ArrayList<>();
        DFS(0, candidates, new ArrayList<>(), list, target);
        return list;
    }

    private void DFS(int i, int[] candidates, List<Integer> temp, List<List<Integer>> list, int target) {
        if (target == 0) {
            list.add(new ArrayList<>(temp));
        } else if (i != candidates.length) {
            if (target > 0) {
                // Choose candidates[i]
                temp.add(candidates[i]);
                DFS(i, candidates, temp, list, target - candidates[i]);
                temp.remove(temp.size() - 1);
                // Skip candidates[i]
                DFS(i + 1, candidates, temp, list, target);
            }
        }
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> list = new ArrayList<>();
        DFS(list, candidates, 0, new ArrayList<>(), target);
        return list;
    }

    private void DFS(List<List<Integer>> list, int[] candidates, int index, List<Integer> temp, int target) {
        for (int i = index; i < candidates.length; i++) {
            if (target == candidates[i]) {
                temp.add(candidates[i]);
                list.add(new ArrayList<>(temp));
                temp.remove(temp.size() - 1);
            } else if (target > candidates[i]) {
                temp.add(candidates[i]);
                DFS(list, candidates, i, temp, target - candidates[i]);
                temp.remove(temp.size() - 1);
            }
        }
    }
}
