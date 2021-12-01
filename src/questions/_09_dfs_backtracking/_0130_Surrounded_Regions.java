package questions._09_dfs_backtracking;

import java.util.LinkedList;

/*

Given an m x n matrix board containing 'X' and 'O', capture all regions that are 4-directionally surrounded by 'X'.
A region is captured by flipping all 'O's into 'X's in that surrounded region.


Example 1:
    Input:
        board =
            [["X","X","X","X"],
             ["X","O","O","X"],
             ["X","X","O","X"],
             ["X","O","X","X"]]
    Output:
            [["X","X","X","X"],
             ["X","X","X","X"],
             ["X","X","X","X"],
             ["X","O","X","X"]]
    Explanation:
        Surrounded regions should not be on the border, which means that any 'O' on the border of the board are not flipped to 'X'.
        Any 'O' that is not on the border, and it is not connected to an 'O' on the border will be flipped to 'X'.
        Two cells are connected if they are adjacent cells connected horizontally or vertically.

Example 2:
    Input:
        board = [["X"]]
    Output:
        [["X"]]


Constraints:
    m == board.length
    n == board[i].length
    1 <= m, n <= 200
    board[i][j] is 'X' or 'O'.

*/
public class _0130_Surrounded_Regions {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    class Solution_DFS {

        private int[] dir = { -1, 0, 1, 0, -1 };
        private LinkedList<Integer> temp = new LinkedList<>();

        public void solve(char[][] board) {
            boolean[][] visited = new boolean[board.length][board[0].length];
            for (int x = 0; x < board.length; ++x) {
                for (int y = 0; y < board[0].length; ++y) {
                    boolean free = free(board, visited, x, y);
                    if (free) {
                        temp.clear();
                    } else {
                        while (temp.size() != 0) {
                            int pos = temp.remove();
                            board[pos / board[0].length][pos % board[0].length] = 'X';
                        }
                    }
                }
            }
        }

        private boolean free(char[][] board, boolean[][] visited, int x, int y) {
            if (x >= 0 && x < board.length && y >= 0 && y < board[0].length) {
                if (visited[x][y] || board[x][y] == 'X') {
                    return false;
                } else {
                    temp.add(x * board[0].length + y);
                    visited[x][y] = true;
                    boolean free = false;
                    for (int i = 0; i < 4; ++i) {
                        int xx = x + dir[i];
                        int yy = y + dir[i + 1];
                        free = free(board, visited, xx, yy) || free;
                    }
                    return free;
                }
            } else {
                return true;
            }
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Optimization:
    //      We only need to start the search from the boarder, all the connected positions are escaped.
    class Solution_DFS_Optimized {

        private int[] dir = { -1, 0, 1, 0, -1 };

        public void solve(char[][] board) {

            // Left and right boarder
            for (int x = 0; x < board.length; ++x) {
                int y1 = 0; // Left
                int y2 = board[0].length - 1; // Right
                dfs(board, x, y1);
                dfs(board, x, y2);
            }

            // Top and bottom boarder
            for (int y = 0; y < board[0].length; ++y) {
                int x1 = 0; // Top
                int x2 = board.length - 1; // Bottom
                dfs(board, x1, y);
                dfs(board, x2, y);
            }

            for (int x = 0; x < board.length; ++x) {
                for (int y = 0; y < board[0].length; ++y) {
                    if (board[x][y] == 'O') {
                        board[x][y] = 'X';
                    } else if (board[x][y] == '#') {
                        board[x][y] = 'O';
                    }
                }
            }
        }

        private void dfs(char[][] board, int x, int y) {
            if (x >= 0 && x < board.length && y >= 0 && y < board[0].length && board[x][y] == 'O') {
                board[x][y] = '#'; // Mark escaped grid
                for (int i = 0; i < 4; ++i) {
                    int xx = x + dir[i];
                    int yy = y + dir[i + 1];
                    dfs(board, xx, yy);
                }
            }
        }
    }

}