package questions._09_DFS_backtracking;

/*

You are given an m x n binary matrix grid.
An island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.)
You may assume all four edges of the grid are surrounded by water.

The area of an island is the number of cells with a value 1 on the island.

Return the maximum area of an island in grid. If there is no island, return 0.


Example 1:
    Input:
        grid =
            [[0,0,1,0,0,0,0,1,0,0,0,0,0],
             [0,0,0,0,0,0,0,1,1,1,0,0,0],
             [0,1,1,0,1,0,0,0,0,0,0,0,0],
             [0,1,0,0,1,1,0,0,1,0,1,0,0],
             [0,1,0,0,1,1,0,0,1,1,1,0,0],
             [0,0,0,0,0,0,0,0,0,0,1,0,0],
             [0,0,0,0,0,0,0,1,1,1,0,0,0],
             [0,0,0,0,0,0,0,1,1,0,0,0,0]]
    Output:
        6
    Explanation:
        The answer is not 11, because the island must be connected 4-directionally.

Example 2:
    Input:
        grid = [[0,0,0,0,0,0,0,0]]
    Output:
        0


Constraints:
    m == grid.length
    n == grid[i].length
    1 <= m, n <= 50
    grid[i][j] is either 0 or 1.

*/

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public class _0695_Max_Area_of_Island {

    private static final int[] dir = { -1, 0, 1, 0, -1 };

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    class Solution {

        private final int[] dir = { -1, 0, 1, 0, -1 };

        public int maxAreaOfIsland(int[][] grid) {
            int max = 0;
            for (int i = 0; i < grid.length; ++i) {
                for (int j = 0; j < grid[0].length; ++j) {
                    max = Math.max(max, getArea(i, j, grid));
                }
            }
            return max;
        }

        private int getArea(int i, int j, int[][] grid) {
            int area = 0;
            if (i >= 0 && i < grid.length && j >= 0 && j < grid[0].length && grid[i][j] == 1) {
                grid[i][j] = 0;
                area = 1;
                for (int k = 0; k < 4; ++k) {
                    area += getArea(i + dir[k], j + dir[k + 1], grid);
                }
            }
            return area;
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Followup: 是要把 enclosed water cell 也算进去
    // Clarification: a water cell can be enclosed by multiple islands, how should we count this case?
    //                  are those islands considered connected or separate?
    static class Solution_FollowUp {

        private final int[] dir = { -1, 0, 1, 0, -1 };

        public int maxAreaOfIsland(int[][] grid) {

            // Find all water cells which are not enclosed
            for (int i = 0; i < grid.length; ++i) {
                for (int j = 0; j < grid[0].length; ++j) {
                    if (i == 0 || i == grid.length - 1 || j == 0 || j == grid[0].length) {
                        getArea(i, j, grid, -1, n -> n == 0);
                    }
                }
            }

            // Mark different island
            Map<Integer, Integer> islandArea = new HashMap<>();
            int mark = 1;
            for (int i = 0; i < grid.length; ++i) {
                for (int j = 0; j < grid[0].length; ++j) {
                    if (grid[i][j] == 1) {
                        ++mark;
                        islandArea.put(mark, getArea(i, j, grid, mark, n -> n == 1));
                    }
                }
            }

            // Update enclose water
            for (int i = 0; i < grid.length; ++i) {
                for (int j = 0; j < grid[0].length; ++j) {
                    if (grid[i][j] == 0) {
                        Integer[] enclose = new Integer[] { null };
                        islandArea.put(enclose[0], islandArea.getOrDefault(enclose[0], 0)
                                                   + updateEncloseWater(i, j, grid, enclose));
                    }
                }
            }
            islandArea.remove(0);
            return islandArea.values().stream().mapToInt(Integer::intValue).max().getAsInt();
        }

        private int getArea(int i, int j, int[][] grid, int update, Predicate<Integer> isTarget) {
            int area = 0;
            if (i >= 0 && i < grid.length && j >= 0 && j < grid[0].length && isTarget.test(grid[i][j])) {
                grid[i][j] = update;
                area = 1;
                for (int k = 0; k < 4; ++k) {
                    area += getArea(i + dir[k], j + dir[k + 1], grid, update, isTarget);
                }
            }
            return area;
        }

        private int updateEncloseWater(int i, int j, int[][] grid, Integer[] enclose) {
            int area = 0;
            if (i >= 0 && i < grid.length && j >= 0 && j < grid[0].length) {
                if (grid[i][j] == 0) {
                    area = 1;
                    grid[i][j] = -1;
                    for (int k = 0; k < 4; ++k) {
                        area += updateEncloseWater(i + dir[k], j + dir[k + 1], grid, enclose);
                    }
                } else if (enclose[0] == null) {
                    enclose[0] = grid[i][j];
                } else if (enclose[0] != grid[i][j]) {
                    enclose[0] = 0;
                }
            }
            return area;
        }

        public static void main(String[] args) {
            System.out.println(new Solution_FollowUp().maxAreaOfIsland(
                    new int[][] { { 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0 },
                            { 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0 },
                            { 0, 1, 1, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0 },
                            { 0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0 },
                            { 0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0 },
                            { 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0 },
                            { 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0 },
                            { 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0 } }));
        }
    }
}