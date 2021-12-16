package questions._04_linkedList;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;
import java.util.TreeMap;

/*

Design a max stack data structure that supports the stack operations and supports finding the stack's maximum element.

Implement the MaxStack class:
    MaxStack() Initializes the stack object.
    void push(int x) Pushes element x onto the stack.
    int pop() Removes the element on top of the stack and returns it.
    int top() Gets the element on the top of the stack without removing it.
    int peekMax() Retrieves the maximum element in the stack without removing it.
    int popMax() Retrieves the maximum element in the stack and removes it. If there is more than one maximum element, only remove the top-most one.


Example 1:
    Input
        ["MaxStack", "push", "push", "push", "top", "popMax", "top", "peekMax", "pop", "top"]
        [[], [5], [1], [5], [], [], [], [], [], []]
    Output
        [null, null, null, null, 5, 5, 1, 5, 1, 5]
    Explanation
        MaxStack stk = new MaxStack();
        stk.push(5);   // [5] the top of the stack and the maximum number is 5.
        stk.push(1);   // [5, 1] the top of the stack is 1, but the maximum is 5.
        stk.push(5);   // [5, 1, 5] the top of the stack is 5, which is also the maximum, because it is the top most one.
        stk.top();     // return 5, [5, 1, 5] the stack did not change.
        stk.popMax();  // return 5, [5, 1] the stack is changed now, and the top is different from the max.
        stk.top();     // return 1, [5, 1] the stack did not change.
        stk.peekMax(); // return 5, [5, 1] the stack did not change.
        stk.pop();     // return 1, [5] the top of the stack and the max element is now 5.
        stk.top();     // return 5, [5] the stack did not change.


Constraints:
    -10^7 <= x <= 10^7
    At most 10^4 calls will be made to push, pop, top, peekMax, and popMax.
    There will be at least one element in the stack when pop, top, peekMax, or popMax is called.


Follow up:
    Could you come up with a solution that supports O(1) for each top call and O(log n) for each other call?

*/
public class _0716_Max_Stack {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    class MaxStack {

        private class Node {
            int val;
            Node left;
            Node right;
            Node pre;

            public Node(int val) {
                this.val = val;
                this.pre = this;
                this.left = this;
                this.right = this;
            }
        }

        private Node head;
        private TreeMap<Integer, Node> map;

        public MaxStack() {
            head = new Node(0);
            map = new TreeMap<>();
        }

        public void push(int x) {
            Node node = new Node(x);
            // Insert stack
            Node right = head.right;
            head.right = node;
            node.left = head;
            right.left = node;
            node.right = right;

            // Insert map
            if (!map.containsKey(x)) {
                map.put(x, node);
            } else {
                Node tempHead = map.get(x);
                Node tail = tempHead.pre;
                node.pre = tail;
                tempHead.pre = node;
            }
        }

        public int pop() {
            Node target = head.right;
            delete(updateMap(target.val));
            return target.val;
        }

        public int top() {
            return head.right.val;
        }

        public int peekMax() {
            return map.lastKey();
        }

        public int popMax() {
            int max = peekMax();
            delete(updateMap(max));
            return max;
        }

        private void delete(Node node) {
            Node left = node.left;
            Node right = node.right;
            left.right = right;
            right.left = left;
        }

        private Node updateMap(int key) {
            Node target = map.get(key);
            if (target.pre == target) {
                map.remove(target.val);
            } else {
                Node temp = target.pre;
                target.pre = temp.pre;
                target = temp;
            }
            return target;
        }

    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    class Solution {

        private class Node {
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