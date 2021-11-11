package questions._12_dynamicProgramming.oneDimension;


/*

Alice and Bob take turns playing a game, with Alice starting first.
Initially, there is a number n on the chalkboard. On each player's turn, that player makes a move consisting of:
    Choosing any x with 0 < x < n and n % x == 0.
    Replacing the number n on the chalkboard with n - x.
    Also, if a player cannot make a move, they lose the game.
Return true if and only if Alice wins the game, assuming both players play optimally.


Example 1:
    Input: n = 2
    Output: true
    Explanation: Alice chooses 1, and Bob has no more moves.

Example 2:
    Input: n = 3
    Output: false
    Explanation: Alice chooses 1, Bob chooses 1, and Alice has no more moves.


Constraints:
    1 <= n <= 1000

*/

public class _1025_Divisor_Game {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public boolean divisorGame(int n) {
        // dp[i] indicates if the number is i, the first play can always win or lose.
        boolean[] dp = new boolean[n + 1];
        for (int i = 2; i < dp.length; ++i) {
            for (int x = 1; x < i && !dp[i]; ++x) {
                if (i % x == 0) {
                    dp[i] = !dp[i - x];
                }
            }
        }
        return dp[n];
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Assume: For any k >= 1, when
    //         N = 2k - 1: lose
    //         N = 2k: win
    //  Base case:
    //     When k = 1, 1: lose, 2: win
    //  Inductive step:
    //    Proof: When N = 2k+1, Alice (has to subtract an odd from N due to the constraint (n % k == 0) ) then Bob wins due to the remaining even number no greater than 2k;
    //           When N = 2k+2, Alice can subtract 1 from N then Bob loses due to the remaining N equal to 2k+1.
    //  In conclusion, Alice wins the game when N is even; Alice loses the game when N is odd.
    public boolean divisorGame_Math(int n) {
        return n % 2 == 0;
    }
}
