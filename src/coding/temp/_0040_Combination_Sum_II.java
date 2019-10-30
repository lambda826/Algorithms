/**
 *  @author: Yunxiang He
 *  @date  : 2018-06-27
 */

package coding.temp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*

Given a collection of candidate numbers (candidates) and a target number (target), find all unique combinations in candidates where the candidate numbers sums to target.

Each number in candidates may only be used once in the combination.


Example 1:
    Input: candidates = [10,1,2,7,6,1,5], target = 8,
    A solution set is:
    [
      [1, 7],
      [1, 2, 5],
      [2, 6],
      [1, 1, 6]
    ]
    
Example 2:
    Input: candidates = [2,5,2,1,2], target = 5,
    A solution set is:
    [
      [1,2,2],
      [5]
    ]


Note:
    All numbers (including target) will be positive integers.
    The solution set must not contain duplicate combinations.
    
*/

public class _0040_Combination_Sum_II {

    public static void main(String[] args) {
        _0040_Combination_Sum_II test = new _0040_Combination_Sum_II();
        test.combinationSum2(new int[] { 2, 5, 2, 1, 2 }, 5);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> list = new ArrayList<>();
        DFS(0, candidates, new ArrayList<>(), list, target);
        return list;
    }

    private void DFS(int i, int[] candidates, List<Integer> temp, List<List<Integer>> list, int target) {
        if (target == 0) {
            List<Integer> temp2 = new ArrayList<>(temp);
            Collections.sort(temp2);
            if (!list.contains(temp2)) {
                list.add(new ArrayList<>(temp2));
            }
        } else if (i != candidates.length) {
            if (target > 0) {
                // Choose candidates[i]
                temp.add(candidates[i]);
                DFS(i + 1, candidates, temp, list, target - candidates[i]);
                temp.remove(temp.size() - 1);
                // Skip candidates[i]
                DFS(i + 1, candidates, temp, list, target);
            }
        }
    }
}
