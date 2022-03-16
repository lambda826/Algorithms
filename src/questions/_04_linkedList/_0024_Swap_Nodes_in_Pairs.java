package questions._04_linkedList;

import common.ListNode;

/*

Given a linked list, swap every two adjacent nodes and return its head.
You must solve the problem without modifying the values in the list's nodes (i.e., only nodes themselves may be changed.)


Example 1:
    Input:
        head = [1,2,3,4]
    Output:
        [2,1,4,3]

Example 2:
    Input:
        head = []
    Output:
        []

Example 3:
    Input:
        head = [1]
    Output:
        [1]


Constraints:
    The number of nodes in the list is in the range [0, 100].
    0 <= Node.val <= 100

*/
public class _0024_Swap_Nodes_in_Pairs {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    class Solution {
        public ListNode swapPairs(ListNode head) {
            ListNode temp = new ListNode();
            ListNode res = temp;
            temp.next = head;
            while (temp.next != null && temp.next.next != null) {
                swap(temp, temp.next, temp.next.next);
                temp = temp.next.next;
            }
            return res.next;
        }

        /**
         * Swap the position of n1 and n2 after n.
         */
        private void swap(ListNode n, ListNode n1, ListNode n2) {
            ListNode next = n2.next;
            n.next = n2;
            n2.next = n1;
            n1.next = next;
        }
    }
}