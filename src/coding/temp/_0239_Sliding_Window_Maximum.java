/**
 *  @author Yunxiang He
 *  @date 02/13/2019
 */

package coding.temp;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;

/*

Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right. 
You can only see the k numbers in the window. 
Each time the sliding window moves right by one position. 
Return the max sliding window.


Example:
    Input: nums = [1,3,-1,-3,5,3,6,7], and k = 3
    Output: [3,3,5,5,6,7] 
    Explanation: 
    
    Window position                Max
    ---------------               -----
    [1  3  -1] -3  5  3  6  7       3
     1 [3  -1  -3] 5  3  6  7       3
     1  3 [-1  -3  5] 3  6  7       5
     1  3  -1 [-3  5  3] 6  7       5
     1  3  -1  -3 [5  3  6] 7       6
     1  3  -1  -3  5 [3  6  7]      7
 

Note: 
    You may assume k is always valid, 1 ≤ k ≤ input array's size for non-empty array.


Follow up:
    Could you solve it in linear time?

*/

public class _0239_Sliding_Window_Maximum {

    public static void main(String[] args) {
        new _0239_Sliding_Window_Maximum().maxSlidingWindow_Heap(new int[] { 1, 3, -1, -3, 5, 3, 6, 7 }, 3);
        System.out.println();
        new _0239_Sliding_Window_Maximum().maxSlidingWindow(new int[] { 1, 3, -1, -3, 5, 3, 6, 7 }, 3);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums.length == 0 || nums == null) {
            return new int[] {};
        }
        int[] res = new int[nums.length - k + 1];
        // Store index, the head stores the max of the current window
        Deque<Integer> deque = new ArrayDeque<>();
        int right = 0;
        int left = 0;
        while (right < k) {
            while (!deque.isEmpty() && nums[right] > nums[deque.peekLast()]) {
                deque.pollLast();
            }
            deque.offerLast(right);
            ++right;
        }
        res[left] = nums[deque.peekFirst()];
        while (right < nums.length) {
            if (left == deque.peekFirst()) {
                deque.pollFirst();
            }
            while (!deque.isEmpty() && nums[right] > nums[deque.peekLast()]) {
                deque.pollLast();
            }
            deque.offerLast(right++);
            res[++left] = nums[deque.peekFirst()];
        }
        return res;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int[] maxSlidingWindow_Heap(int[] nums, int k) {
        if (nums.length == 0 || nums == null) {
            return new int[] {};
        }
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> (b - a));
        int[] res = new int[nums.length - k + 1];
        int index = 0;
        for (int i = 0; i < k; i++) {
            pq.add(nums[i]);
        }
        res[index++] = pq.peek();
        for (int i = k; i < nums.length; i++) {
            pq.remove(nums[i - k]);
            pq.add(nums[i]);
            res[index++] = pq.peek();
        }
        return res;
    }

}
