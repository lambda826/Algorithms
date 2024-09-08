/**
 * @author Yunxiang He
 * @date 03/07/2019
 */

package algorithms.heap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MaxHeap<T extends Comparable<T>> {

    private List<T> heap;

    public MaxHeap(List<T> list) {
        this.heap = list;
        build();
    }

    private void build() {
        for (int i = parent(heap.size() - 1); i >= 0; --i) {
            heapify_down(i);
        }
    }

    public List<T> heapSort() {
        List<T> temp = new ArrayList<T>(heap);
        List<T> res = new ArrayList<>();
        while (!heap.isEmpty()) {
            res.add(heap.get(0));
            swap(0, heap.size() - 1);
            heap.remove(heap.size() - 1);
            heapify_down(0);
        }
        heap = temp;
        return res;
    }

    public void add(T t) {
        heap.add(t);
        heapify_up(heap.size() - 1);
    }

    public void delete(int index) {
        swap(index, heap.size() - 1);
        heap.remove(heap.size() - 1);
        heapify_up(index);
        heapify_down(index);
    }

    private void heapify_up(int index) {
        if (!heap.isEmpty()) {
            if (index > 0) {
                int parent = parent(index);
                if (heap.get(index).compareTo(heap.get(parent)) > 0) {
                    swap(index, parent);
                    heapify_up(parent);
                }
            }
        }
    }

    private void heapify_down(int index) {
        if (!heap.isEmpty()) {
            int max = index;
            int left = left(index);
            int right = right(index);
            if (left == heap.size() - 1) {
                max = left;
            } else if (left < heap.size() - 1) {
                if (heap.get(left).compareTo(heap.get(right)) > 0) {
                    max = left;
                } else {
                    max = right;
                }
            }
            if (heap.get(index).compareTo(heap.get(max)) < 0) {
                swap(index, max);
                heapify_down(max);
            }
        }
    }

    private int parent(int index) {
        return (index - 1) >> 1;
    }

    private int left(int index) {
        return (index << 1) + 1;
    }

    private int right(int index) {
        return left(index) + 1;
    }

    private void swap(int index1, int index2) {
        T temp = heap.get(index1);
        heap.set(index1, heap.get(index2));
        heap.set(index2, temp);
    }

    public static void main(String[] args) {
        MaxHeap<Integer> maxHeap = new MaxHeap<>(new ArrayList<>(Arrays.asList(6, 4, 2, 3, 1, 7, 9)));
        System.out.println(maxHeap.heapSort());
    }
}
