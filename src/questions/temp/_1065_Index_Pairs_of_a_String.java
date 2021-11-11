package questions.temp;

/*

Given a text string and words (a list of strings), return all index pairs [i, j] so that the substring text[i]...text[j] is in the list of words.


Example 1:
    Input: text = "thestoryofleetcodeandme", words = ["story","fleet","leetcode"]
    Output: [[3,7],[9,13],[10,17]]

Example 2:
    Input: text = "ababa", words = ["aba","ab"]
    Output: [[0,1],[0,2],[2,3],[2,4]]
    Explanation:
    Notice that matches can overlap, see "aba" is found in [0,2] and [2,4].


Note:
    All strings contains only lowercase English letters.
    It's guaranteed that all strings in words are different.
    1 <= text.length <= 100
    1 <= words.length <= 20
    1 <= words[i].length <= 50
    Return the pairs [i,j] in sorted order (i.e. sort them by their first coordinate in case of ties sort them by their second coordinate).

*/

import java.util.ArrayList;
import java.util.List;

public class _1065_Index_Pairs_of_a_String {

    /**
     * 1. Build the trie
     * 2. Search the substring in the text that occurs in the trie
     * 3. Record
     */
    public int[][] indexPairs(String text, String[] words) {
        for (String word : words) {
            put(word);
        }
        List<int[]> res = new ArrayList<>();
        for (int i = 0; i < text.length(); ++i) {
            for (int j : search(text, i)) {
                res.add(new int[] { i, j });
            }
        }

        return res.toArray(new int[res.size()][]);
    }

    private class Node {
        private Node[] next = new Node[26];
        private boolean hasWord;
    }

    private Node root = new Node();

    private void put(String key) {
        Node temp = root;
        for (char ch : key.toCharArray()) {
            if (temp.next[ch - 'a'] == null) {
                temp.next[ch - 'a'] = new Node();
            }
            temp = temp.next[ch - 'a'];
        }
        temp.hasWord = true;
    }

    private List<Integer> search(String text, int start) {
        List<Integer> list = new ArrayList<>();
        Node temp = root;
        for (int i = start; i < text.length(); ++i) {
            if (temp.next[text.codePointAt(i) - 97] != null) {
                temp = temp.next[text.codePointAt(i) - 97];
                if (temp.hasWord) {
                    list.add(i);
                }
            } else {
                break;
            }
        }
        return list;
    }

}
