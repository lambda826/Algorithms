/**
 *  @author Yunxiang He
 *  @date 06/22/2018
 */

package questions.temp;

import common.Node;

import java.util.LinkedList;
import java.util.Queue;

/*

You are given a doubly linked list which in addition to the next and previous pointers, 
	it could have a child pointer, which may or may not point to a separate doubly linked list. 
These child lists may have one or more children of their own, and so on, to produce a multilevel data structure, as shown in the example below.

Flatten the list so that all the nodes appear in a single-level, doubly linked list. 
You are given the head of the first level of the list.

 

Example:
	Input:
		 1---2---3---4---5---6--NULL
		         |
		         7---8---9---10--NULL
		             |
		             11--12--NULL
	Output:
		1-2-3-7-8-11-12-9-10-4-5-6-NULL
	Explanation for the above example:
		Given the following multilevel doubly linked list:

We should return the following flattened doubly linked list:

*/

public class _0430_Flatten_a_Multilevel_Doubly_Linked_List {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public Node flatten(Node head) {
        Node node = head;
        DFS(node);
        return head;
    }

    private Node DFS(Node node) {
        if (node != null) {
            Node next = node.next;
            Node child = node.child;
            if (child != null) {
                node.next = child;
                node.child = null;
                child.prev = node;
                node = DFS(child);
                node.next = next;
                if (next != null) {
                    next.prev = node;
                }
            }
            if (next != null) {
                node = DFS(next);
            }
        }
        return node;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public Node flatten2(Node head) {
        if (head != null) {
            Queue<Node> q = new LinkedList<>();
            DFS(head, q);
            Node pre = q.remove();
            pre.child = null;
            while (!q.isEmpty()) {
                Node curr = q.remove();
                pre.next = curr;
                curr.prev = pre;
                curr.child = null;
                pre = curr;
            }
        }
        return head;
    }

    private void DFS(Node node, Queue<Node> q) {
        if (node != null) {
            q.add(node);
            DFS(node.child, q);
            DFS(node.next, q);
        }
    }
}

