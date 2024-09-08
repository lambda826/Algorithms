/**
 * @author Yunxiang He
 * @date 07/26/2018
 */

package questions.temp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/*

Given a non-empty list of words, return the k most frequent elements.

Your answer should be sorted by frequency from highest to lowest. 
If two words have the same frequency, then the word with the lower alphabetical order comes first.


Example 1:
    Input: 
        ["i", "love", "leetcode", "i", "love", "coding"], k = 2
    Output: 
        ["i", "love"]
    Explanation: 
        "i" and "love" are the two most frequent words.
        Note that "i" comes before "love" due to a lower alphabetical order.

Example 2:
    Input: 
        ["the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"], k = 4
    Output: 
        ["the", "is", "sunny", "day"]
    Explanation: 
        "the", "is", "sunny" and "day" are the four most frequent words,
        with the number of occurrence being 4, 3, 2 and 1 respectively.

Note:
    You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
    Input words contain only lowercase letters.


Follow up:
    Try to solve it in O(n log k) time and O(n) extra space.

*/

public class _0692_Top_K_Frequent_Words {

    public static void main(String[] args) {
        String[] words = { "i", "love", "leetcode", "i", "love", "coding" };
        _0692_Top_K_Frequent_Words test = new _0692_Top_K_Frequent_Words();
        System.out.println(test.topKFrequent(words, 1));
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // heap
    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> map = new HashMap<>();
        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }
        PriorityQueue<String> pq = new PriorityQueue<>((a, b) -> map.get(a) == map.get(b) ? a.compareTo(b) : map.get(b) - map.get(a));
        pq.addAll(map.keySet());
        List<String> res = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            res.add(pq.poll());
        }
        return res;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // bucket
    public List<String> topKFrequent2(String[] words, int k) {
        PriorityQueue<String>[] arr = new PriorityQueue[words.length + 1];
        Map<String, Integer> freqMap = new HashMap<>();
        for (String word : words) {
            freqMap.put(word, freqMap.getOrDefault(word, 0) + 1);
        }

        for (String key : freqMap.keySet()) {
            if (arr[freqMap.get(key)] == null) {
                arr[freqMap.get(key)] = new PriorityQueue<>();
            }
            arr[freqMap.get(key)].add(key);
        }

        List<String> res = new ArrayList<>();
        for (int i = arr.length - 1; i >= 0; --i) {
            if (arr[i] != null) {
                while (!arr[i].isEmpty()) {
                    res.add(arr[i].poll());
                    if (res.size() == k) {
                        return res;
                    }
                }
            }
        }
        return res;
    }
}
