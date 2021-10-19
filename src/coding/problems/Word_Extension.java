/**
 *  @author Yunxiang He
 *  @date 11/22/2018
 */

package coding.problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*

When people send messages on their phones they sometimes modify word spelling by adding extra letters for emphasis. 
For example if you want to emphasize 'hello' you might write it 'hellloooooooo'. Let's call the 'l's and the 'o's 'word extensions'. 
Assumption: Regular text contains 2 or fewer of the same character in a row, while word extensions have 3 or more of the same character in a row.
Question: Given an input string representing one word, write a method that returns the start and end indices of all extensions in the word.


Example: 
    hellloooooooo Return [[2,4],[5,12]]


Follow Up:
    Now we want to spell-check extended words. 
    You have a boolean function that takes in a string, boolean isDictionaryWord(String s). 
    Implement a function bool isExtendedDictionaryWord(String s) that will return: 
    -- True if isDictionaryWord(s) is true 
    -- True if you collapse the extensions in the word and it is a dictionary word 
    -- False otherwise

For example let's assume isDictionaryWord("hello") is true, and isDictionaryWord("xyz") is false. Then we want: 
    isExtendedDictionaryWord("hello") == true 
    isExtendedDictionaryWord("heeello") == true 
    isExtendedDictionaryWord("xyz") == false

*/

public class Word_Extension {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    Set<String> dictionary = new HashSet<>();

    public List<int[]> extensions(String word) {
        List<int[]> res = new ArrayList<>();
        int i = 0;
        int j = 0;
        while (i < word.length()) {
            j = i + 1;
            while (j < word.length() && word.charAt(i) == word.charAt(j)) {
                j++;
            }
            if (j - i > 2) {
                res.add(new int[] { i, j - 1 });
            }
            i = j;
        }
        return res;
    }

    public boolean extensive(String word) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        int j = 0;
        while (i < word.length()) {
            sb.append(word.charAt(i));
            j = i + 1;
            while (j < word.length() && word.charAt(i) == word.charAt(j)) {
                j++;
            }
            if (j - i == 2) {
                sb.append(word.charAt(i));
            }
            i = j;
        }
        return isDictionaryWord(sb.toString());
    }

    private boolean isDictionaryWord(String s) {
        return dictionary.contains(s);
    }

    public static void main(String[] args) {
        Word_Extension Word_Extension = new Word_Extension();
        for (int[] indices : Word_Extension.extensions("heeello")) {
            System.out.println(Arrays.toString(indices));
        }
        Word_Extension.dictionary.add("hello");
        System.out.println(Word_Extension.extensive("heeello"));
    }
}
