/**
 * @author Yunxiang He
 * @date 01/04/2018
 */

package questions.temp;

import common.Interval;

import java.util.Arrays;

/*

Given a collection of intervals, find the minimum number of intervals you need to remove to make the rest of the intervals non-overlapping.


Example 1:
    Input: [ [1,2], [2,3], [3,4], [1,3] ]
    Output: 1
    Explanation: [1,3] can be removed and the rest of intervals are non-overlapping.

Example 2:
    Input: [ [1,2], [1,2], [1,2] ]
    Output: 2
    Explanation: You need to remove two [1,2] to make the rest of intervals non-overlapping.

Example 3:
    Input: [ [1,2], [2,3] ]
    Output: 0
    Explanation: You don't need to remove any of the intervals since they're already non-overlapping.


Note:
    You may assume the interval's end point is always bigger than its start point.
    Intervals like [1,2] and [2,3] have borders "touching" but they don't overlap each other.

*/

public class _0435_Non_overlapping_Intervals {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // earliest finish first
    public int eraseOverlapIntervals(Interval[] intervals) {
        if (intervals.length <= 0) {
            return intervals.length;
        }
        Arrays.sort(intervals, (i1, i2) -> i1.end - i2.end);
        int cnt = 0;
        int end = intervals[0].end;
        for (int i = 1; i < intervals.length; ++i) {
            if (intervals[i].start < end) {
                ++cnt;
            } else {
                end = intervals[i].end;
            }
        }
        return cnt;
    }
}
