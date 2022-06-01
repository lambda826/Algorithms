package questions._04_linkedList;

import common.Node;

/*

Given a Circular Linked List node, which is sorted in ascending order, write a function to insert a value insertVal into the list such that it remains a sorted circular list.
The given node can be a reference to any single node in the list and may not necessarily be the smallest value in the circular list.

If there are multiple suitable places for insertion, you may choose any place to insert the new value. After the insertion, the circular list should remain sorted.

If the list is empty (i.e., the given node is null), you should create a new single circular list and return the reference to that single node.
Otherwise, you should return the originally given node.


Example 1:
    Input:
        head = [3,4,1],
        insertVal = 2
    Output:
        [3,4,1,2]
    Explanation:
        In the figure above, there is a sorted circular list of three elements.
        You are given a reference to the node with value 3, and we need to insert 2 into the list.
        The new node should be inserted between node 1 and node 3. After the insertion, the list should look like this, and we should still return node 3.

Example 2:
    Input:
        head = [],
        insertVal = 1
    Output:
        [1]
    Explanation:
        The list is empty (given head is null). We create a new single circular list and return the reference to that single node.

Example 3:
    Input:
        head = [1],
        insertVal = 0
    Output:
        [1,0]

*/
public class _0708_Insert_into_a_Cyclic_Sorted_List {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Walk through the linked list
    // There could be several cases:
    //    1. the given head is null, return the inserted node;
    //    2. the linked list nodes have the same value, use two pointers to check whether it circled back and insert the node;
    //    3. if the value of insert node is greater than the current and less than the next, insert the node;
    //    4. the position between the greatest and the smallest value can possibly also be inserted.
    class Solution {
        public Node insert(Node head, int insertVal) {
            Node insert = new Node(insertVal);
            insert.next = insert;
            if (head == null) {
                return insert;
            }
            Node curr = head;
            while (true) {
                if (curr.next == head
                    || insert.val >= curr.val && insert.val <= curr.next.val
                    || (curr.val > curr.next.val && (insert.val >= curr.val || insert.val <= curr.next.val))) {
                    insertAfter(curr, insert);
                    break;
                }
                curr = curr.next;
            }
            return head;
        }

        public void insertAfter(Node node, Node insert) {
            Node next = node.next;
            node.next = insert;
            insert.next = next;
        }
    }
}
