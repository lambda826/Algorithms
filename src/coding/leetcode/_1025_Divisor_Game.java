package coding.leetcode;


/*

Alice and Bob take turns playing a game, with Alice starting first.
Initially, there is a number N on the chalkboard.  On each player's turn, that player makes a move consisting of:
    Choosing any x with 0 < x < N and N % x == 0.
    Replacing the number N on the chalkboard with N - x.
Also, if a player cannot make a move, they lose the game.
Return True if and only if Alice wins the game, assuming both players play optimally.


Example 1:
    Input: 2
    Output: true
    Explanation: Alice chooses 1, and Bob has no more moves.

Example 2:
    Input: 3
    Output: false
    Explanation: Alice chooses 1, Bob chooses 1, and Alice has no more moves.


Note:
    1 <= N <= 1000


History:
    4/6/2020

*/

public class _1025_Divisor_Game {

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public boolean divisorGame_DP(int N) {
        boolean[] dp = new boolean[N + 1];
        for (int n = 2; n <= N; ++n) {
            for (int x = 1; x < n && !dp[n]; ++x) {
                if (n % x == 0) {
                    dp[n] = dp[x] || !dp[n - x];
                }
            }
        }
        return dp[N];
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //  Base case:
    //     When N = 1, Alice loses; When N = 2, Alice wins.
    //  Inductive step:
    //    Hypothesis: When N = 2k-1, Alice loses; When N = 2k, Alice wins. (For any k >= 1)
    //    Proof: When N = 2k+1, Alice has to subtract an odd from N then Bob wins due to the remaining even number no greater than 2k;
    //           When N = 2k+2, Alice can subtract 1 from N then Bob loses due to the remaining N equal to 2k+1.
    //  In conclusion, Alice wins the game when N is even; Alice loses the game when N is odd.
    public boolean divisorGame_Math(int N) {
        return N % 2 == 0;
    }
}
