/**
 * Copyright 2017 Yunxiang He
 *
 * All right reserved.
 *
 * Create on Oct 20, 2017 1:30:06 AM
 */
package algorithms.dynamic_programming;

/**
 *  @author Yunxiang He
 *  @date Oct 20, 2017 1:30:06 AM
 */
public class Fibonacci_Nums {

    private static int[] table = null;

    public static int tabulationApproach(int n) {
        int res = 0;
        for (int i = 2; i <= n; i++) {
            res = table[i] = table[i - 1] + table[i - 2];
        }
        return res;
    }

    public static int memorizationApproach(int n) {
        if (table[n] != 0) {
            return table[n];
        } else {
            table[n] = memorizationApproach(n - 1) + memorizationApproach(n - 2);
        }
        return table[n];
    }

    public static int recursiveApproach(int n) {
        return n > 2 ? recursiveApproach(n - 1) + recursiveApproach(n - 2) : 1;
    }

    public static void main(String[] args) {
        int n = 10;
        table = new int[n + 1];
        table[0] = table[1] = 1;
        //		System.out.println(tabulationApproach(n));
        //		System.out.println(memorizationApproach(n));
        System.out.println(recursiveApproach(n + 1));
    }
}
