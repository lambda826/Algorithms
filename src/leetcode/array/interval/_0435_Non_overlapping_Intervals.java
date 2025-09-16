package leetcode.array.interval;

import java.util.Arrays;
import java.util.Comparator;

/*
Description:
    Given an array of intervals where intervals[i] = [starti, endi],
    return the minimum number of intervals you need to remove to make the rest
    of the intervals non-overlapping.

    Note that intervals which only touch at a point are non-overlapping.
    For example, [1,2] and [2,3] are non-overlapping.


Examples:
    Example 1:
        Input: intervals = [[1,2],[2,3],[3,4],[1,3]]
        Output: 1
        Explanation: [1,3] can be removed and the rest of the intervals are non-overlapping.

    Example 2:
        Input: intervals = [[1,2],[1,2],[1,2]]
        Output: 2
        Explanation: You need to remove two [1,2] to make the rest non-overlapping.

    Example 3:
        Input: intervals = [[1,2],[2,3]]
        Output: 0
        Explanation: They are already non-overlapping.


Constraints:
    1 <= intervals.length <= 10^5
    intervals[i].length == 2
    -5 * 10^4 <= starti < endi <= 5 * 10^4
*/

public class _0435_Non_overlapping_Intervals {

    /**
     * <h2>435. Non-overlapping Intervals — sort by end, greedy keep</h2>
     *
     * <h3>Goal / Problem summary</h3>
     * Remove the minimum number of intervals so that the remaining intervals are pairwise non-overlapping.
     *
     * <h3>Constraints / Assumptions</h3>
     * <ul>
     *   <li>Touching at endpoints (end == nextStart) is allowed (not overlapping).</li>
     *   <li>Up to 1e5 intervals; coordinates within ±5e4.</li>
     * </ul>
     *
     * <h3>Approach (why this technique)</h3>
     * Classic activity-selection greedy: sort by end time ascending and keep every interval whose start
     * is at or after the end of the last kept interval. Anything that overlaps must be removed.
     * Sorting by end maximizes how many we keep ⇒ minimizes removals.
     *
     * <h3>Algorithm</h3>
     * <ol>
     *   <li>Sort intervals by end ascending.</li>
     *   <li>Initialize {@code removals = 0}, {@code prevEnd = -∞}.</li>
     *   <li>For each interval [s, e] in order:
     *     <ul>
     *       <li>If {@code s >= prevEnd}, keep it and set {@code prevEnd = e}.</li>
     *       <li>Else, it overlaps → {@code removals++} (we drop the current interval; since it's sorted by end it ends no later than a future choice).</li>
     *     </ul>
     *   </li>
     *   <li>Return {@code removals}.</li>
     * </ol>
     *
     * <h3>Correctness / Key Invariant</h3>
     * Greedily keeping the earliest-finishing compatible interval leaves maximal room for future intervals,
     * yielding the maximum number kept and thus the minimum removed.
     *
     * <h3>Complexity</h3>
     * Time {@code O(n log n)} for sort + {@code O(n)} scan; Space {@code O(1)} extra.
     *
     * <h3>Edge Cases &amp; Pitfalls</h3>
     * <ul>
     *   <li>Use {@code s >= prevEnd} (not {@code >}) so touching intervals are kept.</li>
     *   <li>Avoid “cnt = -1” hacks—skip them by sorting by end and counting removals directly.</li>
     * </ul>
     *
     * <h3>Implementation Notes</h3>
     * <ul>
     *   <li>Readable names, braces on all control statements.</li>
     * </ul>
     *
     * <h3>Alternatives / Variants</h3>
     * Sorting by start and tracking the running minimum end also works but is easier to get wrong.
     */
    static class Solution {
        public int eraseOverlapIntervals(int[][] intervals) {
            Arrays.sort(intervals, Comparator.comparingInt(a -> a[1]));

            int removals = 0;
            int prevEnd = Integer.MIN_VALUE;

            for (int[] it : intervals) {
                int s = it[0], e = it[1];
                if (s >= prevEnd) {
                    // keep this interval
                    prevEnd = e;
                } else {
                    // overlap with last kept → remove current
                    removals++;
                }
            }
            return removals;
        }
    }
}
