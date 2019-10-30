/**
 *  @author Yunxiang He
 *  @date 07/04/2018
 */

package coding.temp;

import java.util.PriorityQueue;

/*

Median is the middle value in an ordered integer list. 
If the size of the list is even, there is no middle value. So the median is the mean of the two middle value.


For example,
    [2,3,4], the median is 3
    [2,3], the median is (2 + 3) / 2 = 2.5

Design a data structure that supports the following two operations:
    void addNum(int num) - Add a integer number from the data stream to the data structure.
    double findMedian() - Return the median of all elements so far.
 

Example:
    addNum(1)
    addNum(2)
    findMedian() -> 1.5
    addNum(3)
    findMedian() -> 2
 

Follow up:
    If all integer numbers from the stream are between 0 and 100, how would you optimize it?
        Array[100]
    If 99% of all integer numbers from the stream are between 0 and 100, how would you optimize it?
        (indexed)BST + Array[100] + (indexed)BST
*/

public class _0295_Find_Median_from_Data_Stream {

    public static void main(String[] args) {
        _0295_Find_Median_from_Data_Stream test = new _0295_Find_Median_from_Data_Stream();
        test.addNum(1);
        test.addNum(2);
        test.findMedian();
        test.addNum(3);
        test.findMedian();
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private PriorityQueue<Integer> minHeap;
    private PriorityQueue<Integer> maxHeap;

    public _0295_Find_Median_from_Data_Stream() {
        minHeap = new PriorityQueue<>();
        maxHeap = new PriorityQueue<>((a, b) -> (b - a));
    }

    public void addNum(int num) {
        maxHeap.offer(num);
        minHeap.offer(maxHeap.poll());
        if (maxHeap.size() < minHeap.size()) {
            maxHeap.offer(minHeap.poll());
        }
    }

    public double findMedian() {
        if (maxHeap.size() > minHeap.size()) {
            return maxHeap.peek();
        } else {
            return (double) (maxHeap.peek() + minHeap.peek()) / 2;
        }
    }
}
