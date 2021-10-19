

package algorithms.sorting;


import java.util.Arrays;

public class MergeSort {


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // The parameter of method merge is two seperate array
    public static int[] mergeSort(int[] nums) {
        if (nums.length > 1) {
            int mid = nums.length / 2;
            return merge(mergeSort(Arrays.copyOfRange(nums, 0, mid)), mergeSort(Arrays.copyOfRange(nums, mid, nums.length)));
        } else {
            return nums;
        }
    }

    private static int[] merge(int[] arr1, int[] arr2) {
        int[] subNums = new int[arr1.length + arr2.length];
        int i = 0;
        int j = 0;
        while (i + j < subNums.length) {
            if (i == arr1.length || (j < arr2.length && arr1[i] > arr2[j])) {
                subNums[i + j] = arr2[j];
                j++;
            } else {
                subNums[i + j] = arr1[i];
                i++;
            }
        }
        return subNums;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // The parameter of method merge is the original array and the indices
    public static void mergeSort(int[] nums, boolean f) {
        mergeSort(nums, 0, nums.length - 1);
    }

    public static void mergeSort(int[] nums, int from, int to) {
        if (from < to) {
            int mid = (from + to) / 2;
            mergeSort(nums, from, mid);
            mergeSort(nums, mid + 1, to);
            merge(nums, from, mid + 1, to);
        }
    }

    public static void merge(int[] nums, int from, int mid, int to) {
        int[] arr1 = Arrays.copyOfRange(nums, from, mid);
        int[] arr2 = Arrays.copyOfRange(nums, mid, to + 1);
        int l = 0;
        int r = 0;
        for (int i = from; i <= to; i++) {
            if ((l < arr1.length && r < arr2.length && arr1[l] > arr2[r]) || l == arr1.length) {
                nums[i] = arr2[r];
                r++;
            } else
            //                if (l < arr1.length && r < arr2.length && arr1[l] <= arr2[r] || r == arr2.length)
            {
                nums[i] = arr1[l];
                l++;
            }
        }
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Recursive merge sort
    // with extra parameter, naxele
    public int[] mergeSortInPlace(int[] nums) {
        mergeSortInPlace(nums, 0, nums.length - 1);
        return nums;
    }

    private void mergeSortInPlace(int[] nums, int lo, int hi) {
        if (lo < hi) {
            int mid = (lo + hi) / 2;
            mergeSort(nums, lo, mid);
            mergeSort(nums, mid + 1, hi);
            mergeInPlace(nums, lo, mid + 1, hi);
        }
    }

    private void mergeInPlace(int[] nums, int s1, int s2, int end) {
        if (s1 < s2) {
            int max = Math.max(nums[s2 - 1], nums[end]) + 1;
            int _s1 = s1;
            int _s2 = s2;
            int i = s1;
            while (_s1 < s2 || _s2 <= end) {
                if (_s1 == s2) {
                    nums[i] += (nums[_s2] % max) * max;
                    _s2++;
                } else if (_s2 > end) {
                    nums[i] += (nums[_s1] % max) * max;
                    _s1++;
                } else {
                    int v1 = nums[_s1] % max;
                    int v2 = nums[_s2] % max;
                    if (v1 < v2) {
                        nums[i] += v1 * max;
                        _s1++;
                    } else {
                        nums[i] += v2 * max;
                        _s2++;
                    }
                }
                i++;
            }
            for (int j = s1; j <= end; ++j) {
                nums[j] /= max;
            }
        }
    }
}