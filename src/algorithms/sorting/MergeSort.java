///**
// *  @author Yunxiang He
// *  @date 10/09/2017
// */
//
//package algorithms.sorting;
//
//import common.utils.RandomIntGenerator;
//
//import java.util.Arrays;
//
//public class MergeSort {
//
//    public static void main(String[] args) {
//        int[] nums = RandomIntGenerator.generateIntArray();
//        int[] numsCopy = Arrays.copyOf(nums, nums.length);
//        System.out.println(Arrays.toString(nums));
//        nums = mergeSort(nums);
//        mergeSort(numsCopy, true);
//        System.out.println(Arrays.toString(nums));
//        System.out.println(Arrays.toString(numsCopy));
//    }
//
//    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//    // The parameter of method merge is two seperate array
//    public static int[] mergeSort(int[] nums) {
//        if (nums.length > 1) {
//            int mid = nums.length / 2;
//            return merge(mergeSort(Arrays.copyOfRange(nums, 0, mid)), mergeSort(Arrays.copyOfRange(nums, mid, nums.length)));
//        } else {
//            return nums;
//        }
//    }
//
//    private static int[] merge(int[] arr1, int[] arr2) {
//        int[] subNums = new int[arr1.length + arr2.length];
//        int i = 0;
//        int j = 0;
//        while (i + j < subNums.length) {
//            if (i == arr1.length || (j < arr2.length && arr1[i] > arr2[j])) {
//                subNums[i + j] = arr2[j];
//                j++;
//            } else {
//                subNums[i + j] = arr1[i];
//                i++;
//            }
//        }
//        return subNums;
//    }
//
//    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//    // The parameter of method merge is the original array and the indices
//    public static void mergeSort(int[] nums, boolean f) {
//        mergeSort(nums, 0, nums.length - 1);
//    }
//
//    public static void mergeSort(int[] nums, int from, int to) {
//        if (from < to) {
//            int mid = (from + to) / 2;
//            mergeSort(nums, from, mid);
//            mergeSort(nums, mid + 1, to);
//            merge(nums, from, mid + 1, to);
//        }
//    }
//
//    public static void merge(int[] nums, int from, int mid, int to) {
//        int[] arr1 = Arrays.copyOfRange(nums, from, mid);
//        int[] arr2 = Arrays.copyOfRange(nums, mid, to + 1);
//        int l = 0;
//        int r = 0;
//        for (int i = from; i <= to; i++) {
//            if ((l < arr1.length && r < arr2.length && arr1[l] > arr2[r]) || l == arr1.length) {
//                nums[i] = arr2[r];
//                r++;
//            } else
//            //                if (l < arr1.length && r < arr2.length && arr1[l] <= arr2[r] || r == arr2.length)
//            {
//                nums[i] = arr1[l];
//                l++;
//            }
//        }
//    }
//
//}
