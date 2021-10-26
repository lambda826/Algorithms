package coding.other;


import common.Interval;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/*

Given A and B two interval lists, A has no overlap inside A and B has no overlap inside B.
Write the function to merge two interval lists, output the result with no overlap.
Ask for a very efficient solution


For example,
    A: [1,5], [10,14], [16,18]
    B: [2,6], [8,10], [11,20]

    output [1,6], [8, 20]

*/
public class Facebook_Merge_Two_Interval_Lists {

    class Solution_TowPointer {



        public List<Interval> mergeTwoIntervalLists(List<Interval> interval1, List<Interval> interval2) {
            List<Interval> res = new ArrayList<>();
            Collections.sort(interval1, Comparator.comparingInt(interval -> interval.start));
            Collections.sort(interval2, Comparator.comparingInt(interval -> interval.start));
            int p1 = 0;
            int p2 = 0;
            while (p1 < interval1.size() || p2 < interval2.size()) {
                if (p1 == interval1.size()) {
                    res.add(interval2.get(p2));
                    ++p2;
                } else if (p2 == interval2.size()) {
                    res.add(interval1.get(p1));
                    ++p1;
                } else {

                }
            }
            return res;
        }

    }

}
