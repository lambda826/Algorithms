package questions.temp;

import java.util.ArrayList;
import java.util.List;

/*

You are playing the following Flip Game with your friend: 
    Given a string that contains only these two characters: + and -, 
    you and your friend take turns to flip two consecutive "++" into "--". 
    The game ends when a person can no longer make a move and therefore the other person will be the winner.

Write a function to compute all possible states of the string after one valid move.


Example:
    Input: s = "++++"
    Output: 
    [
      "--++",
      "+--+",
      "++--"
    ]


Note:
    If there is no valid move, return an empty list [].

*/

public class _0293_Flip_Game {

    /**
     * 1. Go through the string
     * 2. Flip "++" into "--" and add to the result list
     * 3. Restore
     */
    public List<String> generatePossibleNextMoves(String s) {
        List<String> list = new ArrayList<>();
        StringBuilder sb = new StringBuilder(s);
        for (int i = 0; i < s.length() - 1; ++i) {
            if (s.charAt(i) == '+' && s.charAt(i + 1) == '+') {
                sb.setCharAt(i, '-');
                sb.setCharAt(i + 1, '-');
                list.add(sb.toString());
                sb.setCharAt(i, '+');
                sb.setCharAt(i + 1, '+');
            }
        }
        return list;
    }

}
