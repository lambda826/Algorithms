/**
 *  @author Yunxiang He
 *  @date 03/10/2019
 */

package questions.temp;

import common.ListNode;

/*

Given a linked list, remove the n-th node from the end of list and return its head.


Example:
    Given linked list: 1->2->3->4->5, and n = 2.
    After removing the second node from the end, the linked list becomes 1->2->3->5.


Note:
    Given n will always be valid.


Follow up:
    Could you do this in one pass?

*/

public class _0019_Remove_Nth_Node_From_End_of_List {

    public static void main(String[] args) {
        _0019_Remove_Nth_Node_From_End_of_List test = new _0019_Remove_Nth_Node_From_End_of_List();
        ListNode head1 = new ListNode(1);
        ListNode head2 = new ListNode(2);
        ListNode head3 = new ListNode(3);
        ListNode head4 = new ListNode(4);
        head1.next = head2;
        head2.next = head3;
        head3.next = head4;
        test.removeNthFromEnd(head1, 2);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode walker = dummy;
        ListNode walker2 = dummy;
        // walk (n + 1) steps
        while (n-- > -1) {
            walker = walker.next;
        }
        while (walker != null) {
            walker = walker.next;
            walker2 = walker2.next;
        }
        walker2.next = walker2.next.next;
        return dummy.next;
    }
}
