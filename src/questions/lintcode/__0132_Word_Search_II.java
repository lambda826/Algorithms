/**
 *  @author Yunxiang He
 *  @date 03/09/2019
 */

package questions.lintcode;

import java.util.ArrayList;
import java.util.List;

/*

Given a matrix of lower alphabets and a dictionary. Find all words in the dictionary that can be found in the matrix. 
A word can start from any position in the matrix and go left/right/up/down to the adjacent position. 
One character only be used once in one word. 
No same word in dictionary


Example
    Given matrix:
        doaf
        agai
        dcan
    and dictionary:
        {"dog", "dad", "dgdg", "can", "again"}
    return 
        {"dog", "dad", "can", "again"}
        dog:
            doaf
            agai
            dcan
        dad:
            doaf
            agai
            dcan
        can:
            doaf
            agai
            dcan
        again:
            doaf
            agai
            dcan


Challenge
    Using trie to implement your algorithm.

*/

public class __0132_Word_Search_II {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public List<String> wordSearchII(char[][] board, List<String> words) {
        for (String word : words) {
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
        if (i >= 0 && i < board.length && j >= 0 && j < board[0].length && board[i][j] != '#') {
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
}
