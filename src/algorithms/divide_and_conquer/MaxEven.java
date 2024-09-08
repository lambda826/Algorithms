/**
 * Copyright 2017 Yunxiang He
 *
 * All right reserved.
 *
 * Create on Oct 10, 2017 8:54:34 AM
 */
package algorithms.divide_and_conquer;

/**
 * @author Yunxiang He
 * @date Oct 10, 2017 8:54:34 AM
 */
public class MaxEven {
    public static int MaxEvenMethod(int[] arr, int from, int to) {
        if (to - from > 0) {
            return Math.max(MaxEvenMethod(arr, from, (from + to) / 2), MaxEvenMethod(arr, (from + to) / 2 + 1, to));
        }
        return (arr[(from + to) / 2] & 0) == 1 ? arr[(from + to) / 2] : 0;
    }

    public static void main(String[] args) {
        int[] arr = new int[] { 12, 33, 42, 15, 16, 1, 2, 4 };
        int max = MaxEvenMethod(arr, 0, arr.length - 1);
        System.out.println(max);
    }
}
