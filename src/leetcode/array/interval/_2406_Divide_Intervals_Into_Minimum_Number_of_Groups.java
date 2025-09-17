package leetcode.array.interval;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/*
Description:
    You are given a 2D integer array intervals where intervals[i] = [starti, endi] represents
    the inclusive interval [starti, endi]. You need to divide the intervals into the minimum
    number of groups so that each interval in a group does not overlap with any other interval
    in the same group (two intervals overlap if they share at least one integer point).
    Return the minimum number of groups needed.


Examples:
    Example 1:
        Input: intervals = [[5,10],[6,8],[1,5],[2,3],[1,10]]
        Output: 3
        Explanation:
            One way to divide the intervals into 3 groups is:
            - Group 1: [1,5], [6,8]
            - Group 2: [2,3], [5,10]
            - Group 3: [1,10]

    Example 2:
        Input: intervals = [[1,3],[5,6],[8,10],[11,13]]
        Output: 1
        Explanation: No intervals overlap; all can go into one group.

    Example 3:
        Input: intervals = [[1,3],[1,3],[1,3]]
        Output: 3
        Explanation: All overlap pairwise; each must be in its own group.


Constraints:
    1 <= intervals.length <= 10^5
    1 <= starti <= endi <= 10^9
*/
public class _2406_Divide_Intervals_Into_Minimum_Number_of_Groups {

    static class Solution_Greedy_MinHeap {
        public int minGroups(int[][] intervals) {
            Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
            PriorityQueue<Integer> minHeap = new PriorityQueue<>();
            minHeap.offer(Integer.MAX_VALUE);
            int cnt = 0;
            for (int[] interval : intervals) {
                if (interval[0] <= minHeap.peek()) {
                    cnt++;
                } else {
                    minHeap.poll();
                }
                minHeap.offer(interval[1]);
            }
            return cnt;
        }
    }

    /**
     * <h2>2406. Divide Intervals Into Minimum Number of Groups — sweep by starts/ends</h2>
     *
     * <h3>Goal / Problem summary</h3>
     * Find the fewest groups so that intervals within the same group do not overlap
     * (closed intervals; sharing an endpoint counts as overlap).
     *
     * <h3>Constraints / Assumptions</h3>
     * <ul>
     *   <li>Up to 1e5 intervals; endpoints up to 1e9.</li>
     *   <li>Closed intervals: [a,b] and [b,c] overlap at b.</li>
     * </ul>
     *
     * <h3>Approach</h3>
     * The answer equals the maximum number of intervals overlapping at any time.
     * Compute it via the classic two-array sweep:
     * <ul>
     *   <li>Sort all starts and all ends independently.</li>
     *   <li>Walk with two pointers; when {@code start <= end}, an interval begins before or at the current end
     *       (closed overlap) ⇒ increase current count. Otherwise, an interval finishes ⇒ decrease.</li>
     *   <li>Track the maximum current count; that is the minimum number of groups.</li>
     * </ul>
     *
     * <h3>Algorithm</h3>
     * <ol>
     *   <li>Extract arrays {@code S[i]=starti}, {@code E[i]=endi}; sort both ascending.</li>
     *   <li>Initialize {@code i=j=0}, {@code curr=0}, {@code best=0}.</li>
     *   <li>While {@code i < n}:
     *     <ul>
     *       <li>If {@code S[i] <= E[j]}: {@code curr++}, {@code best=max(best,curr)}, {@code i++}.</li>
     *       <li>Else: {@code curr--}, {@code j++}.</li>
     *     </ul>
     *   </li>
     *   <li>Return {@code best}.</li>
     * </ol>
     *
     * <h3>Correctness / Key Invariant</h3>
     * With closed intervals, a start at the same coordinate as an end still overlaps,
     * so using {@code S[i] <= E[j]} correctly counts that as requiring another group.
     *
     * <h3>Complexity</h3>
     * Time {@code O(n log n)} for sorting; Space {@code O(n)} for the start/end arrays.
     *
     * <h3>Edge Cases &amp; Pitfalls</h3>
     * <ul>
     *   <li>Be careful to use {@code <=} (not {@code <}) to enforce closed-interval overlap at equal endpoints.</li>
     * </ul>
     *
     * <h3>Implementation Notes</h3>
     * <ul>
     *   <li>Readability-first; braces on all control statements.</li>
     * </ul>
     *
     * <h3>Alternatives / Variants</h3>
     * PriorityQueue of ends (meeting rooms style) with condition {@code start > minEnd} to reuse a room.
     */
    static class Solution {
        public int minGroups(int[][] intervals) {
            int[] starts = new int[intervals.length];
            int[] ends = new int[intervals.length];
            for (int i = 0; i < intervals.length; ++i) {
                starts[i] = intervals[i][0];
                ends[i] = intervals[i][1];
            }
            Arrays.sort(starts);
            Arrays.sort(ends);

            int i = 0;
            int j = 0;
            int concurrent = 0;
            int max = 0;
            while (i < starts.length) {
                if (starts[i] <= ends[j]) {
                    concurrent++;
                    i++;
                } else {
                    concurrent--;
                    j++;
                }
                max = Math.max(max, concurrent);
            }

            return max;
        }
    }
}
