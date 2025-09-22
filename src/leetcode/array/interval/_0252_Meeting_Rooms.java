package leetcode.array.interval;

import java.util.Arrays;
import java.util.Comparator;

/*
Description:
    Given an array of meeting time intervals where intervals[i] = [starti, endi],
    determine if a person could attend all meetings (i.e., no meetings overlap).
    Two meetings that touch at an endpoint (end == nextStart) do NOT overlap.


Examples:
    Example 1:
        Input: intervals = [[0,30],[5,10],[15,20]]
        Output: false

    Example 2:
        Input: intervals = [[7,10],[2,4]]
        Output: true


Constraints:
    0 <= intervals.length <= 10^4
    intervals[i].length == 2
    0 <= starti < endi <= 10^6
*/

public class _0252_Meeting_Rooms {

    /**
     * <h2>252. Meeting Rooms — sort by start, check adjacent</h2>
     *
     * <h3>Goal / Problem summary</h3>
     * Decide whether any two meetings overlap; if none overlap, all can be attended.
     *
     * <h3>Constraints / Assumptions</h3>
     * <ul>
     *   <li>Endpoints are reusable: a meeting ending at t and another starting at t do not overlap.</li>
     *   <li>0 ≤ n ≤ 1e4; times within [0, 1e6].</li>
     * </ul>
     *
     * <h3>Approach</h3>
     * Sort intervals by start time. Walk once and ensure each meeting's start is
     * at least the previous meeting's end (strictly greater not required due to endpoint reuse).
     *
     * <h3>Algorithm</h3>
     * <ol>
     *   <li>Sort by start ascending.</li>
     *   <li>Track {@code prevEnd}. For each interval [s, e] in order:
     *     <ul>
     *       <li>If {@code s < prevEnd} → overlap → return false.</li>
     *       <li>Else update {@code prevEnd = e}.</li>
     *     </ul>
     *   </li>
     *   <li>Return true if no overlap found.</li>
     * </ol>
     *
     * <h3>Correctness / Key Invariant</h3>
     * After sorting by start, only adjacent intervals can overlap; verifying {@code s < prevEnd}
     * is necessary and sufficient.
     *
     * <h3>Complexity</h3>
     * Time {@code O(n log n)} for sorting + {@code O(n)} scan; Space {@code O(1)} extra.
     *
     * <h3>Edge Cases &amp; Pitfalls</h3>
     * <ul>
     *   <li>Empty or single interval → always true.</li>
     *   <li>Use {@code s < prevEnd} (strict) since endpoint reuse is allowed.</li>
     * </ul>
     *
     * <h3>Implementation Notes</h3>
     * Readability-first; braces on all control statements.
     *
     * <h3>Alternatives / Variants</h3>
     * Can also sort by end and check, but start-sorted + single pass is standard.
     */
    static class Solution {
        public boolean canAttendMeetings(int[][] intervals) {
            if (intervals.length <= 1) {
                return true;
            }

            Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));

            int prevEnd = intervals[0][1];
            for (int i = 1; i < intervals.length; i++) {
                int s = intervals[i][0];
                int e = intervals[i][1];
                if (s < prevEnd) {
                    return false; // overlap
                }
                prevEnd = e;
            }
            return true;
        }
    }
}
