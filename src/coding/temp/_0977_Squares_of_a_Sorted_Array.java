/**
 *  @author Yunxiang He
 *  @date 01/19/2019
 */

package coding.temp;

/*

Given an array of integers A sorted in non-decreasing order, return an array of the squares of each number, also in sorted non-decreasing order.


Example 1:
    Input: [-4,-1,0,3,10]
    Output: [0,1,9,16,100]
Example 2:
    Input: [-7,-3,2,3,11]
    Output: [4,9,9,49,121]
 

Note:
    1 <= A.length <= 10000
    -10000 <= A[i] <= 10000
    A is sorted in non-decreasing order.

*/

public class _0977_Squares_of_a_Sorted_Array {

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int[] sortedSquares(int[] A) {
        int[] res = new int[A.length];
        int left = 0;
        int right = A.length - 1;
        int index = right;
        while (index >= 0) {
            if (A[left] < 0 && -A[left] > A[right]) {
                res[index] = A[left] * A[left];
                left++;
            } else {
                res[index] = A[right] * A[right];
                right--;
            }
            index--;
        }
        return res;
    }

    public static void main(String[] args) {
        new _0977_Squares_of_a_Sorted_Array().sortedSquares(new int[] { -4, -1, 0, 3, 10 });
    }
}
