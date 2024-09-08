/**
 * @author Yunxiang He
 * @date 07/29/2018
 */

package questions.temp;

import java.util.ArrayList;
import java.util.List;

/*

Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.

Strings consists of lowercase English letters only and the length of both strings s and p will not be larger than 20,100.

The order of output does not matter.


Example 1:
    Input:
    s: "cbaebabacd" p: "abc"
    Output:
    [0, 6]
    Explanation:
    The substring with start index = 0 is "cba", which is an anagram of "abc".
    The substring with start index = 6 is "bac", which is an anagram of "abc".

Example 2:
    Input:
    s: "abab" p: "ab"
    Output:
    [0, 1, 2]
    Explanation:
    The substring with start index = 0 is "ab", which is an anagram of "ab".
    The substring with start index = 1 is "ba", which is an anagram of "ab".
    The substring with start index = 2 is "ab", which is an anagram of "ab".

*/

public class _0438_Find_All_Anagrams_in_a_String {

    public static void main(String[] args) {
        System.out.println(new _0438_Find_All_Anagrams_in_a_String().findAnagrams("baa", "aa"));
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> list = new ArrayList<>();
        if (p.length() > s.length()) {
            return list;
        }
        int[] _p = new int[26];
        for (char ch : p.toCharArray()) {
            _p[ch - 'a']--;
        }
        for (int i = 0; i < p.length(); i++) {
            _p[s.charAt(i) - 'a']++;
        }
        if (valid(_p)) {
            list.add(0);
        }
        for (int i = 0, j = p.length(); j < s.length(); ++i, ++j) {
            _p[s.charAt(i) - 'a']--;
            _p[s.charAt(j) - 'a']++;
            if (valid(_p)) {
                list.add(i + 1);
            }
        }
        return list;
    }

    private boolean valid(int[] _p) {
        for (int i = 0; i < 26; i++) {
            if (_p[i] != 0) {
                return false;
            }
        }
        return true;
    }

}
