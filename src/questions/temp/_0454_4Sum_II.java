/**
 * @author Yunxiang He
 * @date Jan 29, 2018 9:55:28 PM
 */

package questions.temp;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*

Given four lists A, B, C, D of integer values, compute how many tuples (i, j, k, l) there are such that A[i] + B[j] + C[k] + D[l] is zero.

To make problem a bit easier, all A, B, C, D have same length of N where 0 ≤ N ≤ 500. 
All integers are in the range of -228 to 228 - 1 and the result is guaranteed to be at most 231 - 1.

Example:

Input:
A = [ 1, 2]
B = [-2,-1]
C = [-1, 2]
D = [ 0, 2]

Output:
2

Explanation:
The two tuples are:
1. (0, 0, 0, 1) -> A[0] + B[0] + C[0] + D[1] = 1 + (-2) + (-1) + 2 = 0
2. (1, 1, 0, 0) -> A[1] + B[1] + C[0] + D[0] = 2 + (-1) + (-1) + 0 = 0

 */

public class _0454_4Sum_II {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int fourSumCount_HashTable(int[] A, int[] B, int[] C, int[] D) {

        int count = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int a : A) {
            for (int b : B) {
                int x = -a - b;
                map.put(x, map.getOrDefault(x, 0) + 1);
            }
        }

        for (int c : C) {
            for (int d : D) {
                int y = c + d;
                count += map.getOrDefault(y, 0);
            }
        }
        return count;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int fourSumCount_TwoPointers(int[] A, int[] B, int[] C, int[] D) {
        int[] AB = comb(A, B);
        int[] CD = comb(C, D);
        Arrays.sort(AB);
        Arrays.sort(CD);
        int count = 0;

        int lo = 0;
        int hi = CD.length - 1;
        while (lo < AB.length && hi >= 0) {
            if (AB[lo] + CD[hi] == 0) {
                int ab = 1;
                int cd = 1;
                while (lo + 1 < AB.length && AB[lo] == AB[lo + 1]) {
                    ab++;
                    lo++;
                }
                while (hi - 1 >= 0 && CD[hi] == CD[hi - 1]) {
                    cd++;
                    hi--;
                }
                count += ab * cd;
                lo++;
                hi--;
            } else if (AB[lo] + CD[hi] > 0) {
                hi--;
            } else {
                lo++;
            }
        }
        return count;
    }

    private int[] comb(int[] arr1, int[] arr2) {
        int[] com = new int[arr1.length * arr2.length];
        int i = 0;
        for (int a1 : arr1) {
            for (int a2 : arr2) {
                com[i] = a1 + a2;
                i++;
            }
        }
        return com;
    }

    public static void main(String[] args) {
        _0454_4Sum_II test = new _0454_4Sum_II();
        int count = test.fourSumCount_TwoPointers(new int[] { 1, 2 }, new int[] { -2, -1 }, new int[] { -1, 2 }, new int[] { 0, 2 });
        System.out.println(count);
    }
}
