package questions._04_linkedList;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/*

Design a data structure to store the strings' count with the ability to return the strings with minimum and maximum counts.

Implement the AllOne class:
    AllOne() Initializes the object of the data structure.
    inc(String key) Increments the count of the string key by 1. If key does not exist in the data structure, insert it with count 1.
    dec(String key) Decrements the count of the string key by 1. If the count of key is 0 after the decrement, remove it from the data structure.
        It is guaranteed that key exists in the data structure before the decrement.
    getMaxKey() Returns one of the keys with the maximal count. If no element exists, return an empty string "".
    getMinKey() Returns one of the keys with the minimum count. If no element exists, return an empty string "".
Note that each function must run in O(1) average time complexity.


Example 1:
    Input
        ["AllOne", "inc", "inc", "getMaxKey", "getMinKey", "inc", "getMaxKey", "getMinKey"]
        [[], ["hello"], ["hello"], [], [], ["leet"], [], []]
    Output
        [null, null, null, "hello", "hello", null, "hello", "leet"]
    Explanation
        AllOne allOne = new AllOne();
        allOne.inc("hello");
        allOne.inc("hello");
        allOne.getMaxKey(); // return "hello"
        allOne.getMinKey(); // return "hello"
        allOne.inc("leet");
        allOne.getMaxKey(); // return "hello"
        allOne.getMinKey(); // return "leet"


Constraints:
    1 <= key.length <= 10
    key consists of lowercase English letters.
    It is guaranteed that for each call to dec, key is existing in the data structure.
    At most 5 * 10^4 calls will be made to inc, dec, getMaxKey, and getMinKey.

*/
public class _0432_All_O_one_Data_Structure {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    class AllOne {

        class Node {
            int count;
            TreeSet<String> keys;
            Node pre;
            Node next;

            Node() {
            }

            Node(int count) {
                this.count = count;
                this.keys = new TreeSet<>();
            }
        }

        Map<String, Node> map;
        Node max;
        Node min;

        /** Initialize your data structure here. */
        public AllOne() {
            map = new HashMap<>();
            min = new Node();
            min.count = Integer.MIN_VALUE;
            max = new Node();
            max.count = Integer.MAX_VALUE;
            min.next = max;
            max.pre = min;
        }

        /** Inserts a new key <Key> with value 1. Or increments an existing key by 1. */
        public void inc(String key) {
            if (map.containsKey(key)) {
                Node node = map.get(key);
                int count = node.count;
                Node newNode;
                if (node.next.count == node.count + 1) {
                    newNode = node.next;
                } else {
                    newNode = new Node(count + 1);
                    insertAfter(node, newNode);
                }
                newNode.keys.add(key);
                map.put(key, newNode);
                if (node.keys.isEmpty()) {
                    remove(node);
                }
                node.keys.remove(key);
                if (node.keys.isEmpty()) {
                    remove(node);
                }
            } else {
                Node node;
                if (min.next.count == 1) {
                    node = min.next;
                } else {
                    node = new Node(1);
                    insertAfter(min, node);
                }
                node.keys.add(key);
                map.put(key, node);
            }

        }

        /** Decrements an existing key by 1. If Key's value is 1, remove it from the data structure. */
        public void dec(String key) {
            Node node = map.get(key);
            int count = node.count;
            // Update map
            if (node.count == 1) {
                map.remove(key);
            } else {
                Node newNode;
                if (node.pre.count == node.count - 1) {
                    newNode = node.pre;
                } else {
                    newNode = new Node(count - 1);
                    insertAfter(node.pre, newNode);
                }
                newNode.keys.add(key);
                map.put(key, newNode);
            }
            node.keys.remove(key);
            if (node.keys.isEmpty()) {
                remove(node);
            }
        }

        /** Returns one of the keys with maximal value. */
        public String getMaxKey() {
            if (map.size() == 0) {
                return "";
            } else {
                return max.pre.keys.first();
            }
        }

        /** Returns one of the keys with Minimal value. */
        public String getMinKey() {
            if (map.size() == 0) {
                return "";
            } else {
                return min.next.keys.first();
            }
        }

        private void insertAfter(Node pre, Node node) {
            node.next = pre.next;
            pre.next = node;
            node.next.pre = node;
            node.pre = pre;
        }

        private void remove(Node node) {
            node.pre.next = node.next;
            node.next.pre = node.pre;
            node.next = null;
            node.pre = null;
        }

    }
}
