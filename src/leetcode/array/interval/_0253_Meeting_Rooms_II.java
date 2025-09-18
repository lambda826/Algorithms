package leetcode.array.interval;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/*
Description:
    Given an array of meeting time intervals where intervals[i] = [starti, endi],
    return the minimum number of conference rooms required.

    Note:
    - Meetings are half-open or endpoint-reusable in this problem: a meeting that starts
      at time t can use a room that ends at time t (i.e., [a,b] and [b,c] do NOT overlap).


Examples:
    Example 1:
        Input: intervals = [[0,30],[5,10],[15,20]]
        Output: 2

    Example 2:
        Input: intervals = [[7,10],[2,4]]
        Output: 1

    Example 3:
        Input: intervals = [[1,5],[5,8],[8,10]]
        Output: 1


Constraints:
    0 <= intervals.length <= 10^5
    intervals[i].length == 2
    0 <= starti < endi <= 10^9
*/

public class _0253_Meeting_Rooms_II {

    /**
     * <h2>253. Meeting Rooms II — starts/ends sweep (two pointers)</h2>
     *
     * <h3>Goal / Problem summary</h3>
     * Compute the minimum number of rooms so that no two meetings in the same room overlap.
     *
     * <h3>Constraints / Assumptions</h3>
     * <ul>
     *   <li>Endpoint reuse is allowed: if one meeting ends at t, another starting at t can reuse the room.</li>
     *   <li>0 ≤ n ≤ 1e5, times up to 1e9.</li>
     * </ul>
     *
     * <h3>Approach</h3>
     * Collect all starts and ends into two arrays and sort them. Walk with two pointers:
     * <ul>
     *   <li>If {@code starts[i] < ends[j]} → a new meeting begins before the earliest current meeting ends,
     *       so we need a new room: {@code rooms++}, advance {@code i}.</li>
     *   <li>Else → some meeting ended at or before the next start, so free a room by advancing {@code j}.</li>
     * </ul>
     * Track the peak of {@code rooms} as the answer.
     *
     * <h3>Algorithm</h3>
     * <ol>
     *   <li>Extract and sort arrays {@code starts[]} and {@code ends[]}.</li>
     *   <li>Initialize {@code i = j = 0}, {@code rooms = best = 0}.</li>
     *   <li>While {@code i < n}:
     *     <ul>
     *       <li>If {@code starts[i] < ends[j]}: {@code rooms++}, {@code best = max(best, rooms)}, {@code i++}.</li>
     *       <li>Else: {@code j++} (free one room).</li>
     *     </ul>
     *   </li>
     *   <li>Return {@code best}.</li>
     * </ol>
     *
     * <h3>Correctness / Key Invariant</h3>
     * At any step, {@code rooms} equals the number of ongoing meetings (current concurrency).
     * The maximum concurrency over time is the minimum number of rooms needed.
     *
     * <h3>Complexity</h3>
     * Time {@code O(n log n)} for sorting; Space {@code O(n)} for the start/end arrays.
     *
     * <h3>Edge Cases &amp; Pitfalls</h3>
     * <ul>
     *   <li>Use {@code starts[i] < ends[j]} (strict) because endpoint reuse is allowed.</li>
     *   <li>Empty input returns 0.</li>
     * </ul>
     *
     * <h3>Implementation Notes</h3>
     * Readability-first; braces on all control statements.
     *
     * <h3>Alternatives / Variants</h3>
     * Min-heap of end times: sort by start; if {@code start >= minEnd} reuse a room (pop), then push current end.
     */
    static class Solution {
        public int minMeetingRooms(int[][] intervals) {
            int n = intervals.length;
            int[] starts = new int[n];
            int[] ends = new int[n];
            for (int i = 0; i < n; i++) {
                starts[i] = intervals[i][0];
                ends[i] = intervals[i][1];
            }
            Arrays.sort(starts);
            Arrays.sort(ends);

            int i = 0;
            int j = 0;
            int rooms = 0;
            int best = 0;

            while (i < n) {
                if (starts[i] < ends[j]) { // endpoint reuse allowed
                    rooms++;
                    if (rooms > best) {
                        best = rooms;
                    }
                    i++;
                } else {
                    j++; // a meeting ended; free a room
                }
            }
            return best;
        }
    }

    /**
     * Optional: Min-heap (meeting rooms template).
     * Keep the earliest ending room at the top; reuse when {@code start >= peek()}.
     * Peak heap size is the answer.
     */
    static class Solution_MinHeap {
        public int minMeetingRooms(int[][] intervals) {
            if (intervals.length == 0) {
                return 0;
            }
            Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
            PriorityQueue<Integer> pq = new PriorityQueue<>(); // end times
            int best = 0;

            for (int[] it : intervals) {
                int s = it[0], e = it[1];
                while (!pq.isEmpty() && s >= pq.peek()) {
                    pq.poll(); // reuse a room
                }
                pq.offer(e);
                if (pq.size() > best) {
                    best = pq.size();
                }
            }
            return best;
        }
    }
}
