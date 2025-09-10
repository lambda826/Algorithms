package leetcode.array.interval;

import java.util.ArrayList;
import java.util.List;

/*
Description:
    You are given an array of non-overlapping intervals where intervals[i] = [starti, endi]
    represent the start and the end of the i-th interval, and intervals is sorted in ascending order by starti.
    You are also given an interval newInterval = [start, end] that represents the start and end of another interval.

    Insert newInterval into intervals such that intervals is still sorted in ascending order by starti
    and intervals still does not have any overlapping intervals (merge overlapping intervals if necessary).

    Return intervals after the insertion.
    Note: you don’t need to modify intervals in-place. You can make a new array and return it.


Examples:
    Example 1:
        Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
        Output: [[1,5],[6,9]]

    Example 2:
        Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
        Output: [[1,2],[3,10],[12,16]]
        Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].


Constraints:
    0 <= intervals.length <= 10^4
    intervals[i].length == 2
    0 <= starti <= endi <= 10^5
    intervals is sorted by starti in ascending order.
    newInterval.length == 2
    0 <= start <= end <= 10^5
*/
public class _0057_Insert_Interval {

    /**
     * <h2>57. Insert Interval — sweep + merge</h2>
     *
     * <h3>Goal / Problem summary</h3>
     * Insert a new interval into a sorted, non-overlapping interval list
     * and merge if necessary.
     *
     * <h3>Constraints / Assumptions</h3>
     * <ul>
     *   <li>Intervals are sorted and disjoint.</li>
     *   <li>0 ≤ intervals.length ≤ 10^4.</li>
     * </ul>
     *
     * <h3>Approach</h3>
     * Scan through intervals:
     * <ul>
     *   <li>If current interval is completely before newInterval → output it.</li>
     *   <li>If completely after → output newInterval and reset newInterval to current interval.</li>
     *   <li>If overlapping → merge into newInterval by updating its bounds.</li>
     * </ul>
     *
     * <h3>Algorithm</h3>
     * <ol>
     *   <li>Initialize result list.</li>
     *   <li>Iterate over intervals with [s, e]:</li>
     *     <ul>
     *       <li>If e < start → add [s, e].</li>
     *       <li>Else if s > end → add newInterval, set newInterval = [s, e].</li>
     *       <li>Else (overlap) → merge: start = min(start, s), end = max(end, e).</li>
     *     </ul>
     *   <li>After loop, add last newInterval.</li>
     * </ol>
     *
     * <h3>Correctness / Key Invariant</h3>
     * Intervals are always kept sorted; merging ensures no overlaps remain.
     *
     * <h3>Complexity</h3>
     * Time O(n), Space O(n).
     *
     * <h3>Edge Cases & Pitfalls</h3>
     * <ul>
     *   <li>Empty input intervals.</li>
     *   <li>New interval before all / after all.</li>
     *   <li>Full overlap covering all intervals.</li>
     * </ul>
     *
     * <h3>Implementation Notes</h3>
     * <ul>
     *   <li>Static inner class for LeetCode compatibility.</li>
     *   <li>Descriptive names: result, start, end.</li>
     * </ul>
     *
     * <h3>Alternatives / Variants</h3>
     * Could binary search insertion point then merge only nearby, but linear scan is simple and optimal here.
     */
    static class Solution {
        public int[][] insert(int[][] intervals, int[] newInterval) {
            List<int[]> result = new ArrayList<>();
            int start = newInterval[0];
            int end = newInterval[1];

            for (int[] interval : intervals) {
                if (interval[1] < start) {
                    // Current interval ends before newInterval starts
                    result.add(interval);
                } else if (interval[0] > end) {
                    // Current interval starts after newInterval ends
                    result.add(new int[] { start, end });
                    start = interval[0];
                    end = interval[1];
                } else {
                    // Overlap → merge
                    start = Math.min(start, interval[0]);
                    end = Math.max(end, interval[1]);
                }
            }

            result.add(new int[] { start, end });
            return result.toArray(new int[result.size()][2]);
        }
    }
}
