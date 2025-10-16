package leetcode.math;

import java.util.ArrayList;
import java.util.List;

/*
Description:
    The set [1, 2, 3, ..., n] contains a total of n! unique permutations.
    By listing and labeling all permutations in lexicographic order, we can define a permutation sequence.
    Given n and k, return the k-th permutation sequence of numbers [1..n].


Examples:
    Example 1:
        Input: n = 3, k = 3
        Output: "213"

    Example 2:
        Input: n = 4, k = 9
        Output: "2314"

    Example 3:
        Input: n = 3, k = 1
        Output: "123"


Constraints:
    1 <= n <= 9
    1 <= k <= n!
*/
public class _0060_Permutation_Sequence {

    /**
     * <h2>60. Permutation Sequence — Factorial Number System</h2>
     *
     * <h3>Goal/Problem summary</h3>
     * Return the k-th permutation (1-indexed) of the numbers [1..n] when all n! permutations
     * are listed in lexicographic order.
     *
     * <h3>Approach</h3>
     * <ul>
     *   <li>Use factorial grouping: the first (n-1)! permutations share the same first digit.</li>
     *   <li>Precompute factorials to know group sizes for each position.</li>
     *   <li>Use integer division/modulo on k to select digits iteratively without generating all permutations.</li>
     * </ul>
     *
     * <h3>Algorithm</h3>
     * <ol>
     *   <li>Compute factorials {@code fact[i] = i!} for i in [0..n].</li>
     *   <li>Maintain a list {@code nums = [1..n]} of remaining digits.</li>
     *   <li>Convert k to zero-based index ({@code k--}).</li>
     *   <li>For each position from most significant to least:
     *       <ul>
     *         <li>Determine group index {@code idx = k / fact[i]};</li>
     *         <li>Append {@code nums.get(idx)} to the result;</li>
     *         <li>Remove that element from {@code nums};</li>
     *         <li>Update {@code k %= fact[i]};</li>
     *       </ul>
     *   </li>
     * </ol>
     *
     * <h3>Complexity</h3>
     * <ul>
     *   <li>Time: O(n²) — removing from ArrayList costs O(n) per step, but n ≤ 9.</li>
     *   <li>Space: O(n) — factorial array and digits list.</li>
     * </ul>
     *
     * <h3>Edge Cases</h3>
     * <ul>
     *   <li>n = 1 → always returns "1".</li>
     *   <li>k = n! → returns the last permutation.</li>
     * </ul>
     */
    static class Solution {
        public String getPermutation(int n, int k) {
            List<Integer> nums = new ArrayList<>();
            int[] fact = new int[n];
            fact[0] = 1;
            nums.add(1);

            for (int i = 1; i < n; i++) {
                nums.add(i + 1);
                fact[i] = fact[i - 1] * i;
            }

            StringBuilder sb = new StringBuilder();
            k--; // convert to 0-indexed

            for (int i = n - 1; i >= 0; i--) {
                int idx = k / fact[i];
                sb.append(nums.get(idx));
                nums.remove(idx);
                k %= fact[i];
            }

            return sb.toString();
        }
    }
}
