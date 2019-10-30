/**
 *  @author: Yunxiang He
 *  @date  : 2018-06-20 18:08
 */

package algorithms.greedy;

import common.Interval;

import java.util.Arrays;
import java.util.Comparator;

/*

We have a set of requests {1, 2, …, n}
The ith request corresponds to an interval of time starting at s(i) and finishing at f(i)
Find the maximum size of compatible sets

 */

public class IntervalScheduling {

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Finishes first
    public int maxCompatibleIntervals(Interval[] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }
        Arrays.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                return o1.getEnd() - o2.getEnd();
            }
        });

        int max = 1;
        Interval preInterval = intervals[0];
        for (int i = 1; i < intervals.length; i++) {
            if (preInterval.isCompatible(intervals[i])) {
                preInterval = intervals[i];
                max++;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        Interval[] intervals = new Interval[] { new Interval("i1", 10, 11, 0), new Interval("i2", 10, 12, 0), new Interval("i3", 10, 13, 0), new Interval("i4", 11, 13, 0), new Interval("i5", 12, 14, 0), new Interval("i6", 13, 15, 0),
                new Interval("i7", 13, 15, 0), new Interval("i8", 15, 16, 0), new Interval("i9", 15, 17, 0), new Interval("i10", 15, 17, 0), };
        int max = new IntervalScheduling().maxCompatibleIntervals(intervals);
        System.out.println(max);
    }
}
