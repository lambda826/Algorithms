/**
 *  @author: Yunxiang He
 *    @date: 07/25/2018
 */

package questions.temp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*

Given a set of words (without duplicates), find all word squares you can build from them.
A sequence of words forms a valid word square if the kth row and column read the exact same string, where 0 ≤ k < max(numRows, numColumns).
For example, the word sequence ["ball","area","lead","lady"] forms a word square because each word reads the same both horizontally and vertically.
    b a l l
    a r e a
    l e a d
    l a d y


Example 1:
    Input:
        ["area","lead","wall","lady","ball"]
    Output:
        [
          [ "wall",
            "area",
            "lead",
            "lady"
          ],
          [ "ball",
            "area",
            "lead",
            "lady"
          ]
        ]
    Explanation:
        The output consists of two word squares. The order of output does not matter (just the order of words in each word square matters).

Example 2:
    Input:
        ["abat","baba","atan","atal"]
    Output:
        [
          [ "baba",
            "abat",
            "baba",
            "atan"
          ],
          [ "baba",
            "abat",
            "baba",
            "atal"
          ]
        ]
    Explanation:
        The output consists of two word squares. The order of output does not matter (just the order of words in each word square matters).


Note:
    There are at least 1 and at most 1000 words.
    All words will have the exact same length.
    Word length is at least 1 and at most 5.
    Each word contains only lowercase English alphabet a-z.

*/

public class _0425_Word_Squares {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private final List<List<String>> res = new ArrayList<>();
    private final Node root = new Node();
    private int len = 0;

    private class Node {
        Node[] next = new Node[26];
        List<String> words = new ArrayList<>();
    }

    private void build(String[] words) {
        for (String word : words) {
            Node temp = root;
            for (char ch : word.toCharArray()) {
                if (temp.next[ch - 'a'] == null) {
                    temp.next[ch - 'a'] = new Node();
                }
                temp.words.add(word);
                temp = temp.next[ch - 'a'];
            }
        }
    }

    public List<List<String>> wordSquares_Trie(String[] words) {
        len = words[0].length();
        build(words);
        for (String word : words) {
            String[] res = new String[len];
            res[0] = word;
            dfs(res, 1);
        }
        return res;
    }

    private void dfs(String[] curr, int row) {
        if (curr[len - 1] != null) {
            res.add(new ArrayList<>(Arrays.asList(curr)));
        } else {
            Node temp = root;
            for (int i = 0; i < row; i++) {
                if (curr[i] == null) {
                    return;
                }
                char ch = curr[i].charAt(row);
                if (temp.next[ch - 'a'] == null) {
                    return;
                }
                temp = temp.next[ch - 'a'];
            }
            for (String word : temp.words) {
                curr[row] = word;
                dfs(curr, row + 1);
                curr[row] = null;
            }
        }
    }

    public static void main(String[] args) {
        //        String[] words = { "area", "lead", "wall", "lady", "ball" };
        String[] words = { "ab", "ba", "ac" };
        _0425_Word_Squares test = new _0425_Word_Squares();
        System.out.println(test.wordSquares_Trie(words));

    }

}
