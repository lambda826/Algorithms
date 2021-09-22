package coding.leetcode.temp;

import common.DoubleLinkedList;

import java.util.HashMap;
import java.util.Map;

/*

Design and implement a data structure for Least Frequently Used (LFU) cache. 
It should support the following operations: get and put.

get(key) 
	- Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
put(key, value) 
	- Set or insert the value if the key is not already present. 
	  When the cache reaches its capacity, it should invalidate the least frequently used item before inserting a new item. 
	  For the purpose of this problem, when there is a tie (i.e., two or more keys that have the same frequency), the least recently used key would be evicted.


Example:
	LFUCache cache = new LFUCache(2);
	cache.put(1,1);
	cache.put(2,2);
	cache.get(1);          // returns 1
	cache.put(3,3);        // evicts key 2
	cache.get(2);          // returns -1 (not found)
	cache.get(3);          // returns 3.
	cache.put(4,4);        // evicts key 1.
	cache.get(1);          // returns -1 (not found)
	cache.get(3);          // returns 3 
	cache.get(4);          // returns 4


Follow up:
	Could you do both operations in O(1) time complexity?

*/

public class _0460_LFU_Cache {

    public static void main(String[] args) {
        _0460_LFU_Cache cache = new _0460_LFU_Cache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        cache.get(1); // returns 1
        cache.put(3, 3); // evicts key 2
        cache.get(2); // returns -1 (not found)
        cache.get(3); // returns 3.
        cache.put(4, 4); // evicts key 1.
        cache.get(1); // returns -1 (not found)
        cache.get(3); // returns 3 
        cache.get(4); // returns 4
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private final int capacity;
    private Map<Integer, DoubleLinkedList<Integer, Integer>> cache;
    private Map<Integer, DoubleLinkedList<Integer, Integer>> freqMap; // order by frequency DESC, 5->4->4->2->2->1
    private DoubleLinkedList<Integer, Integer> head;

    public _0460_LFU_Cache(int capacity) {
        this.capacity = capacity;
        cache = new HashMap<>();
        freqMap = new HashMap<>();
        head = new DoubleLinkedList<>(-1, -1, -1);
        head.next = head;
        head.pre = head;
    }

    public int get(int key) {
        int val = -1;
        if (cache.containsKey(key)) {
            DoubleLinkedList<Integer, Integer> node = cache.get(key);
            val = node.val;
            update(node);
        }
        return val;
    }

    public void put(int key, int value) {
        if (capacity != 0) {
            DoubleLinkedList<Integer, Integer> node = null;
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
        // update freMap
        if (freqMap.get(node.freq) == node) { // node == head of node.freq
            if (node.freq != node.next.freq) { // single node
                freqMap.remove(node.freq);
            } else { // non-single node, update head
                freqMap.put(node.freq, node.next);
            }
        }
        // find the right node to insert before
        node.freq++;
        DoubleLinkedList<Integer, Integer> insert_before_node = freqMap.get(node.freq) != null ? freqMap.get(node.freq) : freqMap.get(node.freq - 1);
        if (insert_before_node != null) {
            insert(node, insert_before_node);
        }
        freqMap.put(node.freq, node);
    }

    private void insert(DoubleLinkedList<Integer, Integer> node, DoubleLinkedList<Integer, Integer> insert_before_node) {
        // Connect left and right
        if (node.pre != null) {
            node.pre.next = node.next;
        }
        if (node.next != null) {
            node.next.pre = node.pre;
        }
        // Insert
        insert_before_node.pre.next = node;
        node.pre = insert_before_node.pre;
        node.next = insert_before_node;
        insert_before_node.pre = node;
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
