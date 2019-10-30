/**
 *  @author Yunxiang He
 *  @date Jan 29, 2018 9:55:28 PM
 */

package coding.temp;

/*

A group of two or more people wants to meet and minimize the total travel distance. You are given a 2D grid of values 0 or 1, where each 1 marks the home of someone in the group. 
The distance is calculated using Manhattan Distance, where distance(p1, p2) = |p2.x - p1.x| + |p2.y - p1.y|.

For example, given three people living at (0,0), (0,4), and (2,2):

1 - 0 - 0 - 0 - 1
|   |   |   |   |
0 - 0 - 0 - 0 - 0
|   |   |   |   |
0 - 0 - 1 - 0 - 0
The point (0,2) is an ideal meeting point, as the total travel distance of 2+2+2=6 is minimal. So return 6.

 */

public class _0296_Best_Meeting_Point {

    public int minTotalDistance_Array(int[][] grid) {
        int row = grid.length;
        int col = grid[0].length;
        int[] rows = new int[row];
        int[] cols = new int[col];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 1) {
                    rows[i] += 1;
                    cols[j] += 1;
                }
            }
        }

        return getMinDistance(rows) + getMinDistance(cols);
    }

    //     The median is the point that makes the sum smallest
    private int getMinDistance(int[] line) {
        int i = 0;
        int j = line.length - 1;
        int d = 0, left = line[0];
        int right = line[j];
        while (i != j) {
            if (left < right) {
                d += left;
                left += line[++i];
            } else {
                d += right;
                right += line[--j];
            }
        }
        return d;
    }

    public static void main(String[] args) {
        _0296_Best_Meeting_Point test = new _0296_Best_Meeting_Point();
        int[][] grid = { { 1, 0, 0, 0, 1 }, { 0, 0, 0, 0, 0 }, { 0, 0, 1, 0, 0 } };
        System.out.println(test.minTotalDistance_Array(grid));
    }

}
