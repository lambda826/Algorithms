package questions._09_DFS_backtracking.combination._1d;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/*

Given a collection of candidate numbers (candidates) and a target number (target), find all unique combinations in candidates where the candidate numbers sum to target.

Each number in candidates may only be used once in the combination.

Note: The solution set must not contain duplicate combinations.


Example 1:
    Input:
        candidates = [10,1,2,7,6,1,5],
        target = 8
    Output:
        [
        [1,1,6],
        [1,2,5],
        [1,7],
        [2,6]
        ]

Example 2:
    Input:
        candidates = [2,5,2,1,2]
        target = 5
    Output:
        [
        [1,2,2],
        [5]
        ]


Constraints:
    1 <= candidates.length <= 100
    1 <= candidates[i] <= 50
    1 <= target <= 30
    
*/

public class _0040_Combination_Sum_II {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Duplication methodology:
    //      Based on prefix and current number:
    //          For the same prefix, if the current evaluated number is equal to previous one, then it will produce a duplicate;
    class Solution_Backtracking {

        public List<List<Integer>> combinationSum2(int[] candidates, int target) {
            List<List<Integer>> list = new ArrayList<>();
            Arrays.sort(candidates);
            helper(candidates, 0, target, new LinkedList<>(), list);
            return list;
        }

        private void helper(int[] candidates, int index, int target, LinkedList<Integer> curr, List<List<Integer>> list) {
            if (target == 0) {
                list.add(new ArrayList<>(curr));
            } else if (target > 0) {
                for (int i = index; i < candidates.length; ++i) {
                    // Deduplicate (branches):
                    // 1. We always iterate the first element;
                    // 2. For the second element, we skip if it is same as previous element;
                    //      2.1 The reason is that for the next recursion, the first element is same as the second element in previous recursion;
                    if (i == index || candidates[i] != candidates[i - 1]) {
                        curr.addLast(candidates[i]);
                        helper(candidates, i + 1, target - candidates[i], curr, list);
                        curr.removeLast();
                    }
                }
            }
        }
    }
}