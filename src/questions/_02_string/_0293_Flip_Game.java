package questions._02_string;

import java.util.ArrayList;
import java.util.List;

/*

You are playing a Flip Game with your friend.

You are given a string currentState that contains only '+' and '-'.
You and your friend take turns to flip two consecutive "++" into "--".
The game ends when a person can no longer make a move, and therefore the other person will be the winner.

Return all possible states of the string currentState after one valid move. You may return the answer in any order.
If there is no valid move, return an empty list [].


Example 1:
    Input:
        currentState = "++++"
    Output:
        ["--++","+--+","++--"]

Example 2:
    Input:
        currentState = "+"
    Output:
        []


Constraints:
    1 <= currentState.length <= 500
    currentState[i] is either '+' or '-'.

*/

public class _0293_Flip_Game {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    class Solution {
        public List<String> generatePossibleNextMoves(String currentState) {
            List<String> res = new ArrayList<>();
            StringBuilder sb = new StringBuilder(currentState);
            for (int i = 0; i < sb.length() - 1; ++i) {
                if (sb.charAt(i) == '+' && sb.charAt(i + 1) == '+') {
                    sb.setCharAt(i, '-');
                    sb.setCharAt(i + 1, '-');
                    res.add(sb.toString());
                    sb.setCharAt(i, '+');
                    sb.setCharAt(i + 1, '+');
                }
            }
            return res;
        }
    }

}