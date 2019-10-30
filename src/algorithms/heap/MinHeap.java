/**
 *  @author Yunxiang He
 *  @date 03/07/2019
 */

package algorithms.heap;

import java.util.ArrayList;
import java.util.List;

public class MinHeap<T extends Comparable<T>> {

    private List<T> heap;

    public MinHeap(List<T> heap) {
        build();
    }

    // Do heapify_down operation from the second last layer to the root
    public void build() {
        for (int i = parent((heap.size() - 1)); i >= 0; --i) {
            heapify_down(i);
        }
    }

    // Extract from the root
    // Swap root to the tail
    // Delete the tail
    // Heapify_down
    public List<T> heapSort() {
        List<T> temp = new ArrayList<>(heap);
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

    // Add the new element to the tail of the list
    // Heapify_up
    public void add(T entry) {
        heap.add(entry);
        heapify_up(heap.size() - 1);
    }

    // Swap the to be deleted element to the tail
    // Delete the tail
    // Heapify_up
    // Heapify_down
    public T delete(int index) {
        T deleted = heap.get(index);
        heap.set(index, heap.get(heap.size() - 1));
        heap.remove(heap.size() - 1);
        heapify_up(index);
        heapify_down(index);
        return deleted;
    }

    private void heapify_up(int index) {
        if (!heap.isEmpty()) {
            if (index > 0) {
                int parent = parent(index);
                if (heap.get(index).compareTo(heap.get(parent)) < 0) {
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
                swap(max, index);
                heapify_down(max);
            }
        }
    }

    private void swap(int i, int j) {
        T temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
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
}
