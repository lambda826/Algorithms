/**
 *  @author Yunxiang He
 *  @date 03/29/2019
 */

package coding.leetcode.temp;

import common.Interval;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*

We are given a list schedule of employees, which represents the working time for each employee.
Each employee has a list of non-overlapping Intervals, and these intervals are in sorted order.
Return the list of finite intervals representing common, positive-length free time for all employees, also in sorted order.


Example 1:
    Input: schedule = [[[1,2],[5,6]],[[1,3]],[[4,10]]]
    Output: [[3,4]]
    Explanation:
    There are a total of three employees, and all common
    free time intervals would be [-inf, 1], [3, 4], [10, inf].
    We discard any intervals that contain inf as they aren't finite.

Example 2:
    Input: schedule = [[[1,3],[6,7]],[[2,4]],[[2,5],[9,12]]]
    Output: [[5,6],[7,9]]


(Even though we are representing Intervals in the form [x, y], the objects inside are Intervals, not lists or arrays. For example, schedule[0][0].start = 1, schedule[0][0].end = 2, and schedule[0][0][0] is not defined.)
Also, we wouldn't include intervals like [5, 5] in our answer, as they have zero length.

Note:
    schedule and schedule[i] are lists with lengths in range [1, 50].
    0 <= schedule[i].start < schedule[i].end <= 10^8.

*/

public class _0759_Employee_Free_Time {

    public static void main(String[] args) {
        _0759_Employee_Free_Time test = new _0759_Employee_Free_Time();
        Interval i1 = new Interval(1, 2);
        Interval i2 = new Interval(5, 6);
        Interval i3 = new Interval(1, 3);
        Interval i4 = new Interval(4, 10);
        List<Interval> l1 = new ArrayList<>();
        List<Interval> l2 = new ArrayList<>();
        List<Interval> l3 = new ArrayList<>();
        l1.add(i1);
        l1.add(i2);
        l2.add(i3);
        l3.add(i4);
        List<List<Interval>> schedule = new ArrayList<>();
        schedule.add(l1);
        schedule.add(l2);
        schedule.add(l3);
        test.employeeFreeTime(schedule);

    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // 1. merge all intervals
    // 2. find the free intervals
    public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
        List<Integer> starts = new ArrayList<>();
        List<Integer> ends = new ArrayList<>();
        for (List<Interval> intervals : schedule) {
            for (Interval interval : intervals) {
                starts.add(interval.start);
                ends.add(interval.end);
            }
        }
        Collections.sort(starts);
        Collections.sort(ends);
        // merge intervals
        int i = 0;
        List<Interval> res = new ArrayList<>();
        while (i < starts.size()) {
            while (i + 1 < starts.size() && ends.get(i) >= starts.get(i + 1)) {
                ++i;
            }
            if (i + 1 < starts.size()) {
                res.add(new Interval(ends.get(i), starts.get(i + 1)));
            }
            ++i;
        }
        return res;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // 1. merge all intervals
    // 2. find the free intervals
    public List<Interval> employeeFreeTime2(List<List<Interval>> schedule) {
        List<Interval> list = new ArrayList<>();
        for (List<Interval> l : schedule) {
            list.addAll(l);
        }
        list.sort((i1, i2) -> i1.start - i2.start);
        // merge intervals
        List<Interval> res = new ArrayList<>();
        int end = list.get(0).end;
        for (Interval interval : list) {
            if (interval.start > end) {
                res.add(new Interval(end, interval.start));
            }
            end = Math.max(end, interval.end);
        }
        return res;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // 1. find all free intervals
    // 2. sort by starts and ends
    // 3. find overlapped intervals which has the # of n
    public List<Interval> employeeFreeTime3(List<List<Interval>> schedule) {
        int n = schedule.size();
        List<Integer> starts = new ArrayList<>();
        List<Integer> ends = new ArrayList<>();
        for (List<Interval> intervals : schedule) {
            int start = Integer.MIN_VALUE;
            for (Interval interval : intervals) {
                starts.add(start);
                ends.add(interval.start);
                start = interval.end;
            }
            starts.add(start);
            ends.add(Integer.MAX_VALUE);
        }
        Collections.sort(starts);
        Collections.sort(ends);
        List<Interval> res = new ArrayList<>();
        for (int i = 0; i < ends.size() - n; ++i) {
            if (starts.get(i + n - 1) < ends.get(i) && starts.get(i + n - 1) != Integer.MIN_VALUE && ends.get(i) != Integer.MAX_VALUE) {
                res.add(new Interval(starts.get(i + n - 1), ends.get(i)));
            }
        }
        return res;
    }
}
