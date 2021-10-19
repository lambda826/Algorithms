/**
 *  @author: Yunxiang He
 *  @date  : 2018-06-20 18:08
 */

package algorithms.greedy;

import common.Interval;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/*

We have many identical resources available and we wish to schedule all the requests using as few resources as possible

 */

public class IntervalPartitioning {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // One-pass greedy strategy that orders intervals by their starting times
    // And try to assign to each interval we encounter a label that hasn’t already been assigned to any previous interval that overlaps it
    public int minResources(Interval[] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }
        // 1. Sort the intervals by start time
        Arrays.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                return o1.getStart() - o2.getStart();
            }
        });

        // 2. Assign labers
        PriorityQueue<Interval> pq = new PriorityQueue<>();
        int label = 1;
        pq.add(intervals[0]);
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i].isCompatible(pq.peek())) {
                pq.remove();
            } else {
                label++;
            }
            pq.add(intervals[i]);
        }

        return label;
    }

    public static void main(String[] args) {
        Interval[] intervals = new Interval[] { new Interval("i1", 10, 11, 0), new Interval("i2", 10, 12, 0), new Interval("i3", 10, 13, 0), new Interval("i4", 11, 13, 0), new Interval("i5", 12, 14, 0), new Interval("i6", 13, 15, 0),
                new Interval("i7", 13, 15, 0), new Interval("i8", 15, 16, 0), new Interval("i9", 15, 17, 0), new Interval("i10", 15, 17, 0), };
        int min = new IntervalPartitioning().minResources(intervals);
        System.out.println(min);
    }
}
