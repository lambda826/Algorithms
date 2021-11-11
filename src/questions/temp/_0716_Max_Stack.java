package questions.temp;

/*



 */

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;
import java.util.TreeMap;

public class _0716_Max_Stack {


    private static class Solution {

        private static class Node {
            int val;
            Node pre;
            Node next;

            public Node(int val) {
                this.val = val;
            }
        }

        private TreeMap<Integer, Deque<Node>> max;
        private Node tail;

        public Solution() {
            tail = new Node(0);
            max = new TreeMap<>();
        }

        public void push(int x) {
            Node newTail = new Node(x);
            tail.next = newTail;
            newTail.pre = tail;
            tail = newTail;

            max.putIfAbsent(x, new ArrayDeque<>());
            max.get(x).offerLast(newTail);
        }

        public int pop() {
            Deque<Node> nodes = max.get(tail.val);
            nodes.pollLast();
            if (nodes.size() == 0) {
                max.remove(tail.val);
            }

            int res = tail.val;
            Node pre = tail.pre;
            pre.next = null;
            tail.pre = null;
            tail = pre;
            return res;
        }

        public int top() {
            return tail.val;
        }

        public int peekMax() {
            return max.get(max.lastKey()).peekLast().val;
        }

        public int popMax() {
            Map.Entry<Integer, Deque<Node>> entry = max.lastEntry();
            int key = entry.getKey();
            Deque<Node> nodes = entry.getValue();
            Node node = nodes.pollLast();
            if (nodes.size() == 0) {
                max.remove(key);
            }

            if (node == tail) {
                tail = node.pre;
            }
            Node pre = node.pre;
            Node next = node.next;
            pre.next = next;
            if (next != null) {
                next.pre = pre;
            }
            return key;
        }
    }

}