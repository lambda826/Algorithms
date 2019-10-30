/**
 *  @author Yunxiang He
 *  @date 05/21/2018
 */

package coding.temp;

import common.Interval;

import java.util.Arrays;

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

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public boolean canAttendMeetings(Interval[] intervals) {
        Arrays.sort(intervals, (i1, i2) -> (i1.start - i2.start));
        int end = Integer.MIN_VALUE;
        for (Interval i : intervals) {
            if (end > i.start) {
                return false;
            }
            end = i.end;
        }
        return true;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
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
