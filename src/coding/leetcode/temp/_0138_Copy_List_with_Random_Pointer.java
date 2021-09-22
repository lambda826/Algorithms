package coding.leetcode.temp;

import java.util.HashMap;

/*

A linked list is given such that each node contains an additional random pointer which could point to any node in the list or null.
Return a deep copy of the list.
 

Example 1:
    Input:
    {"$id":"1","next":{"$id":"2","next":null,"random":{"$ref":"2"},"val":2},"random":{"$ref":"2"},"val":1}
    Explanation:
    Node 1's value is 1, both of its next and random pointer points to Node 2.
    Node 2's value is 2, its next pointer points to null and its random pointer points to itself.


Note:
    You must return the copy of the given head as a reference to the cloned list.

*/

public class _0138_Copy_List_with_Random_Pointer {


    static class Node {
        public int val;
        public Node next;
        public Node random;

        public Node() {
        }

        public Node(int _val, Node _next, Node _random) {
            val = _val;
            next = _next;
            random = _random;
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Copy graph
    HashMap<Node, Node> map = new HashMap<Node, Node>();

    public Node copyRandomList(Node node) {
        if (node == null) {
            return null;
        } else if (!map.containsKey(node)) {
            Node cloned = new Node(node.val, null, null);
            map.put(node, cloned);
            cloned.next = copyRandomList(node.next);
            cloned.random = copyRandomList(node.random);
        }
        return map.get(node);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Iterative
    // Instead of a separate dictionary to keep the old node --> new node mapping, we can tweak the original linked list and keep every cloned node next to its original node. 
    // This interleaving of old and new nodes allows us to solve this problem without any extra space.
    public Node copyRandomList2(Node head) {
        if (head == null) {
            return null;
        }
        // 1. clone node, append it to the next ponter of the original
        Node curr = head;
        Node copyCurr;
        while (curr != null) {
            copyCurr = new Node(curr.val, null, null);
            copyCurr.next = curr.next;
            curr.next = copyCurr;
            curr = copyCurr.next;
        }
        // 2. iterate the list having both the new and old nodes intertwined with each other and use the original nodes' random pointers to assign references to random pointers for cloned nodes.
        curr = head;
        while (curr != null) {
            curr.next.random = (curr.random == null ? null : curr.random.next);
            curr = curr.next.next;
        }
        // 3. now that the random pointers are assigned to the correct node, the next pointers need to be correctly assigned to unweave the current linked list and get back the original list and the cloned list.
        curr = head;
        copyCurr = head.next;
        Node result = copyCurr;
        while (curr != null) {
            curr.next = curr.next.next;
            copyCurr.next = copyCurr.next == null ? null : copyCurr.next.next;
            curr = curr.next;
            copyCurr = copyCurr.next;
        }
        return result;
    }

}
