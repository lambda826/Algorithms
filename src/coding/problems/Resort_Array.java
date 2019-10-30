/**
 *  @author Yunxiang He
 *  @date 11/24/2018
 */

package coding.problems;

import common.utils.RandomIntGenerator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*

给一个排序好的int数组，修改其中某些元素
让我处理修改过后的数组，使得这个数组依旧是排序好的数组。
时间复杂度要求是优于O(nlogn)。空间复杂度没有要求。

*/

public class Resort_Array {

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static int[] resortArray_BucketSort(int[] array) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < array.length; i++) {
            min = Math.min(min, array[i]);
            max = Math.max(max, array[i]);
        }
        int[] bucket = new int[max - min + 1];
        for (int num : array) {
            bucket[num - min]++;
        }
        int k = 0;
        int i = 0;
        while (k < bucket.length) {
            while (bucket[k] > 0) {
                array[i++] = min + k;
                bucket[k]--;
            }
            k++;
        }
        return array;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static Integer[] resortArray_BinarySearch(int[] array) {
        List<Integer> unorder = new ArrayList<>();
        List<Integer> order = new ArrayList<>();
        for (int i = 0, j = 0; j < array.length; j++) {
            if (array[i] <= array[j]) {
                i = j;
                order.add(array[j]);
            } else {
                unorder.add(array[j]);
            }
        }
        for (int num : unorder) {
            int index = Collections.binarySearch(order, num);
            if (index < 0) {
                index = -(index + 1);
            }
            order.add(index, num);
        }
        return order.toArray(new Integer[array.length]);
    }

    public static void main(String[] args) {
        int[] nums = RandomIntGenerator.generateIntArray();
        System.out.println(Arrays.toString(nums));
        int[] nums2 = Arrays.copyOf(nums, nums.length);
        Arrays.sort(nums2);
        System.out.println(Arrays.toString(nums2));
        //        System.out.println(Arrays.toString(resortArray_BucketSort(nums)));
        System.out.println("--------------------------------------");
        System.out.println(Arrays.toString(resortArray_BinarySearch(nums)));
    }
}
