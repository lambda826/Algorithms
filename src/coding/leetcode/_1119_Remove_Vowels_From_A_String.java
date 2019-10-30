package coding.leetcode;

/*

Given a string S, remove the vowels 'a', 'e', 'i', 'o', and 'u' from it, and return the new string.


Example 1:
    Input: "leetcodeisacommunityforcoders"
    Output: "ltcdscmmntyfrcdrs"
Example 2:
    Input: "aeiou"
    Output: ""


Note:
    S consists of lowercase English letters only.
    1 <= S.length <= 1000

*/

public class _1119_Remove_Vowels_From_A_String {


    /**
     * 1. Mark the vowels
     * 2. Go through the string, append the non-vowels
     */
    public String removeVowels(String S) {
        int[] vowelArr = new int[26];
        vowelArr['a' - 'a'] = 1;
        vowelArr['e' - 'a'] = 1;
        vowelArr['i' - 'a'] = 1;
        vowelArr['o' - 'a'] = 1;
        vowelArr['u' - 'a'] = 1;
        StringBuilder sb = new StringBuilder();
        for (char ch : S.toCharArray()) {
            if (vowelArr[ch - 'a'] == 0) {
                sb.append(ch);
            }
        }
        return sb.toString();
    }
}
