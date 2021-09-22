/**
 * @author Yunxiang He
 * @date 12/21/2017
 */

package coding.leetcode.temp;


/*
 *
 * There are two sorted arrays nums1 and nums2 of size m and n respectively.
 *
 * Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
 *
 * Example 1: nums1 = [1, 3] nums2 = [2]
 * The median is 2.0
 *
 * Example 2: nums1 = [1, 2] nums2 = [3, 4]
 * The median is (2 + 3)/2 = 2.5
 *
 */

public class _0004_Median_of_Two_Sorted_Arrays {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // nums1 and nums2 are sorted, the median lies in the mid of the 2 arrays combined
    // To ensure, we collect the elements of the left and right half of the median
    // Finally, leftLen == RightLen, 
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }
        int len = nums1.length + nums2.length;
        int lo = 0;
        int hi = nums1.length;
        while (true) {
            int cut1 = lo + (hi - lo) / 2;
            int cut2 = len / 2 - cut1;
            int l1 = (cut1 == 0 ? Integer.MIN_VALUE : nums1[cut1 - 1]);
            int r1 = (cut1 == nums1.length ? Integer.MAX_VALUE : nums1[cut1]);
            int l2 = (cut2 == 0 ? Integer.MIN_VALUE : nums2[cut2 - 1]);
            int r2 = (cut2 == nums2.length ? Integer.MAX_VALUE : nums2[cut2]);
            if (l1 <= r2 && l2 <= r1) {
                if ((len & 1) == 1) {
                    return Math.min(r1, r2);
                } else {
                    return ((double) Math.max(l1, l2) + Math.min(r1, r2)) / 2;
                }
            } else if (l1 > r2) {
                hi = cut1 - 1;
            } else {
                lo = cut1 + 1;
            }
        }
    }

}
