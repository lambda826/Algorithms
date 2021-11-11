package questions._06_sorting.bucketSorting;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/*

Given an integer array nums and an integer k, return the k most frequent elements. You may return the answer in any order.


Example 1:
    Input:
        nums = [1,1,1,2,2,3],
        k = 2
    Output:
        [1,2]
Example 2:
    Input:
        nums = [1],
        k = 1
    Output:
        [1]


Constraints:
    1 <= nums.length <= 10^5
    k is in the range [1, the number of unique elements in the array].
    It is guaranteed that the answer is unique.

*/

public class _0347_Top_K_Frequent_Elements {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // bucket
    class Solution_BucketSorting {
        public int[] topKFrequent(int[] nums, int k) {
            Map<Integer, Integer> frequency = new HashMap<>();
            for (int num : nums) {
                frequency.put(num, frequency.getOrDefault(num, 0) + 1);
            }

            List<Integer>[] buckets = new List[nums.length + 1];
            for (int num : frequency.keySet()) {
                if (buckets[frequency.get(num)] == null) {
                    buckets[frequency.get(num)] = new ArrayList<>();
                }
                buckets[frequency.get(num)].add(num);
            }
            int[] res = new int[k];
            int i = buckets.length - 1;
            while (k > 0) {
                while (buckets[i] == null) {
                    --i;
                }
                for (int num : buckets[i]) {
                    res[k - 1] = num;
                    --k;
                }
                --i;
            }
            return res;
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    class Solution_Heap {
        public int[] topKFrequent(int[] nums, int k) {
            Map<Integer, Integer> frequency = new HashMap<>();
            for (int num : nums) {
                frequency.put(num, frequency.getOrDefault(num, 0) + 1);
            }
            PriorityQueue<Integer> minHeap = new PriorityQueue<>(Comparator.comparingInt(frequency::get));
            for (int num : frequency.keySet()) {
                minHeap.offer(num);
                if (minHeap.size() > k) {
                    minHeap.poll();
                }
            }
            return minHeap.stream().mapToInt(Integer::valueOf).toArray();
        }
    }

}