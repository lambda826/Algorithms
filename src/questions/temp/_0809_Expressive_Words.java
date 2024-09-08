/**
 * @author Yunxiang He
 * @date 11/22/2018
 */

package questions.temp;

/*

Sometimes people repeat letters to represent extra feeling, such as "hello" -> "heeellooo", "hi" -> "hiiii".  
Here, we have groups, of adjacent letters that are all the same character, and adjacent characters to the group are different.  
A group is extended if that group is length 3 or more, so "e" and "o" would be extended in the first example, and "i" would be extended in the second example.  
As another example, the groups of "abbcccaaaa" would be "a", "bb", "ccc", and "aaaa"; and "ccc" and "aaaa" are the extended groups of that string.

For some given string S, a query word is stretchy if it can be made to be equal to S by extending some groups.  
Formally, we are allowed to repeatedly choose a group (as defined above) of characters c, and add some number of the same character c to it so that the length of the group is 3 or more.  
Note that we cannot extend a group of size one like "h" to a group of size two like "hh" - all extensions must leave the group extended - ie., at least 3 characters long.

Given a list of query words, return the number of words that are stretchy. 


Example:
    Input: 
        S = "heeellooo"
        words = ["hello", "hi", "helo"]
    Output: 
        1
    Explanation: 
        We can extend "e" and "o" in the word "hello" to get "heeellooo".
        We can't extend "helo" to get "heeellooo" because the group "ll" is not extended.


Notes:
    0 <= len(S) <= 100.
    0 <= len(words) <= 100.
    0 <= len(words[i]) <= 100.
    S and all words in words consist only of lowercase letters

*/

public class _0809_Expressive_Words {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int expressiveWords(String S, String[] words) {
        int count = 0;
        for (String word : words) {
            if (extensive(S, word)) {
                count++;
            }
        }
        return count;
    }

    private boolean extensive(String S, String W) {
        int slen = S.length();
        int i = 0;
        int wlen = W.length();
        int j = 0;
        // If currChar of S equals currChar of W, i++, j++
        // If not, check whether currChar of S is extensible
        //     First assume it is the third char
        //     Then assume it is the second char
        while (i < slen) {
            if (j < wlen && S.charAt(i) == W.charAt(j)) {
                i++;
                j++;
            } else if (i > 1 && S.charAt(i) == S.charAt(i - 1) && S.charAt(i) == S.charAt(i - 2)) {
                i++;
            } else if (i > 0 && i + 1 < slen && S.charAt(i) == S.charAt(i - 1) && S.charAt(i) == S.charAt(i + 1)) {
                i++;
            } else {
                return false;
            }
        }
        return j == wlen;
    }

    public static void main(String[] args) {
        _0809_Expressive_Words _0809_Expressive_Words = new _0809_Expressive_Words();
        System.out.println(_0809_Expressive_Words.expressiveWords("heeellooo", new String[] { "hello" }));
    }
}
