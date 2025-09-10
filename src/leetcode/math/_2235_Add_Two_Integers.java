package leetcode.math;

/*
Description:
    Given two integers num1 and num2, return the sum of the two integers.


Examples:
    Example 1:
        Input: num1 = 12, num2 = 5
        Output: 17
        Explanation: 12 + 5 = 17.

    Example 2:
        Input: num1 = -10, num2 = 4
        Output: -6
        Explanation: -10 + 4 = -6.


Constraints:
    -100 <= num1, num2 <= 100
*/
public class _2235_Add_Two_Integers {

    /**
     * <h2>2235. Add Two Integers — elementary arithmetic</h2>
     *
     * <h3>Goal / Problem summary</h3>
     * Return the sum of two small integers.
     *
     * <h3>Constraints / Assumptions</h3>
     * <ul>
     *   <li>−100 ≤ num1, num2 ≤ 100 → no risk of integer overflow.</li>
     *   <li>Inputs are standard 32-bit Java ints.</li>
     * </ul>
     *
     * <h3>Approach</h3>
     * Direct addition with the {@code +} operator.
     *
     * <h3>Algorithm</h3>
     * <ol>
     *   <li>Compute {@code num1 + num2}.</li>
     *   <li>Return the result.</li>
     * </ol>
     *
     * <h3>Correctness / Key Invariant</h3>
     * Integer addition is closed under the given bounds; the returned value equals the mathematical sum.
     *
     * <h3>Complexity</h3>
     * Time O(1), Space O(1).
     *
     * <h3>Edge Cases &amp; Pitfalls</h3>
     * <ul>
     *   <li>None under given constraints; overflow cannot occur.</li>
     * </ul>
     *
     * <h3>Implementation Notes</h3>
     * <ul>
     *   <li>Static inner {@code Solution} class for LeetCode submission style.</li>
     *   <li>Readability-first, braces on all control statements (not applicable here).</li>
     * </ul>
     *
     * <h3>Alternatives / Variants</h3>
     * Bitwise addition (using XOR and carry) is unnecessary for this task.
     */
    static class Solution {
        public int sum(int num1, int num2) {
            return num1 + num2;
        }
    }
}
