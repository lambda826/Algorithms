/**
 *  @author: Yunxiang He
 *  @date  : 2018-06-27
 */

package coding.temp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*

Given a positive integer n, find the least number of perfect square numbers (for example, 1, 4, 9, 16, ...) which sum to n.


Example 1:
    Input: n = 12
    Output: 3 
    Explanation: 12 = 4 + 4 + 4.

Example 2:
    Input: n = 13
    Output: 2
    Explanation: 13 = 4 + 9.

*/

public class _0279_Perfect_Squares {

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    int res = Integer.MAX_VALUE;
    List<Integer> list;

    public int numSquares_DFS(int n) {
        list = new ArrayList<>();
        for (int i = 1; i * i <= n; i++) {
            list.add(0, i * i);
        }
        DFS(n, 0);
        return res;
    }

    public void DFS(int n, int curr) {
        if (curr + 1 < res) {
            for (int i = 0; i < list.size(); i++) {
                if (n - list.get(i) == 0) {
                    res = Math.min(res, curr + 1);
                } else if (n - list.get(i) > 0) {
                    DFS(n - list.get(i), curr + 1);
                }
            }
        }
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int numSquares_DP(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j * j <= i; j++) {
                dp[i] = Math.min(dp[i], 1 + dp[i - j * j]);
            }
        }
        return dp[n];
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int numSquares_DFS_DP(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        for (int i = 1; i * i <= n; i++) {
            dp[i * i] = 1;
        }
        dp[0] = 0;
        DFS(dp, n);
        return dp[n];
    }

    public int DFS(int[] dp, int n) {
        if (dp[n] != Integer.MAX_VALUE) {
            return dp[n];
        } else {
            for (int i = 1; i * i <= n; i++) {
                dp[n] = Math.min(dp[n], 1 + DFS(dp, n - i * i));
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        _0279_Perfect_Squares test = new _0279_Perfect_Squares();
        System.out.println(test.numSquares_DFS(7691));
    }
}
