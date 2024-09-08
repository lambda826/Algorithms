/**
 * @author: Yunxiang He
 * @date : 2018-10-29
 */

package algorithms.heap;

import java.util.Arrays;

public class MinHeap2 {

    private final int[] nums;
    private int heapMaxIndex;

    public MinHeap2(int[] data, int length) {
        nums = new int[length];
        System.arraycopy(data, 0, nums, 0, data.length);
        heapMaxIndex = data.length - 1;
        buildHeap();
    }

    public void buildHeap() {
        for (int i = parent(heapMaxIndex - 1); i >= 0; i--) {
            heapify_down(i);
        }
    }

    public void heapSort() {
        int temp = heapMaxIndex;
        while (heapMaxIndex > 0) {
            swap(0, heapMaxIndex);
            heapMaxIndex--;
            heapify_down(0);
        }
        heapMaxIndex = temp;
    }

    public void add(int num) {
        if (heapMaxIndex + 1 < nums.length) {
            nums[++heapMaxIndex] = num;
            heapify_up(heapMaxIndex);
        }
    }

    public void delete(int index) {
        nums[index] = nums[heapMaxIndex];
        heapMaxIndex--;
        heapify_up(index);
        heapify_down(index);
    }

    private void heapify_up(int index) {
        if (index > 0) {
            int parent = parent(index);
            if (nums[index] < nums[parent]) {
                swap(index, parent);
                heapify_up(parent);
            }
        }
    }

    private void heapify_down(int index) {
        int min = index;
        int left = left(index);
        int right = right(index);
        if (left < heapMaxIndex) {
            if (nums[left] < nums[right]) {
                min = left;
            } else {
                min = right;
            }
        } else if (left == heapMaxIndex) {
            min = left;
        }
        if (nums[index] > nums[min]) {
            swap(min, index);
            heapify_down(min);
        }
    }

    private void swap(int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private int parent(int index) {
        return (index - 1) >> 1;
    }

    private int left(int index) {
        return (index << 1) + 1;
    }

    private int right(int index) {
        return (index << 1) + 2;
    }

    public static void main(String[] args) {
        int[] data = { 4, 7, 21, 10, 16, 7, 11, 15, 17, 20, 17, 15, 8, 16 };
        MinHeap2 heap = new MinHeap2(data, 30);
        heap.heapSort();
        System.out.println(Arrays.toString(heap.nums));
    }
}
