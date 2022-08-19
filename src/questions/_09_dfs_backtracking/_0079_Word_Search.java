package questions._09_DFS_backtracking;

/*

Given an m x n grid of characters board and a string word, return true if word exists in the grid.

The word can be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally or vertically neighboring.
The same letter cell may not be used more than once.


Example 1:
    Input:
        board = [["A","B","C","E"],
                 ["S","F","C","S"],
                 ["A","D","E","E"]],
        word = "ABCCED"
    Output:
        true

Example 2:
    Input:
        board = [["A","B","C","E"],
                 ["S","F","C","S"],
                 ["A","D","E","E"]],
        word = "SEE"
    Output:
        true

Example 3:
    Input:
        board = [["A","B","C","E"],
                 ["S","F","C","S"],
                 ["A","D","E","E"]],
        word = "ABCB"
    Output:
        false


Constraints:
    m == board.length
    n = board[i].length
    1 <= m, n <= 6
    1 <= word.length <= 15
    board and word consists of only lowercase and uppercase English letters.

*/

public class _0079_Word_Search {
    private static final int[] dir = { -1, 0, 1, 0, -1 };

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    class Solution {

        public boolean exist(char[][] board, String word) {
            for (int i = 0; i < board.length; ++i) {
                for (int j = 0; j < board[0].length; ++j) {
                    if (dfs(board, word, 0, i, j)) {
                        return true;
                    }
                }
            }
            return false;
        }

        private boolean dfs(char[][] board, String word, int index, int i, int j) {
            if (index == word.length()) {
                return true;
            } else {
                if (i >= 0 && i < board.length && j >= 0 && j < board[0].length
                    && board[i][j] == word.charAt(index)) {
                    char ch = board[i][j];
                    board[i][j] = '#';
                    for (int k = 0; k < 4; ++k) {
                        if (dfs(board, word, index + 1, i + dir[k], j + dir[k + 1])) {
                            return true;
                        }
                    }
                    board[i][j] = ch;
                }
                return false;
            }
        }
    }
}