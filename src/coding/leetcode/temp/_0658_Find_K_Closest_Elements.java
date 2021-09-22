/**
 *  @author Yunxiang He
 *  @date Dec 28, 2017 5:21:57 AM
 */

package coding.leetcode.temp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*

Given a sorted array, two integers k and x, find the k closest elements to x in the array. 
The result should also be sorted in ascending order. 
If there is a tie, the smaller elements are always preferred.

Example 1:
Input: [1,2,3,4,5], k=4, x=3
Output: [1,2,3,4]

Example 2:
Input: [1,2,3,4,5], k=4, x=-1

Output: [1,2,3,4]

Note:
The value k is positive and will always be smaller than the length of the sorted array.
Length of the given array is positive and will not exceed 10^4
Absolute value of elements in the array and x will not exceed 10^4

UPDATE (2017/9/19):
The arr parameter had been changed to an array of integers (instead of a list of integers). 
Please reload the code definition to get the latest changes.

 */

public class _0658_Find_K_Closest_Elements {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Find the start point
    public List<Integer> findClosestElements_BinarySearch(int[] arr, int k, int x) {
        List<Integer> res = new ArrayList<>();
        int lo = 0;
        int hi = arr.length - k;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (x - arr[mid] > arr[mid + k] - x) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        for (int i = lo; i < lo + k; i++) {
            res.add(arr[i]);
        }
        return res;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public List<Integer> findClosestElements_BinarySearch2(int[] arr, int k, int x) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        int i = Arrays.binarySearch(arr, x);
        int lo = 0;
        int hi = 0;
        if (i >= 0) {
            lo = i - 1;
            hi = i + 1;
            list.add(arr[i]);
        } else {
            lo = -i - 2;
            hi = -i - 1;
        }
        while (list.size() < k) {
            if (lo < 0) {
                list.add(arr[hi]);
                hi++;
                continue;
            }
            if (hi > arr.length - 1) {
                list.add(arr[lo]);
                lo--;
                continue;
            }
            if (x - arr[lo] > arr[hi] - x) {
                list.add(arr[hi++]);
            } else {
                list.add(arr[lo--]);
            }

        }
        Collections.sort(list);
        return list;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Delete the n- k farthest elements
    // Delete the elements from the left end or right end who has farther distance to the x
    // Iterate until the remaining length (hi - lo) is k
    public List<Integer> findClosestElements_ReverseThinking(int[] arr, int k, int x) {
        int lo = 0;
        int hi = arr.length - 1;
        while (hi - lo + 1 != k) {
            if (x - arr[lo] > arr[hi] - x) {
                lo++;
            } else {
                hi--;
            }
        }
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = lo; i < lo + k; i++) {
            list.add(arr[i]);
        }
        return list;
    }

    public static void main(String[] args) {
        List<Integer> list2 = new _0658_Find_K_Closest_Elements().findClosestElements_BinarySearch(new int[] { 1, 3, 4, 7, 12, 23, 34, 35, 55, 57 }, 2, 9);
        for (int i : (ArrayList<Integer>) list2) {
            System.out.println(i);
        }

    }
}
