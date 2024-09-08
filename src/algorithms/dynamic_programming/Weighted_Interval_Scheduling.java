/**
 * Copyright 2017 Yunxiang He
 *
 * All right reserved.
 *
 * Create on Oct 23, 2017 4:46:45 PM
 */
package algorithms.dynamic_programming;

import common.Interval;

import java.util.Arrays;

/**
 * @author Yunxiang He
 * @date Oct 23, 2017 4:46:45 PM
 */
public class Weighted_Interval_Scheduling {

    private static int[] m;
    static Interval[] interval;

    // recursive
    public static int maxVal(int j) {
        if (j == -1) {
            return 0;
        } else if (m[j] != 0) {
            return m[j];
        } else {
            m[j] = Math.max(interval[j].getWeight() + maxVal(p(j)), maxVal(j - 1));
            return m[j];
        }
    }

    // iterative
    public static void maxVal2(int j) {
        for (int i = 0; i <= j; i++) {
            int p = p(i);
            m[i] = Math.max(interval[i].getWeight() + (p < 0 ? 0 : m[p]), i - 1 < 0 ? 0 : m[i - 1]);
        }
    }

    private static int p(int j) {
        int start = interval[j].getStart();
        for (int i = interval.length - 1; i >= 0; i--) {
            if (interval[i].getEnd() <= start) {
                return i;
            }
        }
        return -1;
    }

    public static void findSolution() {
        int j = interval.length - 1;
        while (j >= 0) {
            int i = p(j);
            if (interval[j].getWeight() + (i < 0 ? 0 : m[i]) >= (j - 1 < 0 ? 0 : m[j - 1])) {
                System.out.println(interval[j].getName());
                j = i;
            } else {
                j--;
            }
        }
    }

    public static void main(String[] args) {
        Interval i1 = new Interval("i1", 0, 4, 2);
        Interval i2 = new Interval("i2", 1, 6, 4);
        Interval i3 = new Interval("i3", 5, 8, 4);
        Interval i4 = new Interval("i4", 2, 10, 7);
        Interval i5 = new Interval("i5", 9, 12, 2);
        Interval i6 = new Interval("i6", 9, 13, 1);

        interval = new Interval[] { i6, i5, i4, i3, i2, i1 };
        Arrays.sort(interval);
        m = new int[interval.length];
        //		maxVal(m.length - 1);
        maxVal2(m.length - 1);
        int max = Integer.MIN_VALUE;
        for (int x : m) {
            max = Math.max(max, x);
        }
        System.out.println(max);
        findSolution();
    }
}
