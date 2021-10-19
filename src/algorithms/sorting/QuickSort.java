package algorithms.sorting;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class QuickSort {

    public static void main(String[] args) {
        int len = ThreadLocalRandom.current().nextInt(20, 30);
        System.out.println(len);
        int[] array = new int[len];
        for (int i = 0; i < array.length; ++i) {
            array[i] = ThreadLocalRandom.current().nextInt(100);
        }
        System.out.println(Arrays.toString(array));
        int[] newArr = Arrays.copyOf(array, array.length);
        Arrays.sort(newArr);
        System.out.println(Arrays.toString(newArr));
        quickSort(array);
        System.out.println(Arrays.toString(array));
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Chose the last element to be the pivot
    // Move all elements which are less than pivot to the left
    public static void quickSort(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
    }

    /**
     * 1. Partition
     * 2. QuickSort(left, indexPivot - 1)
     * 3. QuickSort(indexPivot + 1, right)
     */
    private static void quickSort(int[] nums, int left, int right) {
        if (left < right) {
            int indexPivot = partition(nums, left, right);
            // int indexPivot = partition2(nums, left, right);
            quickSort(nums, left, indexPivot - 1);
            quickSort(nums, indexPivot + 1, right);
        }
    }

    private static int partition(int[] nums, int left, int right) {
        int idx = right;
        swap(nums, ThreadLocalRandom.current().nextInt(left, right + 1), right);
        while (left < right) {
            while (left < right && nums[left] < nums[idx]) {
                left++;
            }
            while (left < right && nums[right] >= nums[idx]) {
                right--;
            }
            swap(nums, left, right);
        }
        swap(nums, left, idx);
        return left;
    }

    private static int partition2(int[] nums, int left, int right) {
        int pivot = nums[right];
        int idx = left;
        for (int i = left; i < right; i++) {
            // Put the elements that smaller than pivot in the front
            if (nums[i] < pivot) {
                swap(nums, idx, i);
                idx++;
            }
        }
        swap(nums, idx, right);
        return idx;
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static void quickSort2(int[] nums) {
        quickSort2(nums, 0, nums.length - 1);
    }

    private static void quickSort2(int[] nums, int left, int right) {
        if (left < right) {
            int idx = partition3(nums, left, right);
            // Elements(< idx) <= elements(>= idx)
            quickSort2(nums, left, idx - 1);
            quickSort2(nums, idx, right);
        }
    }

    private static int partition3(int[] nums, int left, int right) {
        System.out.print("left : " + left + " right: " + right + "   | ");
        int pivot = nums[ThreadLocalRandom.current().nextInt(left, right + 1)];
        while (left <= right) {
            // find the first element that is equal to/greater than pivot
            while (nums[left] < pivot) {
                left++;
            }
            // find the first element that is equal to/less than pivot
            while (nums[right] > pivot) {
                right--;
            }
            if (left <= right) {
                swap(nums, left, right);
                left++;
                right--;
            }
        }
        System.out.println(Arrays.toString(nums) + " | left: " + left + " | pivot: " + pivot);
        return left; // nums[left - 1] <= pivot
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

}
