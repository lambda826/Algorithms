package coding.leetcode._04_linkedList;

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

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Key points:
    //      1. Define a DoubledLinked Node class to hold key and value pair;
    //      2. Declare a head node for convenient list operation;
    //      3. Apart from get/put methods, we need another two methods to operate the links:
    //          3.1 Delete tail (eviction);
    //          3.2 Move the latest used node to the head.
    class LRUCache {

        private class Node {
            int key;
            int value;
            Node pre;
            Node next;

            public Node(int key, int value) {
                this.key = key;
                this.value = value;
                this.pre = this;
                this.next = this;
            }

        }

        private int capacity;
        private Node head;
        private Map<Integer, Node> map;

        public LRUCache(int capacity) {
            this.capacity = capacity;
            head = new Node(-1, -1);
            map = new HashMap<>();
            head.pre = head;
            head.next = head;
        }

        public int get(int key) {
            int value = -1;
            if (map.containsKey(key)) {
                value = map.get(key).value;
                moveToHead(map.get(key));
            }
            return value;
        }

        public void put(int key, int value) {
            if (capacity != 0) {
                Node node;
                if (map.containsKey(key)) {
                    node = map.get(key);
                    node.value = value;
                } else {
                    node = new Node(key, value);
                    if (map.size() == capacity) {
                        deleteTail();
                    }
                    map.put(key, node);
                }
                moveToHead(node);
            }
        }

        private void moveToHead(Node node) {
            // Connect node.pre and node.next;
            node.next.pre = node.pre;
            node.pre.next = node.next;
            // Insert node between head and head.next
            head.next.pre = node;
            node.next = head.next;
            node.pre = head;
            head.next = node;
        }

        private void deleteTail() {
            Node tail = head.pre;
            tail.pre.next = head;
            head.pre = tail.pre;
            map.remove(tail.key);
        }
    }

}