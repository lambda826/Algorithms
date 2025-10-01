package leetcode.dfs.permutation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
Description:
    Given a collection of numbers, nums, that might contain duplicates,
    return all possible unique permutations in any order.


Example 1:
    Input:
        nums = [1,1,2]
    Output:
        [[1,1,2],[1,2,1],[2,1,1]]

Example 2:
    Input:
        nums = [1,2,3]
    Output:
        [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]


Constraints:
    1 <= nums.length <= 8
    -10 <= nums[i] <= 10
*/

public class _0047_Permutations_II {

    /**
     * <h2>47. Permutations II — DFS backtracking with duplicate skipping</h2>
     *
     * <h3>Goal / Problem summary</h3>
     * Generate all unique permutations of an integer array that can contain duplicates.
     *
     * <h3>Constraints / Assumptions</h3>
     * <ul>
     *   <li>1 ≤ n ≤ 8 → factorial growth is manageable.</li>
     *   <li>Array can contain duplicates; output must not repeat permutations.</li>
     * </ul>
     *
     * <h3>Approach</h3>
     * Sort the array first so that equal numbers are adjacent. During DFS,
     * when considering index {@code i}, skip it if {@code nums[i] == nums[i-1]} and the previous
     * {@code i-1} is not used in the current path; this prevents generating the same permutation
     * multiple times at the same recursion depth.
     *
     * <h3>Algorithm</h3>
     * <ol>
     *   <li>Sort {@code nums}.</li>
     *   <li>Use a boolean {@code used[]} to mark which indices are in the current path.</li>
     *   <li>DFS: if path size equals {@code n}, add a copy to result.</li>
     *   <li>Otherwise iterate {@code i = 0..n-1}:
     *     <ul>
     *       <li>Skip if {@code used[i]} is true.</li>
     *       <li>Skip if {@code i > 0 && nums[i] == nums[i-1] && !used[i-1]} (duplicate pruning).</li>
     *       <li>Choose {@code nums[i]}, recurse, then backtrack.</li>
     *     </ul>
     *   </li>
     * </ol>
     *
     * <h3>Correctness / Key Invariant</h3>
     * Sorting ensures equal values are contiguous; requiring the previous equal value to be used
     * before the current one at a given depth enforces a canonical order and removes duplicates.
     *
     * <h3>Complexity</h3>
     * Time {@code O(n * n!)} to build all unique permutations in the worst case;
     * Space {@code O(n)} recursion stack plus {@code O(n)} for {@code used[]} (excluding output).
     *
     * <h3>Edge Cases &amp; Pitfalls</h3>
     * <ul>
     *   <li>Remember to copy the path when adding to results.</li>
     *   <li>Pruning condition uses {@code !used[i-1]} (same-level duplicates), not {@code used[i-1]}.</li>
     * </ul>
     *
     * <h3>Implementation Notes</h3>
     * <ul>
     *   <li>One variable per declaration (project style).</li>
     *   <li>Braces on all control statements; descriptive names.</li>
     * </ul>
     *
     * <h3>Alternatives / Variants</h3>
     * In-place swap with a HashSet per depth to avoid repeating the same value at that position.
     */
    static class Solution {
        public List<List<Integer>> permuteUnique(int[] nums) {
            Arrays.sort(nums);
            List<List<Integer>> res = new ArrayList<>();
            List<Integer> path = new ArrayList<>();
            boolean[] visited = new boolean[nums.length];
            dfs(path, res, nums, visited);
            return res;
        }

        private void dfs(List<Integer> path, List<List<Integer>> res, int[] nums, boolean[] visited) {
            if (path.size() == nums.length) {
                res.add(new ArrayList<>(path));
            } else {
                int pre = Integer.MIN_VALUE;
                for (int i = 0; i < nums.length; i++) {
                    if (!visited[i] && nums[i] != pre) {
                        pre = nums[i];
                        visited[i] = true;
                        path.add(nums[i]);
                        // Or use the canonical pruning instead of 'pre':
                        // if (i > 0 && nums[i] == nums[i - 1] && !visited[i - 1]) {
                        //     visited[i] = false;
                        //     continue;
                        // }
                        dfs(path, res, nums, visited);
                        path.removeLast(); // ArrayList backtrack
                        visited[i] = false;
                    }
                }
            }
        }
    }
}
