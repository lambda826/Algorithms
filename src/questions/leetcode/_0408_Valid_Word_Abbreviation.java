package questions.leetcode;

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

public class _0408_Valid_Word_Abbreviation {

    class Solution {
        public boolean validWordAbbreviation(String word, String abbr) {
            int i = 0;
            int j = 0;
            while (j < abbr.length()) {
                if (Character.isLetter(abbr.charAt(j))) {
                    if (i >= word.length() || word.charAt(i) != abbr.charAt(j)) {
                        return false;
                    }
                    ++i;
                    ++j;
                } else {
                    int k = 0;
                    boolean first = true;
                    while (j < abbr.length() && Character.isDigit(abbr.charAt(j))) {
                        k = k * 10 + (abbr.charAt(j) - '0');
                        ++j;
                        if (first && k == 0) {
                            return false;
                        } else {
                            first = false;
                        }
                    }
                    if (k == 0) {
                        return false;
                    } else {
                        i += k;
                    }
                }
            }
            return i == word.length() && j == abbr.length();
        }
    }

}
