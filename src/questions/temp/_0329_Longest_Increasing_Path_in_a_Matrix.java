/**
 *  @author: Yunxiang He
 *  @date  : 2018-06-27
 */

package questions.temp;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*

Given an integer matrix, find the length of the longest increasing path.

From each cell, you can either move to four directions: left, right, up or down. You may NOT move diagonally or move outside of the boundary (i.e. wrap-around is not allowed).


Example 1:
    Input: nums = 
    [
      [9,9,4],
      [6,6,8],
      [2,1,1]
    ] 
    Output: 4 
    Explanation: The longest increasing path is [1, 2, 6, 9].

Example 2:
    Input: nums = 
    [
      [3,4,5],
      [3,2,6],
      [2,2,1]
    ] 
    Output: 4 
    Explanation: The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.

*/

public class _0329_Longest_Increasing_Path_in_a_Matrix {

    public static void main(String[] args) {
        int[][] matrix = { { 9, 9, 4 }, { 6, 6, 8 }, { 2, 1, 1 } };
        _0329_Longest_Increasing_Path_in_a_Matrix test = new _0329_Longest_Increasing_Path_in_a_Matrix();
        System.out.println(test.longestIncreasingPath_Topological(matrix));
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int longestIncreasingPath_Topological(int[][] matrix) {
        int[][] dirs = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
        int row = matrix.length;
        int col = matrix[0].length;
        // build graph & build indegree
        List<Integer>[] graph = new List[row * col];
        int[] indegree = new int[row * col];
        for (int r = 0; r < row; ++r) {
            for (int c = 0; c < col; ++c) {
                for (int[] dir : dirs) {
                    int _r = r + dir[0];
                    int _c = c + dir[1];
                    if (_c >= 0 && _c < col && _r >= 0 && _r < row && matrix[r][c] < matrix[_r][_c]) {
                        if (graph[r * col + c] == null) {
                            graph[r * col + c] = new ArrayList<>();
                        }
                        graph[r * col + c].add(_r * col + _c);
                        ++indegree[_r * col + _c];
                    }
                }
            }
        }
        // enque 0-indegree
        Queue<Integer> que = new LinkedList<>();
        for (int i = 0; i < indegree.length; ++i) {
            if (indegree[i] == 0) {
                que.add(i);
            }
        }
        // bfs, update indegrees, enque 0-indegree
        int layer = 0;
        while (!que.isEmpty()) {
            ++layer;
            int size = que.size();
            for (int i = 0; i < size; ++i) {
                int node = que.poll();
                List<Integer> neis = graph[node];
                if (neis != null) {
                    for (int nei : neis) {
                        --indegree[nei];
                        if (indegree[nei] == 0) {
                            que.offer(nei);
                        }
                    }
                }
            }
        }
        return layer;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // DFS + Memo
    int max = 0;
    int[] dirX = { 0, 1, 0, -1 };
    int[] dirY = { -1, 0, 1, 0 };
    int[][] matrix;
    int[][] memo;
    int row;
    int col;

    public int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        this.matrix = matrix;
        row = matrix.length;
        col = matrix[0].length;
        memo = new int[row][col];

        for (int i = 0; i < row; ++i) {
            for (int j = 0; j < col; ++j) {
                max = Math.max(max, DFS(i, j));
            }
        }
        return max;
    }

    private int DFS(int r, int c) {
        if (memo[r][c] > 0) {
            return memo[r][c];
        }
        for (int i = 0; i < 4; ++i) {
            int _r = r + dirY[i];
            int _c = c + dirX[i];
            if (_c >= 0 && _c < col && _r >= 0 && _r < row && matrix[r][c] < matrix[_r][_c]) {
                memo[r][c] = Math.max(memo[r][c], DFS(_r, _c) + 1);
            } else {
                memo[r][c] = Math.max(memo[r][c], 1);
            }
        }
        return memo[r][c];
    }

}
