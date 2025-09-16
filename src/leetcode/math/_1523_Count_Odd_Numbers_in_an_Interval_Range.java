package leetcode.math;

/*
Description:
    Given two non-negative integers low and high, return the count of odd numbers
    between low and high (inclusive).


Examples:
    Example 1:
        Input: low = 3, high = 7
        Output: 3
        Explanation: The odd numbers between 3 and 7 are [3,5,7].

    Example 2:
        Input: low = 8, high = 10
        Output: 1
        Explanation: The odd numbers between 8 and 10 are [9].


Constraints:
    0 <= low <= high <= 10^9
*/
public class _1523_Count_Odd_Numbers_in_an_Interval_Range {

    /**
     * <h2>1523. Count Odd Numbers in an Interval Range — arithmetic formula</h2>
     *
     * <h3>Goal / Problem summary</h3>
     * Return the number of odd integers in the inclusive range [low, high].
     *
     * <h3>Constraints / Assumptions</h3>
     * <ul>
     *   <li>0 ≤ low ≤ high ≤ 1e9.</li>
     *   <li>Fits in 32-bit int; no overflow issues with the chosen formula.</li>
     * </ul>
     *
     * <h3>Approach</h3>
     * Use the count of odds ≤ x: ⌊(x + 1)/2⌋. Then
     * {@code odds(low..high) = odds(≤high) − odds(<low) = (high+1)/2 − (low)/2}.
     *
     * <h3>Algorithm</h3>
     * <ol>
     *   <li>Compute {@code (high + 1) / 2 - low / 2} using integer division.</li>
     *   <li>Return the result.</li>
     * </ol>
     *
     * <h3>Correctness / Key Invariant</h3>
     * Every pair of consecutive integers contributes exactly one odd number;
     * the formula counts how many odd positions are within bounds.
     *
     * <h3>Complexity</h3>
     * Time O(1), Space O(1).
     *
     * <h3>Edge Cases &amp; Pitfalls</h3>
     * <ul>
     *   <li>Works for low == high (single value) and large bounds up to 1e9.</li>
     *   <li>No need for branching on parity when using the closed-form.</li>
     * </ul>
     *
     * <h3>Implementation Notes</h3>
     * Readability-first; explicit formula avoids loops/conditionals.
     *
     * <h3>Alternatives / Variants</h3>
     * A parity-branch version: {@code (high - low) / 2 + ((low % 2 == 1 || high % 2 == 1) ? 1 : 0)}.
     */
    static class Solution {
        public int countOdds(int low, int high) {
            return (high + 1) / 2 - low / 2;
        }
    }
}
