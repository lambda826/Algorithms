/**
 * @author Yunxiang He
 * @date 2018-11-02 16:47
 */

package questions.problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class BleekInk {
    private static final int[] dirX = { 0, 1, 0, -1 };
    private static final int[] dirY = { -1, 0, 1, 0 };

    public static void main(String[] args) {
        List<Integer> res = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        Ink[] inks = null;
        int row = 0;
        int col = 0;
        String line;
        while ((line = sc.nextLine()) != null) {
            int m = Integer.parseInt(line);
            for (int i = 0; i < m; i++) {
                if (i == 1) {
                    String[] row_col = sc.nextLine().split(" ");
                    row = Integer.parseInt(row_col[0]);
                    col = Integer.parseInt(row_col[1]);
                } else if (i == 2) {
                    int n = Integer.parseInt(line);
                    inks = new Ink[n];
                    for (int k = 0; k < n; k++) {
                        String[] _inks = line.split(" ");
                        inks[k] = new Ink(Integer.parseInt(_inks[0]), Integer.parseInt(_inks[1]), Integer.parseInt(_inks[2]));
                    }
                }
            }
            res.add(sumGrid(row, col, inks));
        }
    }

    public static int sumGrid(int row, int col, Ink[] inks) {
        int[][] grid = new int[row][col];
        inks = new Ink[] { new Ink(0, 0, 255), new Ink(1, 2, 255), };
        PriorityQueue<Ink> pq = new PriorityQueue<>(Arrays.asList(inks));
        while (!pq.isEmpty()) {
            Ink ink = pq.remove();
            int _row = ink.row;
            int _col = ink.col;
            if (ink.darkness > grid[_row][_col]) {
                grid[_row][_col] = ink.darkness;
                for (int i = 0; i < 4; i++) {
                    if (_row + dirY[i] >= 0 && _row + dirY[i] < row && _col + dirX[i] >= 0 && _col + dirX[i] < col) {
                        pq.add(new Ink(_row + dirY[i], _col + dirX[i], ink.darkness - 1));
                    }
                }
            }
        }
        int sum = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                sum += grid[i][j];
            }
        }
        return sum;
    }
}

class Ink implements Comparable<Ink> {
    int row;
    int col;
    int darkness;

    public Ink(int row, int col, int darkness) {
        super();
        this.row = row;
        this.col = col;
        this.darkness = darkness;
    }

    @Override
    public int compareTo(Ink i) {
        return i.darkness - this.darkness;
    }
}
