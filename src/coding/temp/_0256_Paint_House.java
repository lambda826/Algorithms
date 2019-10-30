/**
 *  @author Yunxiang He
 *  @date 01/29/2018
 */

package coding.temp;

/*

There are a row of n houses, each house can be painted with one of the three colors: red, blue or green. 
The cost of painting each house with a certain color is different. 
You have to paint all the houses such that no two adjacent houses have the same color.

The cost of painting each house with a certain color is represented by a n x 3 cost matrix. 
For example, costs[0][0] is the cost of painting house 0 with color red; costs[1][2] is the cost of painting house 1 with color green, and so on... 
Find the minimum cost to paint all houses.


Example:
    Input: [[17,2,17],[16,16,5],[14,3,19]]
    Output: 10
    Explanation: Paint house 0 into blue, paint house 1 into green, paint house 2 into blue. 
                 Minimum cost: 2 + 5 + 3 = 10.


Note:
    All costs are positive integers.

*/

public class _0256_Paint_House {

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int minCost_DP(int[][] costs) {
        if (costs.length == 0) {
            return 0;
        }
        int lastR = costs[0][0];
        int lastG = costs[0][1];
        int lastB = costs[0][2];
        for (int i = 1; i < costs.length; i++) {
            int curR = Math.min(lastG, lastB) + costs[i][0];
            int curG = Math.min(lastR, lastB) + costs[i][1];
            int curB = Math.min(lastR, lastG) + costs[i][2];
            lastR = curR;
            lastG = curG;
            lastB = curB;
        }
        return Math.min(Math.min(lastR, lastG), lastB);
    }
}
