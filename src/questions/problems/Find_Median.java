/**
 *  @author: Yunxiang He
 *  @date  : 2018-11-18
 */

package questions.problems;

import java.util.Arrays;

/*

给一个unsorted array， 要求找出中位数。follow up让用recursive的quick sort做

 */

public class Find_Median {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    boolean isFound = false;

    public double getMedian_QuickSelect(int[] nums) {
        double res = 0;
        quickSelect(nums, 0, nums.length - 1, (nums.length - 1) / 2);
        if ((nums.length & 1) == 0) {
            res = (nums[(nums.length - 1) / 2] + findMin(nums, (nums.length - 1) / 2 + 1)) / 2.0;
        } else {
            res = nums[(nums.length - 1) / 2];
        }
        return res;
    }

    private void quickSelect(int[] nums, int left, int right, int targetIndex) {
        if (left == right) {
            isFound = true;
        }
        while (left < right && !isFound) {
            int index = partition(nums, left, right);
            if (index == targetIndex) {
                isFound = true;
            } else if (index > targetIndex) {
                quickSelect(nums, left, index - 1, targetIndex);
            } else {
                quickSelect(nums, index + 1, right, targetIndex);
            }
        }
    }

    public int partition(int[] nums, int left, int right) {
        int pivot = nums[right];
        int index = left;
        for (int i = left; i < right; i++) {
            if (nums[i] < pivot) {
                swap(nums, index++, i);
            }
        }
        swap(nums, index, right);
        return index;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private int findMin(int[] nums, int from) {
        int min = Integer.MAX_VALUE;
        while (from < nums.length) {
            min = Math.min(min, nums[from++]);
        }
        return min;
    }

    public static void main(String[] args) {
        int[] nums = new int[] { 1, 4, 17, 17, 8, 11, 18, 12, 9, 19 };
        int[] nums2 = Arrays.copyOf(nums, nums.length);
        Arrays.sort(nums2);
        System.out.println(Arrays.toString(nums2));
        Find_Median test = new Find_Median();
        System.out.println(test.getMedian_QuickSelect(nums));
    }
}
