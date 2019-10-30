/**
 *  @author: Yunxiang He
 *  @date  : 2018-10-04
 */

package coding.lintcode;

import java.util.List;

/*

Given a List of intervals, the length of each interval is 1000, such as [500,1500], [2100,3100].
Give a number arbitrarily and determine if the number belongs to any of the intervals.
return True or False.


Example
Given:
List = [[100,1100],[1000,2000],[5500,6500]]
number = 6000
Return: True

*/

public class __1564_Interval_Search {

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public String isInterval(List<List<Integer>> intervalList, int number) {
        for (List<Integer> interval : intervalList) {
            if (number >= interval.get(0) && number <= interval.get(1)) {
                return "True";
            }
        }
        return "False";
    }
}
