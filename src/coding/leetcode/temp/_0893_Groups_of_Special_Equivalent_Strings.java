package coding.leetcode.temp;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/*

You are given an array A of strings.

Two strings S and T are special-equivalent if after any number of moves, S == T.
A move consists of choosing two indices i and j with i % 2 == j % 2, and swapping S[i] with S[j].
Now, a group of special-equivalent strings from A is a non-empty subset S of A such that any string not in S is not special-equivalent with any string in S.
Return the number of groups of special-equivalent strings from A.


Example 1:
    Input: ["a","b","c","a","c","c"]
    Output: 3
    Explanation: 3 groups ["a","a"], ["b"], ["c","c","c"]

Example 2:
    Input: ["aa","bb","ab","ba"]
    Output: 4
    Explanation: 4 groups ["aa"], ["bb"], ["ab"], ["ba"]

Example 3:
    Input: ["abc","acb","bac","bca","cab","cba"]
    Output: 3
    Explanation: 3 groups ["abc","cba"], ["acb","bca"], ["bac","cab"]

Example 4:
    Input: ["abcd","cdab","adcb","cbad"]
    Output: 1
    Explanation: 1 group ["abcd","cdab","adcb","cbad"]
 

Note:
    1 <= A.length <= 1000
    1 <= A[i].length <= 20
    All A[i] have the same length.
    All A[i] consist of only lowercase letters.

*/

public class _0893_Groups_of_Special_Equivalent_Strings {

    /**
     * i % 2 == j % 2 means i and j are both even or odd
     * The question can be reduced to find the number of groups of the strings which have same number of same character on even and odd position
     *
     * 1. Use a char arrays to count the number of characters in even/odd positions of each string, even start from index 0, odd starts from index 26
     * 2. Combine these two arrays
     * 3. Use a hash set to count deduplicate the strings which should be in the same group in order to count the set size
     */
    public int numSpecialEquivGroups(String[] A) {
        Set<String> set = new HashSet<>();
        for (String str : A) {
            char[] chs = new char[52];
            for (int i = 0; i < str.length(); ++i) {
                if ((i & 1) == 0) {
                    ++chs[str.charAt(i) - 'a'];
                } else {
                    ++chs[str.charAt(i) - 'a' + 26];
                }
            }
            set.add(Arrays.toString(chs));
        }
        return set.size();
    }

}
