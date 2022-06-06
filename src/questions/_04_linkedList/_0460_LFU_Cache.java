package questions._04_linkedList;

import common.DoubleLinkedList;

import java.util.HashMap;
import java.util.Map;

/*

Design and implement a data structure for a Least Frequently Used (LFU) cache.

Implement the LFUCache class:
    LFUCache(int capacity) Initializes the object with the capacity of the data structure.
    int get(int key) Gets the value of the key if the key exists in the cache. Otherwise, returns -1.
    void put(int key, int value) Update the value of the key if present, or inserts the key if not already present.
        When the cache reaches its capacity, it should invalidate and remove the least frequently used key before inserting a new item.
        For this problem, when there is a tie (i.e., two or more keys with the same frequency), the least recently used key would be invalidated.
    To determine the least frequently used key, a use counter is maintained for each key in the cache.
        The key with the smallest use counter is the least frequently used key.
When a key is first inserted into the cache, its use counter is set to 1 (due to the put operation).
The use counter for a key in the cache is incremented either a get or put operation is called on it.

The functions get and put must each run in O(1) average time complexity.


Example 1:
    Input
        ["LFUCache", "put", "put", "get", "put", "get", "get", "put", "get", "get", "get"]
        [[2], [1, 1], [2, 2], [1], [3, 3], [2], [3], [4, 4], [1], [3], [4]]
    Output
        [null, null, null, 1, null, -1, 3, null, -1, 3, 4]
    Explanation
        // cnt(x) = the use counter for key x
        // cache=[] will show the last used order for tiebreakers (leftmost element is  most recent)
        LFUCache lfu = new LFUCache(2);
        lfu.put(1, 1);   // cache=[1,_], cnt(1)=1
        lfu.put(2, 2);   // cache=[2,1], cnt(2)=1, cnt(1)=1
        lfu.get(1);      // return 1
                         // cache=[1,2], cnt(2)=1, cnt(1)=2
        lfu.put(3, 3);   // 2 is the LFU key because cnt(2)=1 is the smallest, invalidate 2.
                         // cache=[3,1], cnt(3)=1, cnt(1)=2
        lfu.get(2);      // return -1 (not found)
        lfu.get(3);      // return 3
                         // cache=[3,1], cnt(3)=2, cnt(1)=2
        lfu.put(4, 4);   // Both 1 and 3 have the same cnt, but 1 is LRU, invalidate 1.
                         // cache=[4,3], cnt(4)=1, cnt(3)=2
        lfu.get(1);      // return -1 (not found)
        lfu.get(3);      // return 3
                         // cache=[3,4], cnt(4)=1, cnt(3)=3
        lfu.get(4);      // return 4
                         // cache=[4,3], cnt(4)=2, cnt(3)=3


Constraints:
    0 <= capacity <= 10^4
    0 <= key <= 10^5
    0 <= value <= 10^9
    At most 2 * 10^5 calls will be made to get and put.

*/

public class _0460_LFU_Cache {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    class Solution {
        private final int capacity;
        private Map<Integer, DoubleLinkedList<Integer, Integer>> cache;
        private Map<Integer, DoubleLinkedList<Integer, Integer>> freqMap;
        private DoubleLinkedList<Integer, Integer> head;

        public Solution(int capacity) {
            this.capacity = capacity;
            cache = new HashMap<>();
            freqMap = new HashMap<>();
            head = new DoubleLinkedList<>(-1, -1, -1);
            head.next = head;
            head.pre = head;
        }

        public int get(int key) {
            if (cache.containsKey(key)) {
                DoubleLinkedList<Integer, Integer> node = cache.get(key);
                update(node);
                return node.val;
            } else {
                return -1;
            }
        }

        public void put(int key, int value) {
            if (capacity != 0) {
                DoubleLinkedList<Integer, Integer> node;
                if (cache.containsKey(key)) {
                    node = cache.get(key);
                    node.val = value;
                } else {
                    node = new DoubleLinkedList<>(key, value);
                    if (cache.size() == capacity) {
                        removeTail();
                    }
                    insert(node, head);
                    cache.put(key, node);
                }
                update(node);
            }
        }

        private void update(DoubleLinkedList<Integer, Integer> node) {
            // update freqMap
            if (freqMap.get(node.freq) == node) { // node == head of node.freq
                if (node.freq != node.next.freq) { // single node
                    freqMap.remove(node.freq);
                } else { // non-single node, update head
                    freqMap.put(node.freq, node.next);
                }
            }
            // find the right node to insert before
            node.freq++;
            DoubleLinkedList<Integer, Integer> insertBefore = freqMap.get(node.freq) != null ? freqMap.get(node.freq) : freqMap.get(node.freq - 1);
            if (insertBefore != null) {
                insert(node, insertBefore);
            }
            freqMap.put(node.freq, node);
        }

        private void insert(DoubleLinkedList<Integer, Integer> node,
                            DoubleLinkedList<Integer, Integer> insertBefore) {
            // Connect left and right
            if (node.pre != null) {
                node.pre.next = node.next;
            }
            if (node.next != null) {
                node.next.pre = node.pre;
            }
            // Insert
            insertBefore.pre.next = node;
            node.pre = insertBefore.pre;
            node.next = insertBefore;
            insertBefore.pre = node;
        }

        private void removeTail() {
            DoubleLinkedList<Integer, Integer> tail = head.pre;
            tail.pre.next = head;
            head.pre = tail.pre;
            cache.remove(tail.key);
            if (freqMap.get(tail.freq) == tail) {
                freqMap.remove(tail.freq);
            }
        }
    }

}
