/**
 *  @author: Yunxiang He
 *  @date  : 2018-10-08
 */

package coding.lintcode;

import java.util.ArrayList;
import java.util.List;

/*

Given a string S, we can transform every letter individually to be lowercase or uppercase to create another string. 
Return a list of all possible strings we could create.


Example
    Input: S = "a1b2"
    Output: ["a1b2", "a1B2", "A1b2", "A1B2"]

    Input: S = "3z4"
    Output: ["3z4", "3Z4"]

    Input: S = "12345"
    Output: ["12345"]


Notice
    S will be a string with length at most 12.
    S will consist only of letters or digits.

*/

public class __1032_Letter_Case_Permutation {

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public List<String> letterCasePermutation(String S) {
        List<String> permutation = new ArrayList<>();
        if ("".equals(S)) {
            permutation.add("");
        } else if (S != null) {
            DFS(S.toCharArray(), 0, permutation);
        }
        return permutation;
    }

    private void DFS(char[] s, int index, List<String> permutation) {
        if (index < s.length) {
            if (Character.isLetter(s[index])) {
                s[index] = Character.toLowerCase(s[index]);
                DFS(s, index + 1, permutation);
                s[index] = Character.toUpperCase(s[index]);
                DFS(s, index + 1, permutation);
            } else {
                DFS(s, index + 1, permutation);
            }
        } else {
            permutation.add(String.valueOf(s));
        }
    }

    public static void main(String[] args) {
        System.out.println(new __1032_Letter_Case_Permutation().letterCasePermutation("A2B3"));
    }

}
