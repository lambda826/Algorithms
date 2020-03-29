///**
// *  @author Yunxiang He
// *  @date 11/06/2018
// */
//
//package algorithms.heap;
//
//import common.utils.RandomIntGenerator;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class GeneralHeap<T extends Comparable<T>> {
//
//    private static final String MAX_HEAP = "MAX";
//    private String type = MAX_HEAP;
//    private List<T> heap;
//
//    public GeneralHeap(String type, List<T> heap) {
//        this.type = type;
//        this.heap = new ArrayList<>(heap);
//        build();
//    }
//
//    // Do heapify_down operation from the second last layer to the root
//    public void build() {
//        for (int i = parent((heap.size() - 1)); i >= 0; i--) {
//            heapify_down(i);
//        }
//    }
//
//    // Extract from the root
//    // Swap root to the tail
//    // Delete the tail
//    // Heapify_down
//    public List<T> heapSort() {
//        List<T> temp = new ArrayList<>(heap);
//        List<T> res = new ArrayList<>();
//        while (!temp.isEmpty()) {
//            res.add(temp.get(0));
//            swap(0, temp.size() - 1);
//            temp.remove(temp.size() - 1);
//            heapify_down(0);
//        }
//        return res;
//    }
//
//    // Add the new element to the tail of the list
//    // Heapify_up
//    public void add(T entry) {
//        heap.add(entry);
//        heapify_up(heap.size() - 1);
//    }
//
//    // Swap the to be deleted element to the tail
//    // Delete the tail
//    // Heapify_up
//    // Heapify_down
//    public T delete(int index) {
//        T deleted = heap.get(index);
//        heap.set(index, heap.get(heap.size() - 1));
//        heap.remove(heap.size() - 1);
//        heapify_up(index);
//        heapify_down(index);
//        return deleted;
//    }
//
//    public void heapify_up(int index) {
//        if (!heap.isEmpty()) {
//            if (type == MAX_HEAP) {
//                heapify_up_max(index);
//            } else {
//                heapify_up_min(index);
//            }
//        }
//    }
//
//    // Swap the index with its parent
//    // Move greater upward
//    private void heapify_up_max(int index) {
//        if (index > 0) {
//            int parent = parent(index);
//            if (heap.get(index).compareTo(heap.get(parent)) > 0) {
//                swap(index, parent);
//                heapify_up_max(parent);
//            }
//        }
//    }
//
//    // Swap the index with its parent
//    // Move smaller upward
//    private void heapify_up_min(int index) {
//        if (index > 0) {
//            int parent = parent(index);
//            if (heap.get(index).compareTo(heap.get(parent)) < 0) {
//                swap(index, parent);
//                heapify_up_min(parent);
//            }
//        }
//    }
//
//    public void heapify_down(int index) {
//        if (!heap.isEmpty()) {
//            if (type == MAX_HEAP) {
//                heapify_down_max(index);
//            } else {
//                heapify_down_min(index);
//            }
//        }
//    }
//
//    // Move smaller downward, greater upward
//    // If left is the last element, pick left to be the candidate to be swapped or not
//    // If left is not the last element, pick greater of left or right to be the candidate to be swapped or not
//    private void heapify_down_max(int index) {
//        int max = index;
//        int left = left(index);
//        int right = right(index);
//        if (left == heap.size() - 1) {
//            max = left;
//        } else if (left < heap.size() - 1) {
//            if (heap.get(left).compareTo(heap.get(right)) > 0) {
//                max = left;
//            } else {
//                max = right;
//            }
//        }
//        if (heap.get(index).compareTo(heap.get(max)) < 0) {
//            swap(max, index);
//            heapify_down_max(max);
//        }
//    }
//
//    // Move greater downward, smaller upward
//    // If left is the last element, pick left to be the candidate to be swapped or not
//    // If left is not the last element, pick smaller of left or right to be the candidate to be swapped or not
//    private void heapify_down_min(int index) {
//        int min = index;
//        int left = left(index);
//        int right = right(index);
//        if (left == heap.size() - 1) {
//            min = left;
//        } else if (left < heap.size() - 1) {
//            if (heap.get(left).compareTo(heap.get(right)) < 0) {
//                min = left;
//            } else {
//                min = right;
//            }
//        }
//        if (heap.get(index).compareTo(heap.get(min)) > 0) {
//            swap(min, index);
//            heapify_down_min(min);
//        }
//    }
//
//    public boolean isValid() {
//        return type == MAX_HEAP ? isValid_max(parent((heap.size() - 1))) : isValid_min(parent((heap.size() - 1)));
//    }
//
//    private boolean isValid_max(int index) {
//        if (index < 0) {
//            int left = left(index);
//            int right = right(index);
//            return heap.get(index).compareTo(heap.get(left)) >= 0 && heap.get(index).compareTo(heap.get(right)) >= 0 && isValid_max(index - 1);
//        } else {
//            return true;
//        }
//    }
//
//    private boolean isValid_min(int index) {
//        if (index < 0) {
//            int left = left(index);
//            int right = right(index);
//            return heap.get(index).compareTo(heap.get(left)) <= 0 && heap.get(index).compareTo(heap.get(right)) <= 0 && isValid_max(index - 1);
//        } else {
//            return true;
//        }
//    }
//
//    private void swap(int index1, int index2) {
//        T temp = heap.get(index1);
//        heap.set(index1, heap.get(index2));
//        heap.set(index2, temp);
//    }
//
//    private int parent(int index) {
//        return (index - 1) >> 1;
//    }
//
//    private int left(int index) {
//        return (index << 1) + 1;
//    }
//
//    private int right(int index) {
//        return left(index) + 1;
//    }
//
//    public static void main(String[] args) {
//        List<Integer> list1 = RandomIntGenerator.generateIntList();
//        List<Integer> list2 = new ArrayList<>(list1);
//        GeneralHeap<Integer> maxHeap = new GeneralHeap<>("MAX", list1);
//        System.out.println(maxHeap.isValid());
//        System.out.println(maxHeap.heap);
//        System.out.println(maxHeap.heapSort());
//        System.out.println("delete: " + maxHeap.delete(2));
//        System.out.println(maxHeap.heap);
//        System.out.println(maxHeap.isValid());
//        System.out.println(maxHeap.heapSort());
//
//        System.out.println("----------------------------------------------");
//
//        GeneralHeap<Integer> minHeap = new GeneralHeap<>("MIN", list2);
//        System.out.println(minHeap.isValid());
//        System.out.println(minHeap.heap);
//        System.out.println(minHeap.heapSort());
//
//        System.out.println("delete: " + minHeap.delete(3));
//        System.out.println(minHeap.heap);
//        System.out.println(minHeap.isValid());
//        System.out.println(minHeap.heapSort());
//
//    }
//}
