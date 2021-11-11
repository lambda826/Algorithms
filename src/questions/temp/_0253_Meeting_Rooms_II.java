package questions.temp;

import common.Interval;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/*

Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), find the minimum number of conference rooms required.


Example 1:
    Input: 
        [[0, 30],[5, 10],[15, 20]]
    Output: 
        2

Example 2:
    Input: 
        [[7,10],[2,4]]
    Output: 
        1

*/

public class _0253_Meeting_Rooms_II {

    public static void main(String[] args) {
        Interval i1 = new Interval(0, 5);
        Interval i2 = new Interval(4, 7);
        Interval i3 = new Interval(6, 9);
        Interval i4 = new Interval(9, 13);
        Interval i5 = new Interval(10, 15);
        Interval i6 = new Interval(14, 26);
        System.out.println(new _0253_Meeting_Rooms_II().minMeetingRooms(new Interval[] { i1, i2, i3, i4, i5, i6 }));
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int minMeetingRooms(Interval[] intervals) {
        int[] starts = new int[intervals.length];
        int[] ends = new int[intervals.length];
        for (int i = 0; i < intervals.length; ++i) {
            starts[i] = intervals[i].start;
            ends[i] = intervals[i].end;
        }
        Arrays.sort(starts);
        Arrays.sort(ends);
        int count = 0;
        // When we encounter an ending event, that means that some meeting that started earlier has ended now. 
        // We are not really concerned with which meeting has ended. All we need is that some meeting ended thus making a room available.
        for (int i = 0, j = 0; i < intervals.length; ++i) {
            if (starts[i] < ends[j]) { // last
                ++count;
            } else {
                j++;
            }
        }
        return count;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Greedy
    // MinHeap
    public int minMeetingRooms2(Interval[] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(o -> o.start));
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        minHeap.offer(Integer.MIN_VALUE);
        for (Interval i : intervals) {
            if (i.start >= minHeap.peek()) {
                minHeap.poll();
            }
            minHeap.offer(i.end);
        }
        return minHeap.size();
    }

}
