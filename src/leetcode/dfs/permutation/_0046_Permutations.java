package leetcode.dfs.permutation;

import java.util.ArrayList;
import java.util.List;

/*
Description:
    Given an array nums of distinct integers, return all the possible permutations.
    You can return the answer in any order.


Examples:
    Example 1:
        Input:
            nums = [1,2,3]
        Output:
            [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]

    Example 2:
        Input:
            nums = [0,1]
        Output:
            [[0,1],[1,0]]

    Example 3:
        Input:
            nums = [1]
        Output:
            [[1]]


Constraints:
    1 <= nums.length <= 6
    -10 <= nums[i] <= 10
    All the integers of nums are unique.
*/

public class _0046_Permutations {

    /**
     * <h2>46. Permutations — DFS backtracking</h2>
     *
     * <h3>Goal / Problem summary</h3>
     * Generate all permutations of a small array of distinct integers.
     *
     * <h3>Constraints / Assumptions</h3>
     * <ul>
     *   <li>Array length up to 6 → factorial growth manageable.</li>
     *   <li>All numbers are distinct (no need to skip duplicates).</li>
     * </ul>
     *
     * <h3>Approach</h3>
     * Standard backtracking with a <em>path</em> list and a <em>used[]</em> boolean array to mark which
     * elements are already chosen in the current partial permutation.
     *
     * <h3>Algorithm</h3>
     * <ol>
     *   <li>Create an empty result list and a path list.</li>
     *   <li>Use {@code used[i]} to indicate whether {@code nums[i]} is in the path.</li>
     *   <li>DFS: if path size equals {@code n}, add a copy to result.</li>
     *   <li>Otherwise, iterate over indices; if not used, choose it, recurse, then backtrack.</li>
     * </ol>
     *
     * <h3>Correctness / Key Invariant</h3>
     * At depth {@code d}, the path contains exactly {@code d} distinct elements from {@code nums},
     * each index used at most once; recursion explores all orderings without repetition.
     *
     * <h3>Complexity</h3>
     * Time {@code O(n * n!)} to build all permutations; Space {@code O(n)} recursion + {@code O(n!)} output.
     *
     * <h3>Edge Cases &amp; Pitfalls</h3>
     * <ul>
     *   <li>Remember to copy the path into result (avoid aliasing).</li>
     *   <li>Backtrack by undoing both {@code used[i]} and the path append.</li>
     * </ul>
     *
     * <h3>Implementation Notes</h3>
     * <ul>
     *   <li>One variable per declaration (project style).</li>
     *   <li>Braces on all control statements; descriptive names.</li>
     * </ul>
     *
     * <h3>Alternatives / Variants</h3>
     * In-place swap-from-index method also works and avoids the {@code used[]} array.
     */
    static class Solution {
        public List<List<Integer>> permute(int[] nums) {
            List<List<Integer>> res = new ArrayList<>();
            dfs(new ArrayList<>(), res, nums, new boolean[nums.length]);
            return res;
        }

        private void dfs(List<Integer> path, List<List<Integer>> res, int[] nums, boolean[] visited) {
            if (path.size() == nums.length) {
                res.add(new ArrayList<>(path));
            } else {
                for (int i = 0; i < nums.length; ++i) {
                    if (!visited[i]) {
                        visited[i] = true;
                        path.add(nums[i]);
                        dfs(path, res, nums, visited);
                        path.removeLast();
                        visited[i] = false;
                    }
                }
            }
        }
    }
}
