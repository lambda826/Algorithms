/**
 *  @author: Yunxiang He
 *  @date  : 2018-06-20 18:02
 */

package coding.lintcode;

import common.Interval;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/*

391. Number of Airplanes in the Sky
Given an interval list which are flying and landing time of the flight. How many airplanes are on the sky at most?

Example
For interval list

[
  (1,10),
  (2,3),
  (5,8),
  (4,7)
]
Return 3

 */

public class __0391_Number_of_Airplanes_in_the_Sky {

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Schedual patitioning
    public int countOfAirplanes(List<Interval> airplanes) {
        if (airplanes == null || airplanes.size() == 0) {
            return 0;
        }
        // 1. Sort the intervals by start time
        Collections.sort(airplanes, new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                return o1.getStart() - o2.getStart();
            }
        });

        // 2. Assign labers
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int label = 1;
        pq.add(airplanes.get(0).getEnd());
        for (int i = 1; i < airplanes.size(); i++) {
            if (airplanes.get(i).getStart() >= pq.peek()) {
                pq.remove();
            } else {
                label++;
            }
            pq.add(airplanes.get(i).getEnd());
        }
        return label;
    }
}
