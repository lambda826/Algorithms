/**
 *  @author Yunxiang He
 *  @date 01/11/2018
 */

package coding.temp;

import common.Interval;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*

Given a collection of intervals, merge all overlapping intervals.


Example 1:
    Input: [[1,3],[2,6],[8,10],[15,18]]
    Output: [[1,6],[8,10],[15,18]]
    Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].

Example 2:
    Input: [[1,4],[4,5]]
    Output: [[1,5]]
    Explanation: Intervals [1,4] and [4,5] are considerred overlapping.

*/

public class _0056_Merge_Intervals {

    public static void main(String[] args) {
        _0056_Merge_Intervals test = new _0056_Merge_Intervals();
        Interval i1 = new Interval(1, 4);
        Interval i2 = new Interval(2, 3);
        Interval i3 = new Interval(8, 10);
        Interval i4 = new Interval(15, 18);
        ArrayList<Interval> list = new ArrayList<>();
        list.add(i1);
        list.add(i2);
        list.add(i3);
        list.add(i4);
        List<Interval> list2 = test.merge(list);
        for (Interval interval : list2) {
            System.out.print(interval.getStart() + "    ");
            System.out.println(interval.getEnd());
        }
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public List<Interval> merge(List<Interval> intervals) {
        if (intervals.size() < 1) {
            return intervals;
        }
        int[] starts = new int[intervals.size()];
        int[] ends = new int[intervals.size()];
        for (int i = 0; i < intervals.size(); ++i) {
            starts[i] = intervals.get(i).start;
            ends[i] = intervals.get(i).end;
        }
        Arrays.parallelSort(starts);
        Arrays.parallelSort(ends);
        List<Interval> list = new ArrayList<>();
        int i = 0;
        while (i < starts.length) {
            int start = starts[i];
            while (i + 1 < starts.length && starts[i + 1] <= ends[i]) {
                ++i;
            }
            list.add(new Interval(start, ends[i++]));
        }
        return list;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public List<Interval> merge2(List<Interval> intervals) {
        if (intervals.size() < 1) {
            return intervals;
        }
        List<Interval> res = new ArrayList<>();
        Collections.sort(intervals, (i1, i2) -> i1.start - i2.start);
        int start = intervals.get(0).start;
        int end = intervals.get(0).end;
        for (Interval curr : intervals) {
            if (end < curr.start) {
                res.add(new Interval(start, end));
                start = curr.start;
            }
            end = Math.max(end, curr.end);
        }
        res.add(new Interval(start, end));
        return res;
    }
}
