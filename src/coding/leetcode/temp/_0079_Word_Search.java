/**
 *  @author Yunxiang He
 *  @date 05/23/2018
 */

package coding.leetcode.temp;

/*

Given a 2D board and a word, find if the word exists in the grid.

The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. 
The same letter cell may not be used more than once.

Example:
    board =
    [
      ['A','B','C','E'],
      ['S','F','C','S'],
      ['A','D','E','E']
    ]
    Given word = "ABCCED", return true.
    Given word = "SEE", return true.
    Given word = "ABCB', return false.

*/

public class _0079_Word_Search {

    public static void main(String[] args) {
        new _0079_Word_Search().exist(new char[][] { { 'D', 'B', 'C', 'E' }, { 'S', 'F', 'C', 'S' }, { 'A', 'D', 'E', 'E' } }, "ABCCED");
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    int[][] dirs = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

    public boolean exist(char[][] board, String word) {
        if (board.length == 0 || board[0].length == 0) {
            return word == "";
        }
        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board[0].length; ++j) {
                if (DFS(i, j, board, word.toCharArray(), 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean DFS(int i, int j, char[][] board, char[] target, int start) {
        if (start == target.length) {
            return true;
        } else {
            if (i >= 0 && i < board.length && j >= 0 && j < board[0].length && board[i][j] != '#' && target[start] == board[i][j]) {
                char ch = board[i][j];
                board[i][j] = '#';
                for (int[] dir : dirs) {
                    if (DFS(i + dir[0], j + dir[1], board, target, start)) {
                        return true;
                    }
                }
                board[i][j] = ch;
            }
            return false;
        }
    }
}
