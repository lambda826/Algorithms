package questions.problems;

/**
 * @author: Yunxiang He
 * @date : 2018-11-12
 */

import java.util.LinkedList;
import java.util.Queue;

/**
 * While I was wasing my dishes just now, I found out the right way to solve the problem
 * Before we do BFS, we initialize the queue.
 * we push all the guards into the queue first and then do the BFS, which ensures that every node get the shortest distances
 * The time complexity is O(number of grids of the board)
 */
public class ShortestDis_YunxiangHe {

    private static final int OBSTACLE_VISITED = -3;
    private static final int GUARD = -2;
    private static final int ITEM = -1;
    private static final int PASS = 0;
    private final int[][] dirs = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
    private int[][] board;
    private int row;
    private int col;

    public void shortest(int[][] board) {
        if (board.length > 0 && board[0].length > 0) {
            this.board = board;
            printMap(board);
            row = board.length;
            col = board[0].length;
            Queue<int[]> que = new LinkedList<>();
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    if (board[i][j] == GUARD) {
                        que.add(new int[] { i, j });
                    }
                }
            }
            BFS(que);
            printMap(board);
        }
    }

    private void BFS(Queue<int[]> que) {
        int len = 1;
        while (!que.isEmpty()) {
            int size = que.size();
            for (int sz = 0; sz < size; sz++) {
                int[] curr = que.remove();
                int nextRow;
                int nextCol;
                for (int[] dir : dirs) {
                    nextRow = curr[0] + dir[0];
                    nextCol = curr[1] + dir[1];
                    if (nextRow >= 0 && nextRow < row && nextCol >= 0 && nextCol < col && board[nextRow][nextCol] != OBSTACLE_VISITED) {
                        if (board[nextRow][nextCol] == PASS) {
                            board[nextRow][nextCol] = OBSTACLE_VISITED;
                            que.add(new int[] { nextRow, nextCol });
                        } else if (board[nextRow][nextCol] == ITEM) {
                            board[nextRow][nextCol] = len;
                            que.add(new int[] { nextRow, nextCol });
                        }
                    }
                }
            }
            len++;
        }
    }

    private void printMap(int[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == -1) {
                    System.out.print("1 ");
                } else if (board[i][j] == -3) {
                    System.out.print("X ");
                } else if (board[i][j] == -2) {
                    System.out.print("G ");
                } else {
                    System.out.print(board[i][j] + " ");
                }
            }
            System.out.println();
        }
        System.out.println("-------------------");
    }

    public static void main(String[] args) {
        ShortestDis_YunxiangHe test = new ShortestDis_YunxiangHe();
        test.shortest(new int[][] { { -1, -1, 0, 0, 0 }, { 0, -3, 0, 0, 0 }, { -3, 0, -1, -3, -1 }, { -2, 0, 0, -2, 0 } });
        System.out.println();
        test.shortest(new int[][] { { 0, -1, 0, -1, 0 }, { 0, -3, 0, -3, 0 }, { -3, 0, -1, -3, -1 }, { -2, 0, 0, -2, 0 } });
    }

}
