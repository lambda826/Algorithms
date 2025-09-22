package leetcode.array.interval;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/*
Description:
    Given the availability time slots arrays slots1 and slots2 of two people and a meeting duration duration,
    return the earliest time slot that works for both of them and is of duration duration.

    If there is no common time slot that satisfies the requirements, return an empty list [].

    A time slot is an array [start, end] representing a continuous time range from start to end.
    (Treat times as integers on a line; we only need that the overlap length >= duration.)

Examples:
    Example 1:
        Input: slots1 = [[10,50],[60,120],[140,210]], slots2 = [[0,15],[60,70]], duration = 8
        Output: [60,68]

    Example 2:
        Input: slots1 = [[10,50],[60,120],[140,210]], slots2 = [[0,15],[60,70]], duration = 12
        Output: []

Constraints:
    1 <= slots1.length, slots2.length <= 10^4
    slots1[i].length == 2, slots2[j].length == 2
    slots1[i][0] < slots1[i][1]
    slots2[j][0] < slots2[j][1]
    0 <= slots1[i][k], slots2[j][k] <= 10^9
    1 <= duration <= 10^6
*/
public class _1229_Meeting_Scheduler {

    /**
     * <h2>1229. Meeting Scheduler — two pointers on sorted slots</h2>
     *
     * <h3>Goal / Problem summary</h3>
     * Find the earliest overlapping window between two people of length at least {@code duration}.
     *
     * <h3>Constraints / Assumptions</h3>
     * <ul>
     *   <li>Each person's slots are pairwise non-overlapping.</li>
     *   <li>We consider overlap length {@code overlap = min(end1,end2) - max(start1,start2)}; need {@code overlap >= duration}.</li>
     * </ul>
     *
     * <h3>Approach</h3>
     * Sort both slot arrays by start time. Use two pointers; at each step:
     * <ul>
     *   <li>Compute the intersection {@code [L, R] = [max(s1, s2), min(e1, e2)]}.</li>
     *   <li>If {@code R - L >= duration}, return {@code [L, L+duration]}.</li>
     *   <li>Otherwise, advance the pointer of the interval that ends earlier (smaller end time).</li>
     * </ul>
     *
     * <h3>Algorithm</h3>
     * <ol>
     *   <li>Sort {@code slots1} and {@code slots2} by start ascending.</li>
     *   <li>Initialize {@code i = 0, j = 0}.</li>
     *   <li>While both in range:
     *     <ul>
     *       <li>{@code L = max(slots1[i][0], slots2[j][0])}, {@code R = min(slots1[i][1], slots2[j][1])}.</li>
     *       <li>If {@code R - L >= duration} → return {@code [L, L + duration]}.</li>
     *       <li>Else advance the one with smaller end: if {@code slots1[i][1] < slots2[j][1]} → {@code i++} else {@code j++}.</li>
     *     </ul>
     *   </li>
     *   <li>No feasible window → return empty list.</li>
     * </ol>
     *
     * <h3>Correctness / Key Invariant</h3>
     * Advancing the interval with the earlier end cannot remove any future feasible overlap starting earlier,
     * while it strictly increases the potential overlap with the other person's next slot.
     *
     * <h3>Complexity</h3>
     * Time {@code O((m+n) log m + (m+n) log n)} for sorting, then {@code O(m+n)} scan; Space {@code O(1)} extra.
     *
     * <h3>Edge Cases &amp; Pitfalls</h3>
     * <ul>
     *   <li>Use {@code R - L >= duration}（而不是 >）。</li>
     *   <li>Slots may be large (up to 1e9), but differences fit in int; no overflow issues here.</li>
     * </ul>
     */
    static class Solution {
        public List<Integer> minAvailableDuration(int[][] slots1, int[][] slots2, int duration) {
            Arrays.sort(slots1, Comparator.comparingInt(a -> a[0]));
            Arrays.sort(slots2, Comparator.comparingInt(a -> a[0]));

            int i = 0;
            int j = 0;
            while (i < slots1.length && j < slots2.length) {
                int L = Math.max(slots1[i][0], slots2[j][0]);
                int R = Math.min(slots1[i][1], slots2[j][1]);

                if (R - L >= duration) {
                    return List.of(L, L + duration);
                }

                // Move the pointer with the earlier end to seek a larger overlap next time
                if (slots1[i][1] < slots2[j][1]) {
                    i++;
                } else {
                    j++;
                }
            }
            return new ArrayList<>();
        }
    }
}
