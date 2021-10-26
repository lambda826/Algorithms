package coding.leetcode._04_linkedList;

import java.util.HashMap;

/*

A linked list of length n is given such that each node contains an additional random pointer, which could point to any node in the list, or null.

Construct a deep clone of the list. The deep clone should consist of exactly n brand new nodes, where each new node has its value set to the value of its corresponding original node.
Both the next and random pointer of the new nodes should point to new nodes in the copied list such that the pointers in the original list and copied list represent the same list state.
None of the pointers in the new list should point to nodes in the original list.

For example, if there are two nodes X and Y in the original list, where X.random --> Y, then for the corresponding two nodes x and y in the copied list, x.random --> y.
Return the head of the copied linked list.

The linked list is represented in the input/output as a list of n nodes. Each node is represented as a pair of [val, random_index] where:
    val: an integer representing Node.val
    random_index: the index of the node (range from 0 to n-1) that the random pointer points to, or null if it does not point to any node.
Your code will only be given the head of the original linked list.


Example 1:
    Input:
        head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
    Output:
        [[7,null],[13,0],[11,4],[10,2],[1,0]]
Example 2:
    Input:
        head = [[1,1],[2,1]]
    Output:
        [[1,1],[2,1]]
Example 3:
    Input:
        head = [[3,null],[3,0],[3,null]]
    Output:
        [[3,null],[3,0],[3,null]]
Example 4:
    Input:
        head = []
    Output:
        []
    Explanation:
        The given linked list is empty (null pointer), so return null.


Constraints:
    0 <= n <= 1000
    -10000 <= Node.val <= 10000
    Node.random is null or is pointing to some node in the linked list.

*/

public class _0138_Copy_List_with_Random_Pointer {

    class Node {
        public int val;
        public Node next;
        public Node random;

        public Node(int val) {
            this.val = val;
        }

        public Node(int _val, Node _next, Node _random) {
            val = _val;
            next = _next;
            random = _random;
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Intertwine the original linked list and keep every cloned node next to its original node one by one;
    // This interleaving of old and new nodes allows us to solve this problem without any extra space.
    class Solution {

        public Node cloneRandomList(Node head) {
            if (head == null) {
                return null;
            }

            Node curr = head;
            Node cloneCurr;
            Node next;
            Node cloneNext;

            // Intertwine
            while (curr != null) {
                cloneCurr = new Node(curr.val); // Clone node
                next = curr.next;
                curr.next = cloneCurr; // Append cloned node to the original current node.
                cloneCurr.next = next; // Append the original next node to the cloned node.
                curr = next;
            }

            // Establish random pointer of the cloned node.
            curr = head;
            while (curr != null) {
                curr.next.random = (curr.random == null ? null : curr.random.next);
                curr = curr.next.next; // Move to next original node
            }

            Node res = head.next;
            // Detach cloned nodes from the originals.
            curr = head;
            cloneCurr = head.next;
            while (curr != null) {
                next = curr.next.next; // Original next node of the current original node.
                cloneNext = (cloneCurr.next == null ? null : cloneCurr.next.next); // Cloned next node of the current cloned node.
                curr.next = next; // Restore the link between original current node and original next node.
                cloneCurr.next = cloneNext; // Link append cloned next node to the cloned current node.
                curr = next;
                cloneCurr = cloneNext;
            }
            return res;
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    class Solution_HashMap {

        public Node cloneRandomList(Node node) {
            HashMap<Node, Node> map = new HashMap<>();
            if (node == null) {
                return null;
            } else if (!map.containsKey(node)) {
                Node cloned = new Node(node.val);
                map.put(node, cloned);
                cloned.next = cloneRandomList(node.next);
                cloned.random = cloneRandomList(node.random);
            }
            return map.get(node);
        }
    }

}