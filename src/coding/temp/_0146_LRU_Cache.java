/**
 *  @author Yunxiang He
 *  @date 06/22/2018
 */

package coding.temp;

import common.DoubleLinkedList;

import java.util.HashMap;
import java.util.Map;

/*

Design and implement a data structure for Least Recently Used (LRU) cache. 
It should support the following operations: get and put.

get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
put(key, value) - Set or insert the value if the key is not already present. 
When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.


Example:
    LRUCache cache = new LRUCache(2);
    cache.put(1, 1);
    cache.put(2, 2);
    cache.get(1);       // returns 1
    cache.put(3, 3);    // evicts key 2
    cache.get(2);       // returns -1 (not found)
    cache.put(4, 4);    // evicts key 1
    cache.get(1);       // returns -1 (not found)
    cache.get(3);       // returns 3
    cache.get(4);       // returns 4


Follow up:
    Could you do both operations in O(1) time complexity?

*/

public class _0146_LRU_Cache {

    private final int capacity;
    private Map<Integer, DoubleLinkedList<Integer, Integer>> cache;
    private DoubleLinkedList<Integer, Integer> head;

    public _0146_LRU_Cache(int capacity) {
        this.capacity = capacity;
        cache = new HashMap<>();
        head = new DoubleLinkedList<>(-1, -1);
        head.next = head;
        head.pre = head;
    }

    public int get(int key) {
        int val = -1;
        if (cache.containsKey(key)) {
            DoubleLinkedList<Integer, Integer> node = cache.get(key);
            val = node.val;
            moveToHead(node);
        }
        return val;
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
                cache.put(key, node);
            }
            moveToHead(node);
        }
    }

    private void moveToHead(DoubleLinkedList<Integer, Integer> node) {
        // Connect pre and next of node;
        if (node.pre != null) {
            node.pre.next = node.next;
        }
        if (node.next != null) {
            node.next.pre = node.pre;
        }
        // Insert node between head and head.next
        node.pre = head;
        node.next = head.next;
        node.next.pre = node;
        head.next = node;
    }

    private void removeTail() {
        DoubleLinkedList<Integer, Integer> tail = head.pre;
        tail.pre.next = head;
        head.pre = tail.pre;
        cache.remove(tail.key);
    }

}