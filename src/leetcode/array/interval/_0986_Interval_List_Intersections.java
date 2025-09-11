package leetcode.array.interval;

import java.util.ArrayList;
import java.util.List;

/*
Description:
    You are given two lists of closed intervals, firstList and secondList, where
    firstList[i] = [starti, endi] and secondList[j] = [startj, endj]. Each list is
    pairwise disjoint and sorted by start time.

    Return the intersection of these two interval lists. The intersection of two
    closed intervals is also a closed interval (possibly empty). If an intersection
    is empty, do not include it in the result.


Examples:
    Example 1:
        Input: firstList = [[0,2],[5,10],[13,23],[24,25]], secondList = [[1,5],[8,12],[15,24],[25,26]]
        Output: [[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]]

    Example 2:
        Input: firstList = [[1,3],[5,9]], secondList = []
        Output: []


Constraints:
    0 <= firstList.length, secondList.length <= 1000
    0 <= starti <= endi <= 10^9
    0 <= startj <= endj <= 10^9
    Each list is pairwise disjoint and sorted by start time.
*/

public class _0986_Interval_List_Intersections {

    /**
     * <h2>986. Interval List Intersections — two pointers</h2>
     *
     * <h3>Goal / Problem summary</h3>
     * Compute all intersections between two sorted, pairwise-disjoint lists of closed intervals.
     *
     * <h3>Constraints / Assumptions</h3>
     * <ul>
     *   <li>Both lists are sorted by start and internally non-overlapping.</li>
     *   <li>Closed intervals: an overlap exists if {@code max(aL,bL) <= min(aR,bR)}.</li>
     * </ul>
     *
     * <h3>Approach</h3>
     * Use two pointers. For current intervals {@code A=[aL,aR]} and {@code B=[bL,bR]}:
     * <ul>
     *   <li>The intersection is {@code [max(aL,bL), min(aR,bR)]} when {@code max <= min}.</li>
     *   <li>Advance the pointer with the smaller end, since that interval cannot intersect further ones.</li>
     * </ul>
     *
     * <h3>Algorithm</h3>
     * <ol>
     *   <li>Initialize {@code i=j=0} and an output list.</li>
     *   <li>While both pointers are in range:
     *     <ul>
     *       <li>Compute {@code L = max(aL,bL)} and {@code R = min(aR,bR)}; if {@code L <= R}, add {@code [L,R]}.</li>
     *       <li>If {@code aR < bR}, increment {@code i}; else increment {@code j}.</li>
     *     </ul>
     *   </li>
     *   <li>Return the output list as an array.</li>
     * </ol>
     *
     * <h3>Correctness / Key Invariant</h3>
     * The interval with the smaller end cannot overlap any future interval in the other list,
     * so advancing that pointer preserves completeness and ensures progress.
     *
     * <h3>Complexity</h3>
     * Time {@code O(m + n)}; Space {@code O(k)} for k intersections (output size).
     *
     * <h3>Edge Cases &amp; Pitfalls</h3>
     * <ul>
     *   <li>Empty list on either side → empty result.</li>
     *   <li>Touching endpoints (e.g., {@code [1,2]} and {@code [2,3]}) produce {@code [2,2]}.</li>
     * </ul>
     *
     * <h3>Implementation Notes</h3>
     * <ul>
     *   <li>Readability-first variable names; braces on all control statements.</li>
     * </ul>
     *
     * <h3>Alternatives / Variants</h3>
     * Event-point sweep is possible but overkill; two pointers are optimal and simple.
     */
    static class Solution {
        public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
            int i = 0;
            int j = 0;
            List<int[]> res = new ArrayList<>();
            while (i < firstList.length && j < secondList.length) {
                int[] first = firstList[i];
                int[] second = secondList[j];
                int start = Math.max(first[0], second[0]);
                int end = Math.min(first[1], second[1]);
                if (start <= end) {
                    res.add(new int[] { start, end });
                }
                if (first[1] == end) {
                    ++i;
                }
                if (second[1] == end) {
                    ++j;
                }
            }
            return res.toArray(new int[res.size()][2]);
        }
    }
}
