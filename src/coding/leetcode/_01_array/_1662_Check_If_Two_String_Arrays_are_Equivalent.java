package coding.leetcode._01_array;

/*

Given two string arrays word1 and word2, return true if the two arrays represent the same string, and false otherwise.
A string is represented by an array if the array elements concatenated in order forms the string.


Example 1:
    Input:
        word1 = ["ab", "c"], word2 = ["a", "bc"]
    Output:
        true
    Explanation:
        word1 represents string "ab" + "c" -> "abc"
        word2 represents string "a" + "bc" -> "abc"
        The strings are the same, so return true.

Example 2:
    Input:
        word1 = ["a", "cb"], word2 = ["ab", "c"]
    Output:
        false

Example 3:
    Input:
        word1  = ["abc", "d", "defg"], word2 = ["abcddefg"]
    Output:
        true


Constraints:
    1 <= word1.length, word2.length <= 1000
    1 <= word1[i].length, word2[i].length <= 1000
    1 <= sum(word1[i].length), sum(word2[i].length) <= 1000
    word1[i] and word2[i] consist of lowercase letters.

*/

public class _1662_Check_If_Two_String_Arrays_are_Equivalent {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public boolean arrayStringsAreEqual(String[] word1, String[] word2) {
        int i1 = 0;
        int j1 = 0;
        int i2 = 0;
        int j2 = 0;
        while (i1 < word1.length && i2 < word2.length) {
            if (word1[i1].charAt(j1++) != word2[i2].charAt(j2++)) {
                return false;
            }
            if (j1 == word1[i1].length()) {
                j1 = 0;
                ++i1;
            }
            if (j2 == word2[i2].length()) {
                j2 = 0;
                ++i2;
            }
        }
        return i1 == word1.length && j1 == 0 && i2 == word2.length && j2 == 0;
    }
}
