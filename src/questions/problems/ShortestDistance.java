/**
 * @author Yunxiang He
 * @date 2018-11-12 14:04
 */

package questions.problems;

import java.util.LinkedList;
import java.util.Queue;

public class ShortestDistance {
    private static int row;
    private static int col;
    private static final int[][] dirs = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

    public static void main(String[] args) {
        System.out.println("Hello World!");
    }

    private static void shortestDis(char[][] board) {
        if (board.length > 0 && board[0].length > 0) {
            row = board.length;
            col = board[0].length;
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    if (board[i][j] == 'G') {
                        BFS(board, i, j);
                    }
                }
            }
        }
    }

    private static void BFS(char[][] board, int i, int j) {
        Queue<int[]> que = new LinkedList<>();
        que.add(new int[] { i, j });
        int len = 0;
        while (!que.isEmpty()) {
            int size = que.size();
            for (int sz = 0; sz < size; sz++) {
                int[] curr = que.remove();
                for (int[] dir : dirs) {
                    int nextRow = curr[0] + dir[0];
                    int nextCol = curr[1] + dir[1];
                    if (nextRow >= 0 && nextRow < row && nextCol >= 0 && nextCol < col && board[nextRow][nextCol] != 'X') {
                        if (board[nextRow][nextCol] == 'i' || len <= Character.valueOf(board[nextRow][nextCol])) {
                            board[nextRow][nextCol] = (char) len;
                            que.add(new int[] { nextRow, nextCol });
                        }
                    }
                }
            }
            len++;
        }
        // board: "i" : item, "g" : guard
    }
}
