package algorithms.dynamic_programming;

import java.util.Arrays;

/*

Given a chain <A1, A2, ..., An> of n matrices, where for i = 1, 2, ..., n, 
matrix Ai has dimension pi-1 x pi, 
fully parenthesize the product A1A2...An in a way that minimizes the number of scalar multiplications.
Note that in the matrix-chain multiplication problem, we are not actually multiplying matrices. 
Our goal is only to determine an order for multiplying matrices that has the lowest cost.

*/

public class Matrix_Chain_Multiplication {

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // m[i, j] = 0, if i = j
    // m[i, j] = min{m[i, k] + m[k + 1, j] + pi-1pkpj} if i < j
    public int matrix_chain_order(int[] p, int[][] m, int[][] s, int n) {
        // length of matrix chain, from 2 to n
        for (int l = 2; l <= n; l++) {
            for (int i = 0; i < n - l + 1; i++) {
                int j = i + l - 1;
                m[i][j] = Integer.MAX_VALUE;
                for (int k = i; k < j; k++) {
                    m[i][j] = Math.min(m[i][j], m[i][k] + m[k + 1][j] + p[i] * p[k + 1] * p[j + 1]);
                }
            }
        }
        return m[0][m.length - 1];
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int memorized_matrix_chain(int[] p, int[][] m, int[][] s, int n) {
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                m[i][j] = Integer.MAX_VALUE;
            }
        }
        look_up_table(p, m, s, 0, n - 1);
        return m[0][m.length - 1];
    }

    private int look_up_table(int[] p, int[][] m, int[][] s, int i, int j) {
        if (m[i][j] < Integer.MAX_VALUE) {
            return m[i][j];
        }
        if (i == j) {
            m[i][j] = 0;
        } else {
            for (int k = i; k < j; k++) {
                int q = look_up_table(p, m, s, i, k) + look_up_table(p, m, s, k + 1, j) + p[i] * p[k + 1] * p[j + 1];
                if (q < m[i][j]) {
                    m[i][j] = q;
                    s[i][j] = k;
                }
            }
        }
        return m[i][j];
    }

    private void print(int[][] s, int i, int j) {
        if (i == j) {
            System.out.print("A" + i);
        } else {
            System.out.print("(");
            print(s, i, s[i][j]);
            print(s, s[i][j] + 1, j);
            System.out.print(")");
        }
    }

    public static void main(String[] args) {
        Matrix_Chain_Multiplication test = new Matrix_Chain_Multiplication();
        int[] p = new int[] { 30, 35, 15, 5, 10, 20, 25 };
        int n = p.length - 1;
        int[][] m = new int[n][n];
        int[][] s = new int[n][n];
        System.out.println(test.matrix_chain_order(p, m, s, n));
        System.out.println(test.memorized_matrix_chain(p, m, s, n));
        Arrays.deepToString(m);
        System.out.println("----------------------------");
        Arrays.deepToString(s);
        test.print(s, 0, n - 1);
    }

}
