/**
 *  @author: Yunxiang He
 *  @date  : 2018-10-04
 */

package coding.lintcode;

import java.util.Arrays;

/*

There is a one-dimensional board with a starting point on the far left side of the board and an end point on the far right side of the board. 
There are several positions on the board that are connected to other positions, ie if A is connected to B, then when chess falls at position A, you can choose whether to move the chess from A to B. 
And the connection is one way, which means that the chess cannot move from B to A. 
Now you have a six-sided dice, find the minimum steps to reach the end.


Example
input:
length = 10
connections = [[2, 10]]
output:
1


Notice
the index starts from 1.
length > 1
The starting point is not connected to any other location
connections[i][0] < connections[i][1]

*/

public class __1565_Modern_Ludo_I {

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int modernLudo(int length, int[][] connections) {
        int[] dp = new int[length + 1];
        Arrays.fill(dp, length);
        for (int i = 2; i < 8; i++) {
            dp[i] = 1;
        }
        dp[1] = 0;
        for (int i = 8; i <= length; i++) {
            for (int[] connection : connections) {
                if (i == connection[1]) {
                    dp[i] = Math.min(dp[i], dp[connection[0]]);
                }
            }
            for (int d = 1; d <= 6; d++) {
                dp[i] = Math.min(dp[i - d] + 1, dp[i]);
            }
        }
        return dp[length];
    }

    public static void main(String[] args) {
        new __1565_Modern_Ludo_I().modernLudo(15, new int[][] { { 7, 9 }, { 8, 14 } });
        //        new _1565_Modern_Ludo_I().modernLudo(10, new int[][] { { 2, 10 }});
    }
}
