package coding.leetcode.temp;

/*

Given a string S, return the number of substrings that have only one distinct letter.


Example 1:
    Input: S = "aaaba"
    Output: 8
    Explanation: The substrings with one distinct letter are "aaa", "aa", "a", "b".
    "aaa" occurs 1 time.
    "aa" occurs 2 times.
    "a" occurs 4 times.
    "b" occurs 1 time.
    So the answer is 1 + 2 + 4 + 1 = 8.

Example 2:
    Input: S = "aaaaaaaaaa"
    Output: 55


Constraints:
    1 <= S.length <= 1000
    S[i] consists of only lowercase English letters.

*/

public class _1180_Count_Substrings_with_Only_One_Distinct_Letter {

    /**
     * 1. Whenever different character occurs, count the num of the qualified substring
     */
    public int countLetters(String S) {
        S += "A";
        int count = 0;
        for (int l = 0, r = 0; r < S.length(); ++r) {
            if (S.charAt(r) != S.charAt(l)) {
                count += (r - l + 1) * (r - l) / 2;
                l = r;
            }
        }
        return count;
    }
}
