/**
 *  @author Yunxiang He
 *  @date 03/10/2019
 */

package coding.temp;

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

    public static void main(String[] args) {
        Node node1 = new Node();
        Node node2 = new Node();
        node1.val = 1;
        node1.next = node2;
        node1.random = node2;
        node2.val = 2;
        node2.random = node2;
        _0138_Copy_List_with_Random_Pointer test = new _0138_Copy_List_with_Random_Pointer();
        test.copyRandomList(node1);
    }

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

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
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

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Iterative
    // Instead of a separate dictionary to keep the old node --> new node mapping, we can tweak the original linked list and keep every cloned node next to its original node. 
    // This interleaving of old and new nodes allows us to solve this problem without any extra space.
    public Node copyRandomList2(Node head) {
        if (head == null) {
            return null;
        }
        // 1. clone node, append it to the next ponter of the original
        Node curr1 = head;
        Node curr2;
        while (curr1 != null) {
            curr2 = new Node(curr1.val, null, null);
            curr2.next = curr1.next;
            curr1.next = curr2;
            curr1 = curr2.next;
        }
        // 2. iterate the list having both the new and old nodes intertwined with each other and use the original nodes' random pointers to assign references to random pointers for cloned nodes.
        curr1 = head;
        while (curr1 != null) {
            curr1.next.random = (curr1.random == null ? null : curr1.random.next);
            curr1 = curr1.next.next;
        }
        // 3. now that the random pointers are assigned to the correct node, the next pointers need to be correctly assigned to unweave the current linked list and get back the original list and the cloned list.
        curr1 = head;
        curr2 = head.next;
        Node result = curr2;
        while (curr1 != null) {
            curr1.next = curr1.next.next;
            curr2.next = curr2.next == null ? null : curr2.next.next;
            curr1 = curr1.next;
            curr2 = curr2.next;
        }
        return result;
    }

}
