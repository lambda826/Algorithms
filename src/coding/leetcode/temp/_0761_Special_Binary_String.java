/**
 *  @author: Yunxiang He
 *  @date  : 2018-06-27
 */

package coding.leetcode.temp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*

Special binary strings are binary strings with the following two properties:
    The number of 0's is equal to the number of 1's.
    Every prefix of the binary string has at least as many 1's as 0's.
Given a special string S, a move consists of choosing two consecutive, non-empty, special substrings of S, and swapping them. 
(Two strings are consecutive if the last character of the first string is exactly one index before the first character of the second string.)
At the end of any number of moves, what is the lexicographically largest resulting string possible?


Example 1:
    Input: 
        S = "11011000"
    Output: 
        "11100100"
    Explanation:
        The strings "10" [occuring at S[1]] and "1100" [at S[3]] are swapped.
        This is the lexicographically largest string possible after some number of swaps.


Note:
    S has length at most 50.
    S is guaranteed to be a special binary string as defined above.


*/

public class _0761_Special_Binary_String {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public String makeLargestSpecial(String S) {
        List<String> list = new ArrayList<>();
        int count = 0;
        int i = 0;
        int j = 0;
        while (j < S.length()) {
            if (S.charAt(j) == '1') {
                count++;
            } else {
                count--;
            }
            if (count == 0) {
                list.add("1" + makeLargestSpecial(S.substring(i + 1, j)) + "0");
                i = j + 1;

            }
            j++;
        }
        Collections.sort(list, Collections.reverseOrder());
        return String.join("", list);
    }

    public static void main(String[] args) {
        System.out.println(new _0761_Special_Binary_String().makeLargestSpecial("11011000"));
    }
}
