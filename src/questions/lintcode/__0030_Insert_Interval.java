/**
 * @author: Yunxiang He
 * @date : 2018-10-11
 */

package questions.lintcode;

/*

Given a non-overlapping interval list which is sorted by start point.

Insert a new interval into it, make sure the list is still in order and non-overlapping (merge intervals if necessary).


Example
    Insert (2, 5) into [(1,2), (5,9)], we get [(1,9)].
    Insert (3, 4) into [(1,2), (5,9)], we get [(1,2), (3,4), (5,9)].

*/

public class __0030_Insert_Interval {

    //    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
    //        int startIndex = Collections.binarySearch(intervals, newInterval, getCmp(0));
    //        int endIndex = Collections.binarySearch(intervals, newInterval, getCmp(0));
    //
    //    }
    //
    //    private Comparator<Interval> getCmp(int k) {
    //        new Comparator<Interval>() {
    //            public int compare(Interval o1, Interval o2) {
    //                if (k == 0) {
    //                    return o1.start - o2.start;
    //                } else {
    //                    return o1.end - o2.end;
    //                }
    //            }
    //        };
    //    }
}
