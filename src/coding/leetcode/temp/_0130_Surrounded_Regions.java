/**
 *  @author Yunxiang He
 *  @date 08/05/2018
 */

package coding.leetcode.temp;

import java.util.LinkedList;
import java.util.Queue;

/*

Given a 2D board containing ''X', ' and ''O', ' (the letter 'O', ), capture all regions surrounded by ''X', '.
A region is captured by flipping all ''O', 's into ''X', 's in that surrounded region.


Example:
        'X',  'X',  'X',  'X', 
        'X',  'O',  'O',  'X', 
        'X',  'X',  'O',  'X', 
        'X',  'O',  'X',  'X', 
    After running your function, the board should be:
        'X',  'X',  'X',  'X', 
        'X',  'X',  'X',  'X', 
        'X',  'X',  'X',  'X', 
        'X',  'O',  'X',  'X', 
    Explanation:
        Surrounded regions shouldn’t be on the border, which means that any ''O', ' on the border of the board are not flipped to ''X', '. 
        Any ''O', ' that is not on the border and it is not connected to an ''O', ' on the border will be flipped to ''X', '. 
        Two cells are connected if they are adjacent cells connected horizontally or vertically.

*/

public class _0130_Surrounded_Regions {

    public static void main(String[] args) {
        new _0130_Surrounded_Regions().solve_DFS(new char[][] { { 'X', 'X', 'X', 'X' }, { 'X', 'O', 'O', 'X' }, { 'X', 'X', 'O', 'X' }, { 'X', 'O', 'X', 'X' } });
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private int[][] dirs = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

    public void solve_DFS(char[][] board) {
        if (board.length == 0 || board[0].length == 0) {
            return;
        }
        int row = board.length;
        int col = board[0].length;
        // up & down
        for (int c = 0; c < col; c++) {
            int r1 = 0;
            int r2 = row - 1;
            DFS(board, r1, c);
            DFS(board, r2, c);
        }
        // left & right
        for (int r = 0; r < row; r++) {
            int c1 = 0;
            int c2 = col - 1;
            DFS(board, r, c1);
            DFS(board, r, c2);
        }
        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                if (board[r][c] == 'Y') {
                    board[r][c] = 'O';
                } else {
                    board[r][c] = 'X';
                }
            }
        }
    }

    private void DFS(char[][] board, int row, int col) {
        if (row >= 0 && col >= 0 && row < board.length && col < board[0].length && board[row][col] == 'O') {
            board[row][col] = 'Y';
            for (int[] dir : dirs) {
                DFS(board, row + dir[0], col + dir[1]);
            }
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void solve_BFS(char[][] board) {
        if (board.length == 0 || board[0].length == 0) {
            return;
        }
        int row = board.length;
        int col = board[0].length;
        Queue<int[]> q = new LinkedList<>();
        for (int c = 0; c < col; c++) {
            int r1 = 0;
            int r2 = row - 1;
            if (board[r1][c] == 'O') {
                q.offer(new int[] { r1, c });
            }
            if (board[r2][c] == 'O') {
                q.offer(new int[] { r2, c });
            }
        }
        for (int r = 0; r < row; r++) {
            int c1 = 0;
            int c2 = col - 1;
            if (board[r][c1] == 'O') {
                q.offer(new int[] { r, c1 });
            }
            if (board[r][c2] == 'O') {
                q.offer(new int[] { r, c2 });
            }
        }
        while (!q.isEmpty()) {
            int[] next = q.poll();
            board[next[0]][next[1]] = 'Y';
            for (int[] dir : dirs) {
                int r = next[0] + dir[0];
                int c = next[1] + dir[1];
                if (r >= 0 && c >= 0 && r < row && c < col && board[r][c] == 'O') {
                    q.offer(new int[] { r, c });
                }
            }
        }

        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                if (board[r][c] == 'Y') {
                    board[r][c] = 'O';
                } else {
                    board[r][c] = 'X';
                }
            }
        }
    }

}
