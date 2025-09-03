package leetcode.array.interval;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/*
Description:
    Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping intervals,
    and return an array of the non-overlapping intervals that cover all the intervals in the input.


Examples:
    Example 1:
        Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
        Output: [[1,6],[8,10],[15,18]]
        Explanation: Since intervals [1,3] and [2,6] overlap, merge them into [1,6].

    Example 2:
        Input: intervals = [[1,4],[4,5]]
        Output: [[1,5]]
        Explanation: Intervals [1,4] and [4,5] are considered overlapping.

    Example 3:
        Input: intervals = [[4,7],[1,4]]
        Output: [[1,7]]
        Explanation: Intervals [1,4] and [4,7] are considered overlapping.


Constraints:
    1 <= intervals.length <= 10^4
    intervals[i].length == 2
    0 <= starti <= endi <= 10^4
*/
public class _0056_Merge_Intervals {

    /**
     * <h2>56. Merge Intervals — sort + one pass</h2>
     *
     * <h3>Approach</h3>
     * Sort intervals by start. Sweep from left to right, merging into a growing window
     * {@code [a, b]}. If the next interval starts at {@code <= b}, extend {@code b};
     * otherwise, output {@code [a, b]} and start a new window.
     *
     * <h3>Algorithm</h3>
     * <ol>
     *   <li>Sort by {@code start} ascending.</li>
     *   <li>Initialize {@code a=intervals[0][0]}, {@code b=intervals[0][1]}.</li>
     *   <li>For each interval {@code [s,e]} in order:
     *     <ul>
     *       <li>If {@code s <= b}: {@code b = max(b, e)} (overlap).</li>
     *       <li>Else: emit {@code [a,b]} and set {@code a=s}, {@code b=e}.</li>
     *     </ul>
     *   </li>
     *   <li>Emit the last {@code [a,b]}.</li>
     * </ol>
     *
     * <h3>Complexity</h3>
     * <ul>
     *   <li>Time: {@code O(n log n)} for sorting + {@code O(n)} scan.</li>
     *   <li>Space: {@code O(n)} for the output list (sorting can be in-place).</li>
     * </ul>
     */
    static class Solution {
        public int[][] merge(int[][] intervals) {
            List<int[]> res = new ArrayList<>();
            Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
            int a = intervals[0][0];
            int b = intervals[0][1];
            for (int[] interval : intervals) {
                if (interval[0] > b) {
                    res.add(new int[] { a, b });
                    a = interval[0];
                }
                b = Math.max(b, interval[1]);
            }
            res.add(new int[] { a, b });
            return res.toArray(new int[res.size()][2]);
        }
    }
}