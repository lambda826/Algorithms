/**
 *  @author Yunxiang He
 *  @date 01/02/2018
 */

package coding.temp;

import java.util.Arrays;
import java.util.PriorityQueue;

/*

Find the kth largest element in an unsorted array. 
Note that it is the kth largest element in the sorted order, not the kth distinct element.


Example 1:
    Input: [3,2,1,5,6,4] and k = 2
    Output: 5

Example 2:
    Input: [3,2,3,1,2,4,5,5,6] and k = 4
    Output: 4


Note: 
    You may assume k is always valid, 1 ≤ k ≤ array's length.

*/

public class _0215_Kth_Largest_Element_in_an_Array {

    public static void main(String[] args) {
        _0215_Kth_Largest_Element_in_an_Array test = new _0215_Kth_Largest_Element_in_an_Array();
        int[] nums = new int[] { 3, 2, 1, 5, 6, 4 };
        System.out.println(test.findKthLargest(nums, 2));
        Arrays.sort(nums);
        System.out.println(Arrays.toString(nums));
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // heap
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int num : nums) {
            minHeap.offer(num);
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }
        return minHeap.peek();
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // quick select
    public int findKthLargest2(int[] nums, int k) {
        quickSelect(nums, 0, nums.length - 1, nums.length - k);
        return nums[nums.length - k];
    }

    private void quickSelect(int[] nums, int from, int to, int k) {
        if (from < to) {
            int idx = partition(nums, from, to, k);
            if (idx == k) {
                return;
            } else if (idx > k) {
                quickSelect(nums, from, idx - 1, k);
            } else {
                quickSelect(nums, idx + 1, to, k);
            }
        }
    }

    private int partition(int[] nums, int from, int to, int k) {
        int _to = to;
        int pivot = nums[to];
        while (from < to) {
            while (from < to && nums[from] < pivot) {
                ++from;
            }
            while (from < to && nums[to] >= pivot) {
                --to;
            }
            swap(nums, from, to);
        }
        swap(nums, from, _to);
        return from;
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }


}
