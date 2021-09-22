/**
 *  @author Yunxiang He
 *  @date 02/14/2019
 */

package coding.leetcode.temp;

import java.util.Arrays;

/*

In a N x N grid representing a field of cherries, each cell is one of three possible integers.
    0 means the cell is empty, so you can pass through;
    1 means the cell contains a cherry, that you can pick up and pass through;
    -1 means the cell contains a thorn that blocks your way.

Your task is to collect maximum number of cherries possible by following the rules below:
    Starting at the position (0, 0) and reaching (N-1, N-1) by moving right or down through valid path cells (cells with value 0 or 1);
    After reaching (N-1, N-1), returning to (0, 0) by moving left or up through valid path cells;
    When passing through a path cell containing a cherry, you pick it up and the cell becomes an empty cell (0);
    If there is no valid path between (0, 0) and (N-1, N-1), then no cherries can be collected.
 

Example 1:
    Input: grid =
        [[0, 1, -1],
         [1, 0, -1],
         [1, 1,  1]]
    Output: 
        5
    Explanation: 
        The player started at (0, 0) and went down, down, right right to reach (2, 2).
        4 cherries were picked up during this single trip, and the matrix becomes [[0,1,-1],[0,0,-1],[0,0,0]].
        Then, the player went left, up, up, left to return home, picking up one more cherry.
        The total number of cherries picked up is 5, and this is the maximum possible.


Note:
    grid is an N by N 2D array, with 1 <= N <= 50.
    Each grid[i][j] is an integer in the set {-1, 0, 1}.
    It is guaranteed that grid[0][0] and grid[N-1][N-1] are not -1.


*/

public class _0741_Cherry_Pickup {

    public static void main(String[] args) {
        _0741_Cherry_Pickup test = new _0741_Cherry_Pickup();
        System.out.println(test.cherryPickup(new int[][] { { 1, 1, -1 }, { 1, -1, 1 }, { -1, 1, 1 } }));
        System.out.println(test.cherryPickup2(new int[][] { { 1, 1, -1 }, { 1, -1, 1 }, { -1, 1, 1 } }));
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // 正反两次走，等价于分别做两次正着走。
    // 问题就变成分别走两次找最大收益，其中第一次走过的1会变成0。
    // 简化理解，可以是2个人同时正着走，且速度一样，希望两人总体的收益最大，如果它们同时走到一个格子上，那它们只能拿一次。
    // 可以简单理解一下为什么这个问题，和刚才的问题等价：设速度都是1，则第t个时刻，设第一个人走到(x1, y1)，第二个人走到(x2, y2)，
    // 那么一定有x1 + y1 = t，x2 + y2 = t，假如x1 != x2，那么这一次行程中，第一个人永远不会走到(x2, y2)，同理第二人永远不会走到(x1, y1)。
    // 因此，拿重的问题只会在它们同时走到一个格子的时候遇到，因此我们判断他们每个时刻是否会到达同一个格子就可以去重了。
    // 把这个思想转换成dp的状态，则可以表示为dp(t, x1, x2)，也就是第t时刻第一个人走到(x1, t - x1)，第二个人走到(x2, t - x2)时两人的最大收益。
    // 状态转移也非常简单：dp(t, x1, x2) = grid(x1, t - x1) + (x1 == x2 ? 0 : grid(x2, t - x2)) + max(dp(t-1, x1, x2), dp(t - 1, x1, x2 - 1), dp(t - 1, x1 - 1, x2), dp(t - 1, x1 - 1, x2 - 1))
    // 最后就是t这一维我们可以通过滚动数组压掉，注意这样的话需要反向遍历更新dp
    public int cherryPickup(int[][] grid) {
        int n = grid.length;
        int step = (n << 1) - 1;
        int[][][] dp = new int[step][n][n];
        dp[0][0][0] = grid[0][0];
        for (int s = 1; s < step; ++s) {
            for (int x1 = 0; x1 < n && x1 <= s; ++x1) {
                for (int x2 = 0; x2 < n && x2 <= s; ++x2) {
                    int y1 = s - x1;
                    int y2 = s - x2;
                    if (y1 < 0 || y2 < 0 || y1 >= n || y2 >= n || grid[x1][y1] == -1 || grid[x2][y2] == -1) {
                        dp[s][x1][x2] = -1;
                    }
                    if (x1 > 0) {
                        dp[s][x1][x2] = Math.max(dp[s][x1][x2], dp[s - 1][x1 - 1][x2]);
                    }
                    if (x2 > 0) {
                        dp[s][x1][x2] = Math.max(dp[s][x1][x2], dp[s - 1][x1][x2 - 1]);
                    }
                    if (x1 > 0 && x2 > 0) {
                        dp[s][x1][x2] = Math.max(dp[s][x1][x2], dp[s - 1][x1 - 1][x2 - 1]);
                    }
                    dp[s][x1][x2] += grid[x1][y1] + (x1 == x2 ? 0 : grid[x2][y2]);
                }
            }
        }
        return Math.max(0, dp[step - 1][n - 1][n - 1]);
    }

    public int cherryPickup2(int[][] grid) {
        int n = grid.length;
        int[][][] dp = new int[n + 1][n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }
        dp[1][1][1] = grid[0][0];
        for (int x1 = 1; x1 <= n; x1++) {
            for (int y1 = 1; y1 <= n; y1++) {
                for (int x2 = 1; x2 <= n; x2++) {
                    int y2 = x1 + y1 - x2;
                    if (dp[x1][y1][x2] > 0 || y2 < 1 || y2 > n || grid[x1 - 1][y1 - 1] == -1 || grid[x2 - 1][y2 - 1] == -1) {
                        continue;
                    }
                    int temp = Math.max(Math.max(dp[x1 - 1][y1][x2], dp[x1 - 1][y1][x2 - 1]), Math.max(dp[x1][y1 - 1][x2], dp[x1][y1 - 1][x2 - 1]));
                    if (temp >= 0) {
                        dp[x1][y1][x2] = temp + grid[x1 - 1][y1 - 1] + (x1 == x2 ? 0 : grid[x2 - 1][y2 - 1]);
                    }
                }
            }
        }
        return dp[n][n][n] < 0 ? 0 : dp[n][n][n];
    }

    public int cherryPickup3(int[][] grid) {
        int N = grid.length;
        int T = (N << 1) - 1;
        int[][] dp = new int[N][N];
        dp[0][0] = grid[0][0];
        for (int t = 1; t < T; t++) {
            for (int x1 = N - 1; x1 >= 0; x1--) {
                for (int x2 = N - 1; x2 >= 0; x2--) {
                    int y1 = t - x1;
                    int y2 = t - x2;
                    if (y1 < 0 || y1 >= N || y2 < 0 || y2 >= N || grid[x1][y1] < 0 || grid[x2][y2] < 0) {
                        dp[x1][x2] = -1;
                        continue;
                    }
                    if (x1 > 0) {
                        dp[x1][x2] = Math.max(dp[x1][x2], dp[x1 - 1][x2]);
                    }
                    if (x2 > 0) {
                        dp[x1][x2] = Math.max(dp[x1][x2], dp[x1][x2 - 1]);
                    }
                    if (x1 > 0 && x2 > 0) {
                        dp[x1][x2] = Math.max(dp[x1][x2], dp[x1 - 1][x2 - 1]);
                    }
                    if (dp[x1][x2] >= 0) {
                        dp[x1][x2] += grid[x1][y1] + (x1 != x2 ? grid[x2][y2] : 0);
                    }
                }
            }
        }
        return Math.max(dp[N - 1][N - 1], 0);
    }
}
