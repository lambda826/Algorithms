/**
 *  @author Yunxiang He
 *  @date Jan 29, 2018 9:55:28 PM
 */

package questions.temp;

/*

Given a 2D integer matrix M representing the gray scale of an image, 
you need to design a smoother to make the gray scale of each cell becomes the average gray scale (rounding down) of all the 8 surrounding cells and itself. 
If a cell has less than 8 surrounding cells, then use as many as you can.

Example 1:
Input:
[[1,1,1],
 [1,0,1],
 [1,1,1]]
Output:
[[0, 0, 0],
 [0, 0, 0],
 [0, 0, 0]]
Explanation:
For the point (0,0), (0,2), (2,0), (2,2): floor(3/4) = floor(0.75) = 0
For the point (0,1), (1,0), (1,2), (2,1): floor(5/6) = floor(0.83333333) = 0
For the point (1,1): floor(8/9) = floor(0.88888889) = 0

Note:
The value in the given matrix is in the range of [0, 255].
The length and width of the given matrix are in the range of [1, 150].

 */

public class __0661_Image_Smoother {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // in place
    // 算出来的结果左移8位
    public int[][] imageSmoother(int[][] M) {
        int row = M.length;
        int col = M[0].length;

        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                int sum = 0;
                int count = 0;
                for (int x = -1; x < 2; x++) {
                    for (int y = -1; y < 2; y++) {
                        int nx = r + x;
                        int ny = c + y;
                        if (!(nx < 0 || nx >= row || ny < 0 || ny >= col)) {
                            count++;
                            sum += M[nx][ny] & 255;
                        }
                    }
                }
                M[r][c] |= (sum / count) << 8;
            }
        }
        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                M[r][c] >>= 8;
            }
        }
        return M;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int[][] imageSmoother2(int[][] M) {
        int row = M.length;
        int col = M[0].length;
        int[][] res = new int[row][col];
        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                int sum = 0;
                int count = 0;
                for (int x = -1; x < 2; x++) {
                    for (int y = -1; y < 2; y++) {
                        int nx = r + x;
                        int ny = c + y;
                        if (!(nx < 0 || nx >= row || ny < 0 || ny >= col)) {
                            count++;
                            sum += M[nx][ny];
                        }
                    }
                }
                res[r][c] = sum / count;
            }
        }
        return res;
    }
}
