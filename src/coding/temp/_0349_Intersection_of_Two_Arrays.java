/**
 *  @author Yunxiang He
 *  @date 01/17/2018
 */

package coding.temp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*

Given two arrays, write a function to compute their intersection.


Example 1:
    Input: nums1 = [1,2,2,1], nums2 = [2,2]
    Output: [2]

Example 2:
    Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
    Output: [9,4]


Note:
    Each element in the result must be unique.
    The result can be in any order.
 

 */

public class _0349_Intersection_of_Two_Arrays {

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int[] intersection(int[] nums1, int[] nums2) {
        if (nums1.length == 0 || nums2.length == 0) {
            return new int[0];
        }
        Set<Integer> set = new HashSet<>();
        for (int x : nums1) {
            set.add(x);
        }
        Set<Integer> intersection = new HashSet<>();
        for (int x : nums2) {
            if (set.contains(x)) {
                intersection.add(x);
            }
        }
        int[] res = new int[intersection.size()];
        int k = 0;
        for (int i : intersection) {
            res[k++] = i;
        }
        return res;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int[] intersection_BinarySearch(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            intersection_BinarySearch(nums2, nums1);
        }
        if (nums1.length == 0 || nums2.length == 0) {
            return new int[0];
        }

        Arrays.sort(nums1);
        Arrays.sort(nums2);

        List<Integer> intersetion = new ArrayList<>();
        Integer pre = null;
        for (int n : nums1) {
            if (null != pre && n == pre) {
                continue;
            }
            intersection_BinarySearch_Aux(n, nums2, intersetion);
            pre = n;
        }

        int k = 0;
        int[] result = new int[intersetion.size()];
        for (int n : intersetion) {
            result[k] = n;
            k++;
        }
        return result;
    }

    private void intersection_BinarySearch_Aux(int target, int[] nums, List<Integer> intersection) {
        int lo = 0;
        int hi = nums.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] == target) {
                intersection.add(target);
                break;
            } else if (nums[mid] > target) {
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int[] intersection_TwoPointers(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);

        int index1 = 0;
        int index2 = 0;
        int k = 0;
        int[] res = new int[Math.min(nums1.length, nums2.length)];
        while (index1 < nums1.length && index2 < nums2.length) {
            while (index1 + 1 < nums1.length && nums1[index1] == nums1[index1 + 1]) {
                index1++;
            }
            while (index2 + 1 < nums2.length && nums2[index2] == nums2[index2 + 1]) {
                index2++;
            }
            if (nums1[index1] == nums2[index2]) {
                res[k] = nums1[index1];
                index1++;
                index2++;
                k++;
            } else if (nums1[index1] > nums2[index2]) {
                index2++;
            } else {
                index1++;
            }

        }

        return Arrays.copyOf(res, k);
    }

    public static void main(String[] args) {
        int[] nums1 = { 2 };
        int[] nums2 = { 2 };
        int[] res = new _0349_Intersection_of_Two_Arrays().intersection_BinarySearch(nums1, nums2);
        System.out.println(Arrays.toString(res));
    }
}
