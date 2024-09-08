/**
 * @author Yunxiang He
 * @date 05/21/2018
 */

package questions.temp;

import common.Interval;

import java.util.Arrays;
import java.util.Comparator;

/*

Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), determine if a person could attend all meetings.


Example 1:
    Input: [[0,30],[5,10],[15,20]]
    Output: false
    
Example 2:
    Input: [[7,10],[2,4]]
    Output: true

*/

public class _0252_Meeting_Rooms {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public boolean canAttendMeetings(Interval[] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(i -> i.start));
        int preEnd = Integer.MIN_VALUE;
        for (Interval currInterval : intervals) {
            if (preEnd > currInterval.start) {
                return false;
            }
            preEnd = currInterval.end;
        }
        return true;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public boolean canAttendMeetings2(Interval[] intervals) {
        int[] starts = new int[intervals.length];
        int[] ends = new int[intervals.length];
        for (int i = 0; i < intervals.length; ++i) {
            starts[i] = intervals[i].start;
            ends[i] = intervals[i].end;
        }
        Arrays.sort(starts);
        Arrays.sort(ends);
        for (int i = 0; i < intervals.length - 1; ++i) {
            if (starts[i + 1] < ends[i]) {
                return false;
            }
        }
        return true;
    }
}
