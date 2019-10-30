/**
 *  @author: Yunxiang He
 *  @date  : 2018-06-27
 */

package coding.temp;

/*

Given two strings S and T, return if they are equal when both are typed into empty text editors. 
# means a backspace character.

Example 1:
    Input: 
        S = "ab#c", T = "ad#c"
    Output: 
        true
    Explanation: 
        Both S and T become "ac".

Example 2:
    Input: 
        S = "ab##", T = "c#d#"
    Output: 
        true
    Explanation: 
        Both S and T become "".

Example 3:
    Input: 
        S = "a##c", T = "#a#c"
    Output: 
        true
    Explanation: 
        Both S and T become "c".

Example 4:
    Input: 
        S = "a#c", T = "b"
    Output: 
        false
    Explanation: 
        S becomes "c" while T becomes "b".


Note:
    1 <= S.length <= 200
    1 <= T.length <= 200
    S and T only contain lowercase letters and '#' characters.


Follow up:
    Can you solve it in O(N) time and O(1) space?

*/

public class _0844_Backspace_String_Compare {

    public static void main(String[] args) {
        _0844_Backspace_String_Compare _0844_Backspace_String_Compare = new _0844_Backspace_String_Compare();
        _0844_Backspace_String_Compare.backspaceCompare2("n\nzp#o#g", "b#nzp#o#g");
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public boolean backspaceCompare(String S, String T) {
        int slen = S.length() - 1;
        int spond = 0;
        int tlen = T.length() - 1;
        int tpond = 0;
        // Compare from the tails
        while (slen >= 0 || tlen >= 0) {
            while (slen >= 0 && (S.charAt(slen) == '#' || spond > 0)) {
                if (S.charAt(slen) == '#') {
                    spond++;
                } else {
                    spond--;
                }
                slen--;
            }
            while (tlen >= 0 && (T.charAt(tlen) == '#' || tpond > 0)) {
                if (T.charAt(tlen) == '#') {
                    tpond++;
                } else {
                    tpond--;
                }
                tlen--;
            }
            if (slen >= 0 && tlen >= 0 && S.charAt(slen) != T.charAt(tlen)) {
                return false;
            } else {
                slen--;
                tlen--;
            }
        }
        return slen == tlen;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public boolean backspaceCompare2(String S, String T) {
        return reduce(S).equals(reduce(T));
    }

    private String reduce(String str) {
        StringBuilder sb = new StringBuilder();
        // If ch is not #, append
        // Else delete one char
        for (char ch : str.toCharArray()) {
            if (ch == '#') {
                if (sb.length() > 0) {
                    sb.deleteCharAt(sb.length() - 1);
                }
            } else {
                sb.append(ch);
            }
        }
        System.out.println(sb.toString());
        return sb.toString();
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public boolean backspaceCompare_FollowUp_Upcase(String S, String T) {
        return reduce_upcase(S).equals(reduce_upcase(T));
    }

    private String reduce_upcase(String str) {
        StringBuilder sb = new StringBuilder();
        boolean isCap = false;
        for (char ch : str.toCharArray()) {
            if (ch == '#') {
                if (sb.length() > 0) {
                    sb.deleteCharAt(sb.length() - 1);
                }
            } else if (ch == '\n') {
                isCap = !isCap;
            } else {
                if (isCap) {
                    sb.append(Character.toUpperCase(ch));
                    isCap = false;
                } else {
                    sb.append(ch);
                }
            }
        }
        System.out.println(sb.toString());
        return sb.toString();
    }
}
