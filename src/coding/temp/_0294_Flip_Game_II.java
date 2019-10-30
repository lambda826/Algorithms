/**
 *  @author Yunxiang He
 *  @date Dec 25, 2017 10:30:59 AM
 */

package coding.temp;

import java.util.HashMap;
import java.util.Map;

/*

You are playing the following Flip Game with your friend: 
    Given a string that contains only these two characters: + and -, you and your friend take turns to flip two consecutive "++" into "--". 
    The game ends when a person can no longer make a move and therefore the other person will be the winner.

Write a function to determine if the starting player can guarantee a win.


Example:
    Input: s = "++++"
    Output: true 
    Explanation: The starting player can guarantee a win by flipping the middle "++" to become "+--+".


Follow up:
    Derive your algorithm's runtime complexity.

*/

public class _0294_Flip_Game_II {

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private Map<String, Boolean> map = new HashMap<>();

    public boolean canWin_DFS_DP(String s) {
        if (s.length() < 2) {
            return false;
        }
        return DFS(s.toCharArray());
    }

    private boolean DFS(char[] chs) {
        String ss = String.valueOf(chs);
        if (map.containsKey(ss)) {
            return map.get(ss);
        }
        boolean canWin = false;
        for (int i = 1; i < chs.length && !canWin; i++) {
            if (chs[i] == '+' && chs[i - 1] == '+') {
                chs[i] = '-';
                chs[i - 1] = '-';
                // If any sub state will lead to lose
                if (!DFS(chs)) {
                    canWin = true;
                }
                chs[i] = '+';
                chs[i - 1] = '+';
            }
        }
        map.put(ss, canWin);
        return canWin;
    }

    public static void main(String[] args) {
        String test = "+++++++";
        System.out.println(new _0294_Flip_Game_II().canWin_DFS_DP(test));
    }
}
