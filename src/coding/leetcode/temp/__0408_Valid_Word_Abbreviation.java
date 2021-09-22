/**
 *  @author: Yunxiang He
 *  @date  : 2018-07-13 23:54
 */

package coding.leetcode.temp;

/*

Given a non-empty string s and an abbreviation abbr, return whether the string matches with the given abbreviation.

A string such as "word" contains only the following valid abbreviations:

["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d", "1or1", "w1r1", "1o2", "2r1", "3d", "w3", "4"]
Notice that only the above abbreviations are valid abbreviations of the string "word". 
Any other string is not a valid abbreviation of "word".

Note:
Assume s contains only lowercase letters and abbr contains only lowercase letters and digits.

Example 1:
Given s = "internationalization", abbr = "i12iz4n":
Return true.

Example 2:
Given s = "apple", abbr = "a2e":
Return false.

*/

public class __0408_Valid_Word_Abbreviation {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public boolean validWordAbbreviation(String word, String abbr) {
        int i = 0;
        int j = 0;
        while (i < abbr.length()) {
            char c = abbr.charAt(i);
            if (c >= 'a' && c <= 'z') {
                if (j >= word.length() || c != word.charAt(j)) {
                    return false;
                }
                i++;
                j++;
            } else {
                if (abbr.charAt(i) == '0') {
                    return false;
                }
                int start = i;
                while (i < abbr.length() && abbr.charAt(i) >= '0' && abbr.charAt(i) <= '9') {
                    i++;
                }
                j += Integer.valueOf(abbr.substring(start, i));
            }
        }
        return j == word.length() && i == abbr.length();
    }
}
