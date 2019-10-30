/**
 *  @author Yunxiang He
 *  @date 01/02/2018
 */

package coding.temp;

import java.util.PriorityQueue;

/*

Given a n x n matrix where each of the rows and columns are sorted in ascending order, find the kth smallest element in the matrix.
Note that it is the kth smallest element in the sorted order, not the kth distinct element.


Example:
    matrix = [
       [ 1,  5,  9],
       [10, 11, 13],
       [12, 13, 15]
    ],
    k = 8,
    return 13.


Note: 
    You may assume k is always valid, 1 ≤ k ≤ n^2.

 */

public class _0378_Kth_Smallest_Element_in_a_Sorted_Matrix {

    public static void main(String[] args) {
        int[][] matrix = { { 1, 5, 9 }, { 10, 11, 13 }, { 12, 13, 15 } };
        int k = 2;
        _0378_Kth_Smallest_Element_in_a_Sorted_Matrix test = new _0378_Kth_Smallest_Element_in_a_Sorted_Matrix();
        test.kthSmallest2(matrix, k);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // heap
    public int kthSmallest(int[][] matrix, int k) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> -(a - b));
        int n = matrix[0].length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                maxHeap.offer(matrix[i][j]);
                if (maxHeap.size() > k) {
                    maxHeap.poll();
                }
            }
        }
        return maxHeap.poll();
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Binary Serach
    // Count the element that is less than or equal to the mid
    public int kthSmallest2(int[][] matrix, int k) {
        int n = matrix.length - 1;
        int lo = matrix[0][0];
        int hi = matrix[n][n];
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            int cnt = noGreaterThanMid(matrix, mid);
            if (cnt < k) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        return lo;
    }

    private int noGreaterThanMid(int[][] matrix, int mid) {
        int cnt = 0;
        for (int i = 0; i < matrix.length; ++i) {
            int j = matrix.length - 1;
            while (j >= 0 && matrix[i][j] > mid) {
                --j;
            }
            cnt += j + 1;
        }
        return cnt;
    }

}
