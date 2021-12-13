package questions._04_linkedList;

import common.ListNode;

/*

Given the head of a linked list and an integer val, remove all the nodes of the linked list that has Node.val == val, and return the new head.


Example 1:
    Input:
        head = [1,2,6,3,4,5,6],
        val = 6
    Output: [1,2,3,4,5]

Example 2:
    Input:
        head = [],
        val = 1
    Output:
        []

Example 3:
    Input:
        head = [7,7,7,7],
        val = 7
    Output:
        []


Constraints:
    The number of nodes in the list is in the range [0, 10^4].
    1 <= Node.val <= 50
    0 <= val <= 50

*/
public class _0203_Remove_Linked_List_Elements {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    class Solution_Iterative {
        public ListNode removeElements(ListNode head, int val) {
            ListNode temp = new ListNode();
            ListNode res = temp;
            temp.next = head;
            while (temp.next != null) {
                if (temp.next.val == val) {
                    temp.next = temp.next.next;
                } else {
                    temp = temp.next;
                }
            }
            return res.next;
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    class Solution_Recursive {
        public ListNode removeElements(ListNode head, int val) {
            if (head == null) {
                return null;
            } else if (head.val != val) {
                head.next = removeElements(head.next, val);
                return head;
            } else {
                return removeElements(head.next, val);
            }
        }
    }
}