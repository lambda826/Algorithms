package questions.temp;

/*

An image is represented by a 2-D array of integers, each integer representing the pixel value of the image (from 0 to 65535).

Given a coordinate (sr, sc) representing the starting pixel (row and column) of the flood fill, and a pixel value newColor, "flood fill" the image.

To perform a "flood fill",
    consider the starting pixel,
    plus any pixels connected 4-directionally to the starting pixel of the same color as the starting pixel,
    plus any pixels connected 4-directionally to those pixels (also with the same color as the starting pixel), and so on.
Replace the color of all of the aforementioned pixels with the newColor.

At the end, return the modified image.


Example 1:
    Input:
        image = [[1,1,1],[1,1,0],[1,0,1]]
        sr = 1, sc = 1, newColor = 2
    Output:
        [[2,2,2],[2,2,0],[2,0,1]]
    Explanation:
        From the center of the image (with position (sr, sc) = (1, 1)), all pixels connected by a path of the same color as the starting pixel are colored with the new color.
        Note the bottom corner is not colored 2, because it is not 4-directionally connected to the starting pixel.


Note:
    The length of image and image[0] will be in the range [1, 50].
    The given starting pixel will satisfy 0 <= sr < image.length and 0 <= sc < image[0].length.
    The value of each color in image[i][j] and newColor will be an integer in [0, 65535].


History:
    3/28/2020

*/

import java.util.ArrayDeque;
import java.util.Queue;

public class _0733_Flood_Fill {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int[][] floodFill_DFS(int[][] image, int sr, int sc, int newColor) {
        int oldColor = image[sr][sc];
        if (oldColor != newColor) {
            int[][] dd = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
            DFS(image, sr, sc, oldColor, newColor, dd);
        }
        return image;
    }

    private void DFS(int[][] image, int r, int c, int oldColor, int newColor, int[][] dd) {
        if (r >= 0 && r < image.length && c >= 0 && c < image[0].length && image[r][c] == oldColor) {
            image[r][c] = newColor;
            for (int[] d : dd) {
                DFS(image, r + d[0], c + d[1], oldColor, newColor, dd);
            }
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int[][] floodFill_BFS(int[][] image, int r, int c, int newColor) {
        int oldColor = image[r][c];
        if (oldColor != newColor) {
            int[][] dd = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
            Queue<int[]> queue = new ArrayDeque<>();
            queue.offer(new int[] { r, c });
            while (!queue.isEmpty()) {
                int[] d = queue.poll();
                image[d[0]][d[1]] = newColor;
                for (int[] next : dd) {
                    r = d[0] + next[0];
                    c = d[1] + next[1];
                    if (r >= 0 && r < image.length && c >= 0 && c < image[0].length && image[r][c] == oldColor) {
                        queue.offer(new int[] { r, c });
                    }
                }
            }
        }
        return image;
    }

}
