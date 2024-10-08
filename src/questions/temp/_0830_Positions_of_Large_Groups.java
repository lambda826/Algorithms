/**
 * @author: Yunxiang He
 * @date : 2018-06-24 00:52
 */

package questions.temp;

import java.util.ArrayList;
import java.util.List;

/*

In a string S of lowercase letters, these letters form consecutive groups of the same character.

For example, a string like S = "abbxxxxzyy" has the groups "a", "bb", "xxxx", "z" and "yy".

Call a group large if it has 3 or more characters.  We would like the starting and ending positions of every large group.

The final answer should be in lexicographic order.

 

Example 1:

Input: "abbxxxxzzy"
Output: [[3,6]]
Explanation: "xxxx" is the single large group with starting  3 and ending positions 6.
Example 2:

Input: "abc"
Output: []
Explanation: We have "a","b" and "c" but no large group.
Example 3:

Input: "abcdddeeeeaabbbcd"
Output: [[3,5],[6,9],[12,14]]
 

Note:  1 <= S.length <= 1000

*/

public class _0830_Positions_of_Large_Groups {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public List<List<Integer>> largeGroupPositions(String S) {
        List<List<Integer>> res = new ArrayList<>();
        if (S == "") {
            return res;
        }
        int start = 0;
        char startChar = S.charAt(0);
        for (int i = 1; i < S.length(); i++) {
            if (startChar != S.charAt(i)) {
                if (i - start >= 3) {
                    List<Integer> list = new ArrayList<>();
                    list.add(start);
                    list.add(i - 1);
                    res.add(list);
                }
                start = i;
                startChar = S.charAt(i);
            }
        }
        if (S.length() - start >= 3) {
            List<Integer> list = new ArrayList<>();
            list.add(start);
            list.add(S.length() - 1);
            res.add(list);
        }
        return res;
    }

    public static void main(String[] args) {
        new _0830_Positions_of_Large_Groups().largeGroupPositions("abbxxxxzzy");
    }
}
