/**
 *  @author: Yunxiang He
 *  @date  : 2018-06-25 18:04
 */

package questions.temp;

/*

In a row of seats, 1 represents a person sitting in that seat, and 0 represents that the seat is empty. 

There is at least one empty seat, and at least one person sitting.

Alex wants to sit in the seat such that the distance between him and the closest person to him is maximized. 

Return that maximum distance to closest person.

Example 1:
Input: [1,0,0,0,1,0,1]
Output: 2
Explanation: 
If Alex sits in the second open seat (seats[2]), then the closest person has distance 2.
If Alex sits in any other open seat, the closest person has distance 1.
Thus, the maximum distance to the closest person is 2.

Example 2:
Input: [1,0,0,0]
Output: 3
Explanation: 
If Alex sits in the last seat, the closest person is 3 seats away.
This is the maximum distance possible, so the answer is 3.

Note:
1 <= seats.length <= 20000
seats contains only 0s or 1s, at least one 0, and at least one 1.

*/

public class _0849_Maximize_Distance_to_Closest_Person {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Find the segment of 0s
    // Compute the length
    // Case1 leftmost
    // Case2 middle
    // Case3 rightmost
    public int maxDistToClosest(int[] seats) {
        int start = 0;
        int max = 0;
        for (int i = 0; i < seats.length; i++) {
            if (seats[i] == 0) {
                start = i;
                while (i < seats.length && seats[i] == 0) {
                    i++;
                }
                if (start >= i) {
                    max = 0;
                } else if (start == 0 || i == seats.length) {
                    max = Math.max(max, i - start - 1);
                } else {
                    max = Math.max(max, (i - start - 1) / 2);
                }
            }
        }
        return max + 1;
    }

    public static void main(String[] args) {
        System.out.println(new _0849_Maximize_Distance_to_Closest_Person().maxDistToClosest(new int[] { 1, 0, 0, 0, 1 }));
    }
}
