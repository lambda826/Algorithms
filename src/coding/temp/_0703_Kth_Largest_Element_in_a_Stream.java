/**
 *  @author Yunxiang He
 *  @date 07/31/2018
 */

package coding.temp;

import java.util.PriorityQueue;

/*

Design a class to find the kth largest element in a stream. 
Note that it is the kth largest element in the sorted order, not the kth distinct element.

Your KthLargest class will have a constructor which accepts an integer k and an integer array nums, which contains initial elements from the stream. 
For each call to the method KthLargest.add, return the element representing the kth largest element in the stream.


Example:
    int k = 3;
    int[] arr = [4,5,8,2];
    KthLargest kthLargest = new KthLargest(3, arr);
    kthLargest.add(3);   // returns 4
    kthLargest.add(5);   // returns 5
    kthLargest.add(10);  // returns 5
    kthLargest.add(9);   // returns 8
    kthLargest.add(4);   // returns 8


Note: 
    You may assume that nums' length ≥ k-1 and k ≥ 1.

*/

public class _0703_Kth_Largest_Element_in_a_Stream {

    public static void main(String[] args) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.add(3);
        pq.add(4);
        pq.add(6);
        pq.add(3);
        pq.add(1);
        pq.add(5);
        while (pq.size() > 0) {
            System.out.println(pq.remove());
        }
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Default min-heap
    // The priority queue keeps the most k largest elements
    private PriorityQueue<Integer> pq;
    private int k;

    public _0703_Kth_Largest_Element_in_a_Stream(int k, int[] nums) {
        this.k = k;
        pq = new PriorityQueue<>();
        for (int num : nums) {
            add(num);
        }
    }

    public int add(int val) {
        pq.add(val);
        if (pq.size() == k + 1) {
            pq.poll();
        }
        return pq.peek();
    }

}
