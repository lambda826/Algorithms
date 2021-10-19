package algorithms.dynamic_programming;

/*

We are given two sequences X = <x1, x2, ..., xm> and Y = <y1, y2, ..., yn> 
find a maximum length common subsequence of X and Y

*/

public class Longest_Cmmon_Subsequence {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int lcs_length(String[] sequenceA, String[] sequenceB) {
        int m = sequenceA.length;
        int n = sequenceB.length;
        int[][] c = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (sequenceA[i - 1] == sequenceB[j - 1]) {
                    c[i][j] = c[i - 1][j - 1] + 1;
                } else if (c[i - 1][j] >= c[i][j - 1]) {
                    c[i][j] = c[i - 1][j];
                } else {
                    c[i][j] = c[i][j - 1];
                }
            }
        }
        return c[m][n];
    }

    public static void main(String[] args) {
        String[] sequenceA = new String[] { "A", "B", "C", "B", "D", "A", "B" };
        String[] sequenceB = new String[] { "B", "D", "C", "A", "B", "A" };
        System.out.println(new Longest_Cmmon_Subsequence().lcs_length(sequenceA, sequenceB));
    }

}
