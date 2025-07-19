package leetcode;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/*

Given an integer array nums and an integer k, return the k most frequent elements. You may return the answer in any order.


Example 1:
    Input: nums = [1,1,1,2,2,3], k = 2
    Output: [1,2]
Example 2:
    Input: nums = [1], k = 1
    Output: [1]


Constraints:
    1 <= nums.length <= 10^5
    -10^4 <= nums[i] <= 10^4
    k is in the range [1, the number of unique elements in the array].
    It is guaranteed that the answer is unique.


Follow up: Your algorithm's time complexity must be better than O(n log n), where n is the array's size.

*/
public class _0347_Top_K_Frequent_Elements {

    class Solution {
        public int[] topKFrequent(int[] nums, int k) {
            Map<Integer, Integer> freq = new HashMap<>();
            for (int num : nums) {
                freq.put(num, freq.getOrDefault(num, 0) + 1);
            }
            PriorityQueue<Integer> minHeap = new PriorityQueue<>(Comparator.comparingInt(freq::get));
            for (int num : freq.keySet()) {
                minHeap.offer(num);
                if (minHeap.size() > k) {
                    minHeap.poll();
                }
            }

            return minHeap.stream().mapToInt(Integer::valueOf).toArray();
        }
    }
}