/**
 *  @author Yunxiang He
 *  @date 12/19/2017
 */

package coding.temp;

/*

Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.


Example:
    Input:
        nums1 = [1,2,3,0,0,0], m = 3
        nums2 = [2,5,6],       n = 3
    Output: 
        [1,2,2,3,5,6]


Note:
    The number of elements initialized in nums1 and nums2 are m and n respectively.
    You may assume that nums1 has enough space (size that is greater or equal to m + n) to hold additional elements from nums2.

*/

public class _0088_Merge_Sorted_Array {

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        while (m + n > 0) {
            if (n <= 0 || m > 0 && nums1[m - 1] > nums2[n - 1]) {
                nums1[m + n - 1] = nums1[m - 1];
                m--;
            } else {
                nums1[m + n - 1] = nums2[n - 1];
                n--;
            }
        }
    }

    public static void main(String[] args) {
        new _0088_Merge_Sorted_Array().merge(new int[] { 1, 2, 3, 0, 0, 0, }, 3, new int[] { 2, 5, 6 }, 3);
    }
}
