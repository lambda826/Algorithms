/**
 * @author Yunxiang He
 * @date Feb 4, 2018 12:23:31 PM
 */

package questions.problems;

/*

m*n格子，能往右往下或者往右下走，问有多少种走法

 */

public class Unique_Path {

    public int solution(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int row = grid.length;
        int col = grid[0].length;

        int[][] dp = new int[row + 1][col + 1];
        dp[0][0] = 1;
        for (int i = 1; i < row + 1; i++) {
            for (int j = 1; j < col + 1; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1] + dp[i - 1][j - 1];
            }
        }
        return dp[row][col];
    }

    public static void main(String[] args) {
        Unique_Path test = new Unique_Path();

        //        int[][] grid0 = new int[0][0];
        //        int[][] grid1 = new int[0][1];
        //        int[][] grid2 = new int[1][0];
        //        int[][] grid3 = new int[1][1];
        //        int[][] grid4 = new int[1][2];
        int[][] grid5 = new int[3][3];

        //        System.out.println(test.solution(grid0));
        //        System.out.println(test.solution(grid1));
        //        System.out.println(test.solution(grid2));
        //        System.out.println(test.solution(grid3));
        //        System.out.println(test.solution(grid4));
        System.out.println(test.solution(grid5));

    }
}
