package leetcode.array.interval;

import java.util.ArrayList;
import java.util.List;

/*
Description:
    A set of real numbers can be represented as the union of several disjoint intervals,
    where each interval is in the form [a, b). A real number x is in the set if one of its
    intervals [a, b) contains x (i.e. a <= x < b).

    You are given a sorted list of disjoint intervals intervals representing a set of real numbers
    as described above, where intervals[i] = [ai, bi] represents the interval [ai, bi).
    You are also given another interval toBeRemoved.

    Return the set of real numbers with the interval toBeRemoved removed from intervals.
    In other words, return the set of real numbers such that every x in the set is in intervals
    but not in toBeRemoved. Your answer should be a sorted list of disjoint intervals as described above.


Examples:
    Example 1:
        Input: intervals = [[0,2],[3,4],[5,7]], toBeRemoved = [1,6]
        Output: [[0,1],[6,7]]

    Example 2:
        Input: intervals = [[0,5]], toBeRemoved = [2,3]
        Output: [[0,2],[3,5]]

    Example 3:
        Input: intervals = [[-5,-4],[-3,-2],[1,2],[3,5],[8,9]], toBeRemoved = [-1,4]
        Output: [[-5,-4],[-3,-2],[4,5],[8,9]]


Constraints:
    1 <= intervals.length <= 10^4
    -10^9 <= ai < bi <= 10^9
*/
public class _1272_Remove_Interval {

    /**
     * <h2>1272. Remove Interval — interval splitting</h2>
     *
     * <h3>Goal / Problem summary</h3>
     * Given disjoint sorted intervals and an interval to remove,
     * return the resulting set after removing overlap parts.
     *
     * <h3>Constraints / Assumptions</h3>
     * <ul>
     *   <li>Intervals are sorted, non-overlapping, half-open [a, b).</li>
     *   <li>1 <= intervals.length <= 1e4, bounds within ±1e9.</li>
     * </ul>
     *
     * <h3>Approach</h3>
     * For each interval [l, r):
     * <ul>
     *   <li>If disjoint with remove interval → keep as is.</li>
     *   <li>If overlapping → cut off the overlapping part:
     *     <ul>
     *       <li>If left portion remains, add [l, max(l, removeStart)).</li>
     *       <li>If right portion remains, add [min(r, removeEnd), r).</li>
     *     </ul>
     *   </li>
     * </ul>
     *
     * <h3>Algorithm</h3>
     * <ol>
     *   <li>Initialize result list.</li>
     *   <li>For each [l, r] in intervals:
     *     <ul>
     *       <li>If r <= removeStart or l >= removeEnd → no overlap, keep [l, r].</li>
     *       <li>Else:
     *         <ul>
     *           <li>If l < removeStart → keep [l, removeStart].</li>
     *           <li>If r > removeEnd → keep [removeEnd, r].</li>
     *         </ul>
     *       </li>
     *     </ul>
     *   </li>
     * </ol>
     *
     * <h3>Correctness / Key Invariant</h3>
     * Because original intervals are disjoint and sorted,
     * each is processed independently without re-merge.
     *
     * <h3>Complexity</h3>
     * Time O(n), Space O(n).
     *
     * <h3>Edge Cases & Pitfalls</h3>
     * <ul>
     *   <li>toBeRemoved outside all intervals → no changes.</li>
     *   <li>toBeRemoved fully covers some intervals → they vanish.</li>
     *   <li>toBeRemoved inside an interval → interval splits into two.</li>
     * </ul>
     *
     * <h3>Implementation Notes</h3>
     * <ul>
     *   <li>Static inner Solution class, compatible with LeetCode runner.</li>
     *   <li>Use List<int[]> to build then convert to List&lt;List&lt;Integer&gt;&gt; or int[][] depending on signature.</li>
     * </ul>
     *
     * <h3>Alternatives / Variants</h3>
     * Could use binary search to skip to first possible overlap,
     * but linear scan is optimal enough (n ≤ 1e4).
     */
    static class Solution {
        public List<List<Integer>> removeInterval(int[][] intervals, int[] toBeRemoved) {
            List<List<Integer>> res = new ArrayList<>();
            for (int[] interval : intervals) {
                if (interval[1] < toBeRemoved[0] || interval[0] > toBeRemoved[1]) {
                    res.add(List.of(interval[0], interval[1]));
                } else if (interval[0] < toBeRemoved[0] && interval[1] > toBeRemoved[1]) {
                    res.add(List.of(interval[0], toBeRemoved[0]));
                    res.add(List.of(toBeRemoved[1], interval[1]));
                } else if (interval[0] < toBeRemoved[0] && interval[1] > toBeRemoved[0]) {
                    res.add(List.of(interval[0], toBeRemoved[0]));
                } else if (interval[0] < toBeRemoved[1] && interval[1] > toBeRemoved[1]) {
                    res.add(List.of(toBeRemoved[1], interval[1]));
                }
            }
            return res;
        }
    }
}