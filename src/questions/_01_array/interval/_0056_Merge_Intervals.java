package questions._01_array.interval;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/*

Given an array of intervals where intervals[i] = [starti, endi],
merge all overlapping intervals, and return an array of the non-overlapping intervals that cover all the intervals in the input.


Example 1:
    Input:
        intervals = [[1,3],[2,6],[8,10],[15,18]]
    Output:
        [[1,6],[8,10],[15,18]]
    Explanation:
        Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].

Example 2:
    Input:
        intervals = [[1,4],[4,5]]
    Output:
        [[1,5]]
    Explanation:
        Intervals [1,4] and [4,5] are considered overlapping.


Constraints:
    1 <= intervals.length <= 10^4
    intervals[i].length == 2
    0 <= start(i) <= end(i) <= 10^4

*/

public class _0056_Merge_Intervals {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    class Solution_Greedy {
        public int[][] merge(int[][] intervals) {
            Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
            List<int[]> res = new ArrayList<>();

            int start = intervals[0][0];
            int maxEnd = intervals[0][1];
            for (int[] interval : intervals) {
                // Can not merge if maxEnd is on the left of the start of current interval
                if (maxEnd < interval[0]) {
                    res.add(new int[] { start, maxEnd });
                    start = interval[0];
                }
                // Merge
                maxEnd = Math.max(maxEnd, interval[1]);
            }

            res.add(new int[] { start, maxEnd });
            return res.toArray(new int[res.size()][2]);
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    class Solution {
        public int[][] merge(int[][] intervals) {
            int len = intervals.length;
            int[] starts = new int[len];
            int[] ends = new int[len];

            for (int i = 0; i < len; ++i) {
                starts[i] = intervals[i][0];
                ends[i] = intervals[i][1];
            }
            Arrays.sort(starts);
            Arrays.sort(ends);
            List<int[]> res = new ArrayList<>();
            int i = 0;
            while (i < len) {
                int start = starts[i];
                while (i + 1 < len && ends[i] >= starts[i + 1]) {
                    ++i;
                }
                res.add(new int[] { start, ends[i] });
                ++i;
            }
            return res.toArray(new int[res.size()][2]);
        }
    }

}