/**
 *  @author Yunxiang He
 *  @date 05/22/2018
 */

package coding.leetcode.temp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*

Given a 2D board and a list of words from the dictionary, find all words in the board.
Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. 
The same letter cell may not be used more than once in a word.


Example:
    Input: 
        words = ["oath","pea","eat","rain"] and board =
        [
          ['o','a','a','n'],
          ['e','t','a','e'],
          ['i','h','k','r'],
          ['i','f','l','v']
        ]
    Output: 
        ["eat","oath"]


Note:
    You may assume that all inputs are consist of lowercase letters a-z.

*/

public class _0212_Word_Search_II {

    public static void main(String[] args) {
        // new _0212_Word_Search_II().findWords(new char[][] { { 'o', 'a', 'a', 'n' }, {
        // 'e', 't', 'a', 'e' }, { 'i', 'h', 'k', 'r' }, { 'i', 'f', 'l', 'v' } }, new
        // String[] { "eat", "oath" });
        System.out.println(new _0212_Word_Search_II().findWords(new char[][] { { 'a' }, { 'b' }, }, new String[] { "ba" }));
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public List<String> findWords(char[][] board, String[] strings) {
        for (String word : strings) {
            put(word);
        }
        List<String> list = new ArrayList<>();
        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board[0].length; ++j) {
                DFS(board, i, j, root, new StringBuilder(), list);
            }
        }
        return list;
    }

    private void DFS(char[][] board, int i, int j, Node temp, StringBuilder tempWord, List<String> list) {
        if (i >= 0 && j >= 0 && i < board.length && j < board[0].length && board[i][j] != '#') {
            char ch = board[i][j];
            board[i][j] = '#';
            tempWord.append(ch);
            int nextIndex = ch - 'a';
            Node node = temp.next[nextIndex];
            if (node != null) {
                if (node.hasWord) {
                    list.add(tempWord.toString());
                    node.hasWord = false;
                }
                DFS(board, i + 1, j, node, tempWord, list);
                DFS(board, i, j + 1, node, tempWord, list);
                DFS(board, i - 1, j, node, tempWord, list);
                DFS(board, i, j - 1, node, tempWord, list);
            }
            board[i][j] = ch;
            tempWord.deleteCharAt(tempWord.length() - 1);
        }
    }

    class Node {
        Node[] next = new Node[26];
        boolean hasWord = false;
        ;
    }

    Node root = new Node();

    private void put(String word) {
        Node temp = root;
        for (char ch : word.toCharArray()) {
            int nextIndex = ch - 'a';
            if (temp.next[nextIndex] == null) {
                temp.next[nextIndex] = new Node();
            }
            temp = temp.next[nextIndex];
        }
        temp.hasWord = true;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public List<String> findWords2(char[][] board, String[] words) {
        List<String> list = new ArrayList<>();
        Set<String> set = new HashSet<>();
        set.addAll(Arrays.asList(words));
        _0079_Word_Search instance = new _0079_Word_Search();
        for (String word : words) {
            if (instance.exist(board, word)) {
                list.add(word);
            }
        }
        return list;
    }

}
