/**
 *  @author Yunxiang He
 *  @date Dec 26, 2017 1:17:57 AM
 */

package questions.temp;

/*

We are playing the Guess Game. 
The game is as follows:

I pick a number from 1 to n. 
You have to guess which number I picked.

Every time you guess wrong, I'll tell you whether the number I picked is higher or lower.

However, when you guess a particular number x, and you guess wrong, you pay $x. 
You win the game when you guess the number I picked.

Example:
n = 10, I pick 8.
First round:  You guess 5, I tell you that it's higher. You pay $5.
Second round: You guess 7, I tell you that it's higher. You pay $7.
Third round:  You guess 9, I tell you that it's lower. You pay $9.
Game over. 
8 is the number I picked.
You end up paying $5 + $7 + $9 = $21.
Given a particular n ≥ 1, find out how much money you need to have to guarantee a win.

*/

public class _0375_Guess_Number_Higher_or_Lower_II {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Find the local maximum and global minimum to guarantee the win
    public int getMoneyAmount_DP(int n) {
        int[][] dp = new int[n + 1][n + 1];
        return search(dp, 1, n);
    }

    private int search(int[][] dp, int start, int end) {
        if (dp[start][end] != 0) {
            return dp[start][end];
        } else if (start >= end) {
            return 0;
        } else if (end - start <= 2) {
            return end - 1;
        } else {
            int mid = (start + end) >> 1;
            int min = Integer.MAX_VALUE;
            for (int i = mid; i < end; i++) {
                min = Math.min(min, i + Math.max(search(dp, i + 1, end), search(dp, start, i - 1)));
            }
            return dp[start][end] = min;
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int getMoneyAmount_DP2(int n) {
        if (n == 1) {
            return 0;
        }
        int[][] minPrice = new int[n + 2][n + 2];
        for (int i = 1; i < n; i++) {
            for (int s = 1; s + i < n + 1; s++) {
                minPrice[s][s + i] = Integer.MAX_VALUE;
                for (int k = s; k <= i + s; k++) {
                    minPrice[s][s + i] = Math.min(minPrice[s][s + i], k + Math.max(minPrice[k + 1][s + i], minPrice[s][k - 1]));
                }
            }
        }
        return minPrice[1][n];
    }

    public static void main(String[] args) {
        System.out.println(new _0375_Guess_Number_Higher_or_Lower_II().getMoneyAmount_DP(9));
    }
}
