/**
 *  @author Yunxiang He
 *  @date 07/26/2018
 */

package coding.leetcode.temp;

import java.util.ArrayList;
import java.util.List;

/*

Given a list of words (without duplicates), please write a program that returns all concatenated words in the given list of words.
A concatenated word is defined as a string that is comprised entirely of at least two shorter words in the given array.

Example:
    Input: 
        ["cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat"]
    Output: 
        ["catsdogcats","dogcatsdog","ratcatdogcat"]
    Explanation: 
        "catsdogcats" can be concatenated by "cats", "dog" and "cats"; 
        "dogcatsdog" can be concatenated by "dog", "cats" and "dog"; 
        "ratcatdogcat" can be concatenated by "rat", "cat", "dog" and "cat".


Note:
    The number of elements of the given array will not exceed 10,000
    The length sum of elements in the given array will not exceed 600,000.
    All the input string will only include lower case letters.
    The returned elements order does not matter.

*/

public class _0472_Concatenated_Words {

    public static void main(String[] args) {
        String[] words = { "catsdogcats", "cat", "cats", "dog", "dogcatsdog", "hippopotamuses", "rat", "ratcatdogcat" };
        _0472_Concatenated_Words test = new _0472_Concatenated_Words();
        System.out.println(test.findAllConcatenatedWordsInADict(words));
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // trie
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        buildTrie(words);
        List<String> res = new ArrayList<>();
        for (String word : words) {
            if (DFS(word.toCharArray(), 0, 0)) {
                res.add(word);
            }
        }
        return res;
    }

    private boolean DFS(char[] chs, int from, int count) {
        if (from == chs.length && count > 1) {
            return true;
        }
        Node temp = root;
        int i = from;
        while (i < chs.length) {
            temp = temp.next[chs[i] - 'a'];
            if (temp == null) {
                return false;
            } else if (temp.hasWord && DFS(chs, i + 1, count + 1)) {
                return true;
            }
            i++;
        }
        return false;
    }

    private Node root = new Node();

    private class Node {
        Node[] next = new Node[26];
        boolean hasWord;
    }

    private void buildTrie(String[] words) {
        for (String word : words) {
            Node temp = root;
            for (char ch : word.toCharArray()) {
                if (temp.next[ch - 'a'] == null) {
                    temp.next[ch - 'a'] = new Node();
                }
                temp = temp.next[ch - 'a'];
            }
            temp.hasWord = true;
        }
    }

}
