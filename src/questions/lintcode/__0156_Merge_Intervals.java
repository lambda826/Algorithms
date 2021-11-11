/**
 *  @author: Yunxiang He
 *  @date  : 2018-10-09
 */

package questions.lintcode;

import common.Interval;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/*

Given a collection of intervals, merge all overlapping intervals.


Example
    Given intervals => merged intervals:

    [                     [
      (1, 3),               (1, 6),
      (2, 6),      =>       (8, 10),
      (8, 10),              (15, 18)
      (15, 18)            ]
    ]


Challenge
    O(n log n) time and O(1) extra space.

*/

public class __0156_Merge_Intervals {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public List<Interval> merge(List<Interval> intervals) {
        Collections.sort(intervals, getCmp());
        List<Interval> list = new ArrayList<>();
        Interval i1 = intervals.get(0);
        Interval i2;
        for (int i = 1; i < intervals.size(); i++) {
            i2 = list.get(i);
            if (i1.end < i2.start) {
                list.add(i1);
            } else {
                i1.end = i2.end;
            }
        }
        return list;
    }

    private Comparator<Interval> getCmp() {
        return new Comparator<Interval>() {
            @Override
            public int compare(Interval i1, Interval i2) {
                if (i1.start == i2.start) {
                    return i2.end - i1.end;
                } else {
                    return i1.start - i2.start;
                }
            }
        };
    }
}
