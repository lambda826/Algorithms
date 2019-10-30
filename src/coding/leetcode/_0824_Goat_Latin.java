package coding.leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/*

A sentence S is given, composed of words separated by spaces. 
Each word consists of lowercase and uppercase letters only.

We would like to convert the sentence to "Goat Latin" (a made-up language similar to Pig Latin.)

The rules of Goat Latin are as follows:
    If a word begins with a vowel (a, e, i, o, u), append "ma" to the end of the word.
    For example, the word 'apple' becomes 'applema'.
 
    If a word begins with a consonant (i.e. not a vowel), remove the first letter and append it to the end, then add "ma".
    For example, the word "goat" becomes "oatgma".
 
    Add one letter 'a' to the end of each word per its word index in the sentence, starting with 1.
    For example, the first word gets "a" added to the end, the second word gets "aa" added to the end and so on.

Return the final sentence representing the conversion from S to Goat Latin. 


Example 1:
    Input: "I speak Goat Latin"
    Output: "Imaa peaksmaaa oatGmaaaa atinLmaaaaa"

Example 2:
    Input: "The quick brown fox jumped over the lazy dog"
    Output: "heTmaa uickqmaaa rownbmaaaa oxfmaaaaa umpedjmaaaaaa overmaaaaaaa hetmaaaaaaaa azylmaaaaaaaaa ogdmaaaaaaaaaa"
 

Notes:
    S contains only uppercase, lowercase and spaces. Exactly one space between each word.
    1 <= S.length <= 150.

*/

public class _0824_Goat_Latin {

    /**
     * 1. Append a whitespace to the end of the String s in order to keep the modification consistent. Be sure to delete it before return the result
     * 2. Whenever encounter a whitespace, do the modification on the word based on the rules
     */
    public String toGoatLatin(String S) {
        S += " ";
        Set<Character> vowels = new HashSet<>(Arrays.asList(new Character[] { 'a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U' }));
        StringBuilder sb = new StringBuilder();
        String append = "";
        int idx = 0;
        for (int i = 0; i < S.length(); ++i) {
            if (S.charAt(i) == ' ') {
                append += "a";
                sb.append(modify(vowels, S, idx, i, append)).append(" ");
                idx = i + 1;
            }
        }
        return sb.substring(0, sb.length() - 1);
    }

    private String modify(Set<Character> vowels, String S, int idx, int end, String append) {
        if (vowels.contains(S.charAt(idx))) {
            return S.substring(idx, end) + "ma" + append;
        } else {
            return S.substring(idx + 1, end) + S.charAt(idx) + "ma" + append;
        }
    }
}
