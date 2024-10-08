/**
 * @author: Yunxiang He
 * @date : 2018-07-14 00:47
 */

package questions.temp;

/*

Given two strings A and B of lowercase letters, return true if and only if we can swap two letters in A so that the result equals B.

Example 1:
Input: A = "ab", B = "ba"
Output: true

Example 2:
Input: A = "ab", B = "ab"
Output: false

Example 3:
Input: A = "aa", B = "aa"
Output: true

Example 4:
Input: A = "aaaaaaabc", B = "aaaaaaacb"
Output: true

Example 5:
Input: A = "", B = "aa"
Output: false


Note:
0 <= A.length <= 20000
0 <= B.length <= 20000
A and B consist only of lowercase letters.

*/

public class _0859_Buddy_Strings {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public boolean buddyStrings(String A, String B) {
        if (A.length() != B.length() || A.length() < 2 || B.length() < 2) {
            return false;
        } else if (A.equals(B)) {
            int[] count = new int[26];
            for (char c : A.toCharArray()) {
                count[c - 'a']++;
                if (count[c - 'a'] > 1) {
                    return true;
                }
            }
            return false;
        } else {
            int first = -1;
            int second = -1;
            for (int i = 0; i < A.length(); i++) {
                if (A.charAt(i) != B.charAt(i)) {
                    if (first == -1) {
                        first = i;
                    } else if (second == -1) {
                        second = i;
                    } else {
                        return false;
                    }
                }
            }
            return first != -1 && second != -1 && A.charAt(first) == B.charAt(second) && A.charAt(second) == B.charAt(first);
        }
    }
}
