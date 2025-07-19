package leetcode;

/*

You are given an array of non-overlapping intervals where intervals[i] = [start_i, end_i] represent the start and the end of the ith interval and intervals is sorted in
ascending order by start_i.
You are also given an interval newInterval = [start, end] that represents the start and end of another interval.

Insert newInterval into intervals such that intervals is still sorted in ascending order by start_i and intervals still does not have any overlapping intervals (merge
overlapping intervals if necessary).

Return intervals after the insertion.
Note that you don't need to modify intervals in-place. You can make a new array and return it.


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
    0 <= start_i <= end_i <= 10^5
    intervals is sorted by start_i in ascending order.
    newInterval.length == 2
    0 <= start <= end <= 10^5

 */

import java.util.ArrayList;
import java.util.List;

public class _0057_Insert_Interval {

    class Solution {
        public int[][] insert(int[][] intervals, int[] newInterval) {
            List<int[]> list = new ArrayList<>();
            boolean inserted = false;
            for (int[] interval : intervals) {
                if (newInterval[1] < interval[0]) {
                    if (!inserted) {
                        list.add(newInterval);
                        inserted = true;
                    }
                    list.add(interval);
                } else if (newInterval[0] > interval[1]) {
                    list.add(interval);
                } else {
                    // Merge
                    newInterval[0] = Math.min(newInterval[0], interval[0]);
                    newInterval[1] = Math.max(newInterval[1], interval[1]);
                }
            }
            if (!inserted) {
                list.add(newInterval);
            }
            int[][] res = new int[list.size()][];
            for (int i = 0; i < res.length; ++i) {
                res[i] = list.get(i);
            }
            return res;
        }
    }

}
