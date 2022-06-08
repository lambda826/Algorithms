package questions._04_linkedList;

import common.Node;

/*

You are given a doubly linked list, which contains nodes that have a next pointer, a previous pointer, and an additional child pointer.
This child pointer may or may not point to a separate doubly linked list, also containing these special nodes.
These child lists may have one or more children of their own, and so on, to produce a multilevel data structure as shown in the example below.

Given the head of the first level of the list, flatten the list so that all the nodes appear in a single-level, doubly linked list.
Let curr be a node with a child list. The nodes in the child list should appear after curr and before curr.next in the flattened list.

Return the head of the flattened list. The nodes in the list must have all of their child pointers set to null.


Example 1:
    Input:
        head = [1,2,3,4,5,6,null,null,null,7,8,9,10,null,null,11,12]
    Output:
        [1,2,3,7,8,11,12,9,10,4,5,6]
    Explanation:
        The multilevel linked list in the input is shown.
        After flattening the multilevel linked list it becomes:

Example 2:
    Input:
        head = [1,2,null,3]
    Output:
        [1,3,2]
    Explanation:
        The multilevel linked list in the input is shown.
        After flattening the multilevel linked list it becomes:

Example 3:
    Input:
        head = []
    Output:
        []
    Explanation:
        There could be empty list in the input.


Constraints:
    The number of Nodes will not exceed 1000.
    1 <= Node.val <= 10^5

How the multilevel linked list is represented in test cases:
    We use the multilevel linked list from Example 1 above:
         1---2---3---4---5---6--NULL
                 |
                 7---8---9---10--NULL
                     |
                     11--12--NULL
    The serialization of each level is as follows:
        [1,2,3,4,5,6,null]
        [7,8,9,10,null]
        [11,12,null]
    To serialize all levels together, we will add nulls in each level to signify no node connects to the upper node of the previous level. The serialization becomes:
    [1,    2,    3, 4, 5, 6, null]
                 |
    [null, null, 7,    8, 9, 10, null]
                       |
    [            null, 11, 12, null]
    Merging the serialization of each level and removing trailing nulls we obtain:
    [1,2,3,4,5,6,null,null,null,7,8,9,10,null,null,11,12]

*/

public class _0430_Flatten_a_Multilevel_Doubly_Linked_List {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    class Solution_DFS {

        public Node flatten(Node head) {
            DFS(head);
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
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    class Solution {
        public Node flatten(Node node) {
            if (node != null) {
                helper(node);
            }
            return node;
        }

        // This method returns last non-null node of child
        private Node helper(Node node) {
            while (node.next != null || node.child != null) {
                if (node.child != null) {
                    Node next = node.next;
                    node.next = node.child;
                    node.child.prev = node;
                    Node childTail = helper(node.child);
                    if (childTail != null) {
                        childTail.next = next;
                        if (next != null) {
                            next.prev = childTail;
                        }
                    }
                    node.child = null;
                }
                node = node.next;
            }
            return node;
        }
    }


}
