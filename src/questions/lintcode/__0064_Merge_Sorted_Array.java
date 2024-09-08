/**
 * @author: Yunxiang He
 * @date : 2018-10-09
 */

package questions.lintcode;

import java.util.Arrays;

/*

Given two sorted integer arrays A and B, merge B into A as one sorted array.


Example
    A = [1, 2, 3, empty, empty], B = [4, 5]
    After merge, A will be filled as [1, 2, 3, 4, 5]


Notice
    You may assume that A has enough space (size that is greater or equal to m + n) to hold additional elements from B. 
    The number of elements initialized in A and B are m and n respectively.

*/

public class __0064_Merge_Sorted_Array {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void mergeSortedArray(int[] A, int m, int[] B, int n) {
        int i = m - 1;
        int j = n - 1;
        while (i >= 0 || j >= 0) {
            if (i < 0 || j >= 0 && A[i] < B[j]) {
                A[i + j + 1] = B[j--];
            } else {
                A[i + j + 1] = A[i--];
            }
        }
    }

    public static void main(String[] args) {
        int[] A = new int[10];
        int[] B = new int[10];
        Arrays.fill(A, 10);
        Arrays.fill(B, 5);
        int j = 4;
        A[j] = B[--j];
        System.out.println(j);
        System.out.println(Arrays.toString(A));
    }

}
