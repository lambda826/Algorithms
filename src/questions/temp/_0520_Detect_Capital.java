/**
 * @author: Yunxiang He
 * @date : 2018-07-08 14:19
 */

package questions.temp;

/*

Given a word, you need to judge whether the usage of capitals in it is right or not.

We define the usage of capitals in a word to be right when one of the following cases holds:
    All letters in this word are capitals, like "USA".
    All letters in this word are not capitals, like "leetcode".
    Only the first letter in this word is capital if it has more than one letter, like "Google".

Otherwise, we define that this word doesn't use capitals in a right way.


Example 1:
    Input: "USA"
    Output: True

Example 2:
    Input: "FlaG"
    Output: False


Note:
    The input will be a non-empty word consisting of uppercase and lowercase latin letters.

*/

public class _0520_Detect_Capital {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public boolean detectCapitalUse(String word) {
        boolean firstCap = Character.isUpperCase(word.charAt(0));
        if (word.length() > 1) {
            boolean secCap = firstCap && Character.isUpperCase(word.charAt(1));
            for (int i = 1; i < word.length(); i++) {
                if (secCap != Character.isUpperCase(word.charAt(i))) {
                    return false;
                }
            }
        }
        return true;
    }

}
