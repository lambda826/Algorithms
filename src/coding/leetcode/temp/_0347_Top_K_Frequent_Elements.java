/**
 *  @author Yunxiang He
 *  @date 02/19/2019
 */

package coding.leetcode.temp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/*

Given a non-empty array of integers, return the k most frequent elements.


Example 1:
    Input: nums = [1,1,1,2,2,3], k = 2
    Output: [1,2]

Example 2:
    Input: nums = [1], k = 1
    Output: [1]


Note:
    You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
    Your algorithm's time complexity must be better than O(n log n), where n is the array's size.

*/

public class _0347_Top_K_Frequent_Elements {

    public static void main(String[] args) {
        int[] nums = { 1, 1, 1, 2, 2, 2, 3 };
        _0347_Top_K_Frequent_Elements test = new _0347_Top_K_Frequent_Elements();
        test.topKFrequent(nums, 2);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // heap
    public List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        PriorityQueue<Integer> minHeap = new PriorityQueue<>((a, b) -> map.get(a) - map.get(b));
        for (int key : map.keySet()) {
            minHeap.offer(key);
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }
        return new ArrayList<>(minHeap);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // bucket
    public List<Integer> topKFrequent2(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        List<Integer>[] frequency = new List[nums.length + 1];
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        for (int key : map.keySet()) {
            if (frequency[map.get(key)] == null) {
                frequency[map.get(key)] = new ArrayList<>();
            }
            frequency[map.get(key)].add(key);
        }
        List<Integer> list = new ArrayList<>();
        for (int i = frequency.length - 1; i >= 0 && list.size() < k; --i) {
            if (frequency[i] != null) {
                list.addAll(frequency[i]);
            }
        }
        return list;
    }

}
